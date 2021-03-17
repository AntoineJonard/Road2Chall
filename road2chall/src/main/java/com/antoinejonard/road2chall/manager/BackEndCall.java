package com.antoinejonard.road2chall.manager;
import com.antoinejonard.road2chall.model.Input.Summoner;
import com.antoinejonard.road2chall.model.Input.SummonerStats;
import com.antoinejonard.road2chall.model.Input.SummonerStatsList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("call")
public class BackEndCall {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/summoner/{name}")
    public Response getTeam(@PathParam("name") String name) throws JsonProcessingException {

        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target("https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/"
                +name
                +"?api_key=RGAPI-e83e0a4f-29d8-426f-834d-af50e1e84033");

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        Summoner summoner;
        try{
            summoner = response.readEntity(Summoner.class);
        }catch (ProcessingException e){
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }

        webTarget = client.target("https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/"+summoner.getId()+"?api_key=RGAPI-e83e0a4f-29d8-426f-834d-af50e1e84033");

        invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        response = invocationBuilder.get();
        response.bufferEntity();
        String stringStats = response.readEntity(String.class);
        SummonerStats[] summonerStats;
        try{
            summonerStats = new ObjectMapper().readValue(stringStats, SummonerStats[].class);
        }catch (UnrecognizedPropertyException e){
           return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
        return Response.ok(summonerStats).build();
    }
}
