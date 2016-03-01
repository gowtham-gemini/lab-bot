package net.apporbit.lab.bot.web.resource;

import java.util.List;

import net.apporbit.lab.bot.domain.entity.DomainUser;
import net.apporbit.lab.bot.domain.entity.Stuff;

/**
 * Service gateway interface.
 */
public interface ServiceGateway {

    /**
     * Get some stuff.
     * @return stuff.
     */
    List<Stuff> getSomeStuff();

    /**
     * Create stuff.
     * @param newStuff to set
     * @param domainUser to set
     */
    void createStuff(Stuff newStuff, DomainUser domainUser);
}
