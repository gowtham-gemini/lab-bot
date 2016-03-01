package net.apporbit.lab.bot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import net.apporbit.lab.bot.domain.entity.Subject;
import net.apporbit.lab.bot.domain.repository.jpa.SubjectReposiory;
import net.apporbit.lab.bot.util.AppValidator;
import net.apporbit.lab.bot.util.domain.vo.PagingAndSorting;
import net.apporbit.lab.bot.util.error.Errors;
import net.apporbit.lab.bot.util.error.exception.ApplicationException;
import net.apporbit.lab.bot.util.error.exception.EntityNotFoundException;

/**
 * Subject service implementation class.
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    /** Logger attribute. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectServiceImpl.class);

    /** Validator attribute. */
    @Autowired
    private AppValidator validator;

    /** Subject repository reference. */
    @Autowired
    private SubjectReposiory subjectRepo;

    @Override
    @PreAuthorize("hasAuthority('ROLE_DOMAIN_USER')")
    public Subject save(Subject subject) throws Exception {

        Errors errors = validator.rejectIfNullEntity("subject", subject);
        errors = validator.validateEntity(subject, errors);

        if (errors.hasErrors()) {
            throw new ApplicationException(errors);
        } else {
            return subjectRepo.save(subject);
        }
    }


    @Override
    public Subject update(Subject subject) throws Exception {

        Errors errors = validator.rejectIfNullEntity("subject", subject);
        errors = validator.validateEntity(subject, errors);

        if (errors.hasErrors()) {
            throw new ApplicationException(errors);
        } else {
            return subjectRepo.save(subject);
        }
    }

    @Override
    public void delete(Subject subject) throws Exception {
        subjectRepo.delete(subject);
    }

    @Override
    public void delete(Long id) throws Exception {
        subjectRepo.delete(id);
    }

    @Override
    @PreAuthorize("hasAuthority('ROLE_DOMAIN_USER')")
    public Subject find(Long id) throws Exception {
        Subject subject = subjectRepo.findOne(id);

        LOGGER.debug("Sample Debug Message");
        LOGGER.trace("Sample Trace Message");

        if (subject == null) {
            throw new EntityNotFoundException("subject.not.found");
        }
        return subject;
    }

    @Override
    public Page<Subject> findAll(PagingAndSorting pagingAndSorting) throws Exception {
        return subjectRepo.findAll(pagingAndSorting.toPageRequest());
    }

}
