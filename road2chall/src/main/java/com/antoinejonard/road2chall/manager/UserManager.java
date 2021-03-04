package com.antoinejonard.road2chall.manager;

import com.antoinejonard.road2chall.model.Team;
import com.antoinejonard.road2chall.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("user")
public class UserManager {

    @Autowired
    UserRepository userRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/allUsers")
    public List<User> getUsers(){
        List<User> allUsers = new ArrayList<>();
        userRepository.findAll().forEach(allUsers::add);
        return allUsers;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/createUser")
    public Response createUser(@QueryParam("name") String name, @QueryParam("pwd") String pwd){
        List<User> usersWithSameName = userRepository.findByName(name);
        User user;
        if(usersWithSameName.isEmpty())
            user = new User(name, pwd, new ArrayList<>());
        else
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        userRepository.save(user);
        return Response.ok(user).build();
    }
}
