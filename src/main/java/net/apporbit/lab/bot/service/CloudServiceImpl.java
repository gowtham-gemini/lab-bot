package net.apporbit.lab.bot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import net.apporbit.lab.bot.domain.entity.Cloud;
import net.apporbit.lab.bot.domain.repository.jpa.CloudReposiory;
import net.apporbit.lab.bot.util.AppValidator;
import net.apporbit.lab.bot.util.domain.vo.PagingAndSorting;
import net.apporbit.lab.bot.util.error.Errors;
import net.apporbit.lab.bot.util.error.exception.ApplicationException;
import net.apporbit.lab.bot.util.error.exception.EntityNotFoundException;

/**
 * Cloud service implementation class.
 */
@Service
public class CloudServiceImpl implements CloudService {

    /** Logger attribute. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CloudServiceImpl.class);

    /** Validator attribute. */
    @Autowired
    private AppValidator validator;

    /** Cloud repository reference. */
    @Autowired
    private CloudReposiory cloudRepo;

    @Override
    
    public Cloud save(Cloud cloud) throws Exception {
        Errors errors = validator.rejectIfNullEntity("cloud", cloud);
        errors = validator.validateEntity(cloud, errors);

        if (errors.hasErrors()) {
            throw new ApplicationException(errors);
        } else {
            return cloudRepo.save(cloud);
        }
    }

    @Override
    public Cloud update(Cloud cloud) throws Exception {
        
        Errors errors = validator.rejectIfNullEntity("cloud", cloud);
        errors = validator.validateEntity(cloud, errors);

        if (errors.hasErrors()) {
            throw new ApplicationException(errors);
        } else {
            return cloudRepo.save(cloud);
        }
    }

    @Override
    public void delete(Cloud cloud) throws Exception {
        cloudRepo.delete(cloud);
    }

    @Override
    public void delete(Long id) throws Exception {
        cloudRepo.delete(id);
    }

    @Override
    public Cloud find(Long id) throws Exception {
        
        Cloud cloud = cloudRepo.findOne(id);

        LOGGER.debug("Sample Debug Message");
        LOGGER.trace("Sample Trace Message");

        if (cloud == null) {
            throw new EntityNotFoundException("cloud.not.found");
        }
        return cloud;
    }

    @Override
    public Page<Cloud> findAll(PagingAndSorting pagingAndSorting) throws Exception {
        return cloudRepo.findAll(pagingAndSorting.toPageRequest());
    }
}
