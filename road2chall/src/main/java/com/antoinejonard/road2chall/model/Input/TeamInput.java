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
    private String code;

    public TeamInput(Integer id, String name, String description, List<String> notes, List<User> owners, List<String> members, List<Game> games, String code) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.notes = notes;
        this.owners = owners;
        this.members = members;
        this.games = games;
        this.code = code;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public void setOwners(List<User> owners) {
        this.owners = owners;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
