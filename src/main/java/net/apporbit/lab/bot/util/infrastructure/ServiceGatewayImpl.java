package net.apporbit.lab.bot.util.infrastructure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.apporbit.lab.bot.domain.entity.DomainUser;
import net.apporbit.lab.bot.domain.entity.Stuff;

/**
 * Service gateway implementation.
 *
 */
@Component
public class ServiceGatewayImpl extends ServiceGatewayBase implements ServiceGateway {

    /**
     * Parameterized constructor.
     * @param authenticatedExternalServiceProvider to set
     */
    @Autowired
    public ServiceGatewayImpl(AuthenticatedExternalServiceProvider authenticatedExternalServiceProvider) {
        super(authenticatedExternalServiceProvider);
    }

    @Override
    public List<Stuff> getSomeStuff() {
        String stuffFromExternalWebService = externalService().getSomeStuff();
        // do some processing, create return list
        return null;
    }

    @Override
    public void createStuff(Stuff newStuff, DomainUser domainUser) {
        // do some processing, store domainUser in newStuff, send newStuff over the wire to external web service etc.
    }
}

