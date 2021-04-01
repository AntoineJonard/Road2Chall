package com.antoinejonard.road2chall.manager;
import com.antoinejonard.road2chall.model.Input.Summoner;
import com.antoinejonard.road2chall.model.Input.SummonerStats;
import com.antoinejonard.road2chall.model.Input.SummonerStatsList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
    public Response getSummoner(@PathParam("name") String name) throws JsonProcessingException {

        // Création d'une instance de client pour faire des requetes REST
        Client client = ClientBuilder.newClient(new ClientConfig());
        // API Cible
        WebTarget webTarget = client.target("https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/"
                +name
                +"?api_key=RGAPI-e83e0a4f-29d8-426f-834d-af50e1e84033");

        // Ce qu'on recoit
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        Summoner summoner;
        try{
            // Parsage automatique en objet dont les champs ont les memes valeurs
            summoner = response.readEntity(Summoner.class);
        }catch (ProcessingException e){
            // Il y a une erreur lorsque que j'ai dépassé le nombre max d'appel à l'API, je la catch ici et préviens le client
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }

        // API Cible
        webTarget = client.target("https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/"+summoner.getId()+"?api_key=RGAPI-e83e0a4f-29d8-426f-834d-af50e1e84033");

        invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        response = invocationBuilder.get();
        response.bufferEntity();

        // Cette fois je convertit juste le json en string
        String stringStats = response.readEntity(String.class);
        SummonerStats[] summonerStats;
        try{
            // J'utilise une outil de Parsage plus avancé car la structure du JSON est plus complexe
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

            // Reception dans un tableau car le JSON n'a pas de valeur au premier niveau de profondeur, juste des éléments
            summonerStats = objectMapper.readValue(stringStats, SummonerStats[].class);
        }catch (UnrecognizedPropertyException e){
            // Il y a une erreur lorsque que j'ai dépassé le nombre max d'appel à l'API, je la catch ici et préviens le client
           return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
        return Response.ok(summonerStats).build();
    }
}
