package com.antoinejonard.road2chall.manager;
import com.antoinejonard.road2chall.model.Input.UserInput;
import com.antoinejonard.road2chall.model.Team;
import com.antoinejonard.road2chall.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("user")
public class UserManager {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TeamRepository teamRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/allUsers")
    public List<User> getUsers(){
        List<User> allUsers = new ArrayList<>();
        userRepository.findAll().forEach(allUsers::add);
        return allUsers;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getTeams/users/{id}")
    public Set<Team> getUserTeams(@PathParam("id") int id){
        Optional<User> optUser = userRepository.findById(id);
        return optUser.map(User::getTeams).orElse(null);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createUser")
    public Response createUser(UserInput input){
        List<User> usersWithSameName = userRepository.findByName(input.getName());
        User user;
        if(usersWithSameName.isEmpty())
            user = new User(input.getName(), input.getPwd(), new TreeSet<>());
        else
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        userRepository.save(user);
        return Response.ok(user).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/connexion")
    public Response connect(UserInput input){
        List<User> usersWithSameName = userRepository.findByName(input.getName());
        if (usersWithSameName.isEmpty())
            return Response.status(Response.Status.UNAUTHORIZED).build();
        User user = usersWithSameName.get(0);
        if (user.getPwd().equals(input.getPwd()))
            return Response.ok(user).build();
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("addTeam/personnes/{idUser}/teams/{codeTeam}")
    public Team addTeam(@PathParam("idUser") Integer idUser,@PathParam("codeTeam") String codeTeam){
        Optional<User> optUser = userRepository.findById(idUser);
        List<Team> optTeam = teamRepository.findByCode(codeTeam);

        if (optTeam.isEmpty() || optUser.isEmpty())
            return null;
        User user = optUser.get();
        Team team = optTeam.get(0);

        user.getTeams().add(team);

        userRepository.save(user);

        return  team;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("removeTeam/personnes/{idUser}/teams/{idTeam}")
    public  Team removeTeam(@PathParam("idUser") Integer idUser, @PathParam("idTeam") Integer idTeam){
        Optional<User> optUser = userRepository.findById(idUser);
        Optional<Team> optTeam = teamRepository.findById(idTeam);

        if (optTeam.isEmpty() || optUser.isEmpty())
            return null;

        User user = optUser.get();
        Team team = optTeam.get();

        user.getTeams().removeIf(teamIt -> teamIt.getId()==team.getId());


        userRepository.save(user);

        return  team;

    }
}
