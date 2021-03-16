package com.antoinejonard.road2chall.manager;

import com.antoinejonard.road2chall.model.Input.TeamInput;
import com.antoinejonard.road2chall.model.Team;
import com.antoinejonard.road2chall.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("team")
public class TeamManager {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    UserRepository userRepository;

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
        List<Team> allTeams = new ArrayList<>();
        teamRepository.findAll().forEach(allTeams::add);
        return allTeams;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/teams/{teamId}")
    public Response getTeam(@PathParam("teamId") Integer teamId){
        Optional<Team> optionalTeam = teamRepository.findById(teamId);

        if (optionalTeam.isEmpty())
            return Response.status(Response.Status.NO_CONTENT).build();
        return Response.ok(optionalTeam.get()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/teamCode/teams/{id}")
    public String getTeamCode(@PathParam("id") int id){
        Optional<Team> optUser = teamRepository.findById(id);
        return optUser.map(Team::getCode).orElse(null);
    }
}
