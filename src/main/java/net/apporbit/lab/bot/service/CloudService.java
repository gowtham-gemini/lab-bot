package net.apporbit.lab.bot.service;

import org.springframework.stereotype.Service;

import net.apporbit.lab.bot.domain.entity.Cloud;
import net.apporbit.lab.bot.util.domain.CRUDService;

/**
 * Service class for Cloud.
 *
 * This service provides basic CRUD and essential api's for Cloud related business actions.
 */
@Service
public interface CloudService extends CRUDService<Cloud>  {

}
