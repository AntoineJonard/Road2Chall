package com.antoinejonard.road2chall.model.Input;
import com.antoinejonard.road2chall.model.Game;
import com.antoinejonard.road2chall.model.User;
import java.util.List;

public class TeamInput {

    private Integer id;
    private String name;
    private String description;
    private List<String> notes;
    private List<User> owners;
    private List<String> members;
    private List<Game> games;

    public TeamInput(Integer id, String name, String description, List<String> notes, List<User> owners, List<String> members, List<Game> games) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.notes = notes;
        this.owners = owners;
        this.members = members;
        this.games = games;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getNotes() {
        return notes;
    }

    public List<User> getOwners() {
        return owners;
    }

    public List<String> getMembers() {
        return members;
    }

    public List<Game> getGames() {
        return games;
    }
}
