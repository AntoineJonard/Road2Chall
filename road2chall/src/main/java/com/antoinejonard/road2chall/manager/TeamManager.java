package com.antoinejonard.road2chall.manager;

import com.antoinejonard.road2chall.model.Team;
import com.antoinejonard.road2chall.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("team")
public class TeamManager {

    @Autowired
    TeamRepository teamRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/allTeams")
    public List<Team> getTeams(){
        List<Team> allUsers = new ArrayList<>();
        teamRepository.findAll().forEach(allUsers::add);
        return allUsers;
    }
}
