package net.apporbit.lab.bot.domain.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import net.apporbit.lab.bot.domain.entity.Cloud;


/**
 * Jpa Repository for Cloud entity.
 */
public interface CloudReposiory extends PagingAndSortingRepository<Cloud, Long> {

}
