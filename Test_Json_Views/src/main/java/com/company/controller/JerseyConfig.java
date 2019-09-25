package com.company.controller;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * Created by ponomarev_ia on 27.08.2019.
 */
@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig()
    {
        register(FirstController.class );
        register(SecondController.class);
    }
}
