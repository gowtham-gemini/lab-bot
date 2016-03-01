package net.apporbit.lab.bot.service;

import org.springframework.stereotype.Service;

import net.apporbit.lab.bot.domain.entity.Subject;
import net.apporbit.lab.bot.util.domain.CRUDService;

/**
 * Service class for Subject.
 *
 * This service provides basic CRUD and essential api's for Subject related business actions.
 */
@Service
public interface SubjectService extends CRUDService<Subject>  {

}
