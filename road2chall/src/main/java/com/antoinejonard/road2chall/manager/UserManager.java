package com.antoinejonard.road2chall.manager;
import com.antoinejonard.road2chall.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

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
    public User createUser(@QueryParam("name") String name, @QueryParam("pwd") String pwd){
        List<User> usersWithSameName = userRepository.findByName(name);
        User user;
        if(usersWithSameName.isEmpty())
            user = new User(name, pwd, new ArrayList<>());
        else
            return null;
            //return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        userRepository.save(user);
        return user;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/connexion")
    public Response connect(@QueryParam("name") String name, @QueryParam("pwd") String pwd){
        List<User> usersWithSameName = userRepository.findByName(name);
        if (usersWithSameName.isEmpty())
            return Response.status(Response.Status.UNAUTHORIZED).build();
        User user = usersWithSameName.get(0);
        if (user.getPwd().equals(pwd))
            return Response.ok(user).build();
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
