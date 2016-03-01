package net.apporbit.lab.bot.domain.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import net.apporbit.lab.bot.domain.entity.Subject;

/**
 * Jpa Repository for Subject entity.
 */
public interface SubjectReposiory extends PagingAndSortingRepository<Subject, Long> {

}
