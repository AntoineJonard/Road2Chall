package com.antoinejonard.road2chall;

import com.antoinejonard.road2chall.manager.UserManager;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("road2Chall")
@Component
@Configuration
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration(){
        register(UserManager.class);
    }
}
