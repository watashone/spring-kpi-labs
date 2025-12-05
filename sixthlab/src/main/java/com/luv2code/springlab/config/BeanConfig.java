package com.luv2code.springlab.config;


import com.luv2code.springlab.model.Client;
import com.luv2code.springlab.model.Landlord;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfig {

    @Bean
    @Scope("singleton")
    public Landlord landlord() {
        Landlord landlord = new Landlord();
        landlord.setName("Main Landlord");
        return landlord;
    }

    @Bean
    @Scope("prototype")
    public Client client() {
        Client client = new Client();
        client.setName("New Client");
        return client;
    }
}
