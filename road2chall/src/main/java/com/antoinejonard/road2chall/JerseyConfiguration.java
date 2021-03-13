package com.antoinejonard.road2chall;

import com.antoinejonard.road2chall.manager.TeamManager;
import com.antoinejonard.road2chall.manager.UserManager;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("road2Chall")
@Component
@Configuration
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration(){
        register(UserManager.class);
        register(TeamManager.class);
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }
}
