package com.antoinejonard.road2chall.manager;

import com.antoinejonard.road2chall.model.Input.MemberInput;
import com.antoinejonard.road2chall.model.Input.NoteInput;
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
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(optionalTeam.get()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/teamCode/teams/{id}")
    public String getTeamCode(@PathParam("id") int id){
        Optional<Team> optUser = teamRepository.findById(id);
        return optUser.map(Team::getCode).orElse(null);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addMember/teams/{teamId}")
    public Response addMember(@PathParam("teamId") int id, MemberInput input){
        Optional<Team> optionalTeam = teamRepository.findById(id);

        if (optionalTeam.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        Team team = optionalTeam.get();

        team.getMembers().add(input.getName());

        teamRepository.save(team);

        return Response.ok(input.getName()).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addNote/teams/{teamId}")
    public Response addNote(@PathParam("teamId") int id, NoteInput input){
        Optional<Team> optionalTeam = teamRepository.findById(id);

        if (optionalTeam.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        Team team = optionalTeam.get();

        team.getNotes().add(input.getNote());

        teamRepository.save(team);

        return Response.ok(input.getNote()).build();
    }
}
