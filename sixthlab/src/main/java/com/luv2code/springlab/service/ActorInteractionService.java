package com.luv2code.springlab.service;

import com.luv2code.springlab.model.Client;
import com.luv2code.springlab.model.Landlord;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorInteractionService {

    private final Landlord landlord;
    private final ObjectFactory<Client> clientFactory;

    @Autowired
    public ActorInteractionService(Landlord landlord, ObjectFactory<Client> clientFactory) {
        this.landlord = landlord;
        this.clientFactory = clientFactory;
    }

    public String demonstrateInteraction() {
        Client client1 = clientFactory.getObject();
        Client client2 = clientFactory.getObject();

        return String.format("""
                Landlord: %s (singleton)
                Client 1: %s (%s)
                Client 2: %s (%s)
                """,
                landlord.getName(),
                client1.getName(), client1,
                client2.getName(), client2
        );
    }
}
