package com.antoinejonard.road2chall.manager;

import com.antoinejonard.road2chall.model.Input.TeamInput;
import com.antoinejonard.road2chall.model.Team;
import com.antoinejonard.road2chall.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("team")
public class TeamManager {

    @Autowired
    TeamRepository teamRepository;

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createTeam")
    public Team createTeam(TeamInput input){
        Team team = new Team(input.getName(),input.getDescription());
        teamRepository.save(team);
        return team;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/allTeams")
    public List<Team> getTeams(){
        List<Team> allUsers = new ArrayList<>();
        teamRepository.findAll().forEach(allUsers::add);
        return allUsers;
    }
}
