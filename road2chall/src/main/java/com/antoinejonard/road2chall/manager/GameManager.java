package com.antoinejonard.road2chall.manager;

import com.antoinejonard.road2chall.model.Game;
import com.antoinejonard.road2chall.model.Team;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("game")
public class GameManager {

    @Autowired
    GameRepository gameRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/games/{gameId}")
    public Response getTeam(@PathParam("gameId") Integer gameId){
        Optional<Game> optionalGame = gameRepository.findById(gameId);

        if (optionalGame.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(optionalGame.get()).build();
    }
}
