package com.antoinejonard.road2chall.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> notes;
    @ManyToMany(mappedBy = "teams", fetch = FetchType.EAGER)
    private Set<User> owners;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> members;
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Game> games;
    private String code;

    public Team() {
    }

    public Team(int id, String name, String description, Set<String> notes, Set<User> owners, Set<String> members, Set<Game> games, String code) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.notes = notes;
        this.owners = owners;
        this.members = members;
        this.games = games;
        this.code = code;
    }

    public Team(String name, String description) {
        this.name = name;
        this.description = description;
        games = new TreeSet<>();
        members = new TreeSet<>();
        owners = new TreeSet<>();
        notes = new TreeSet<>();
        this.setCode(generateCode());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getNotes() {
        return notes;
    }

    public void setNotes(Set<String> notes) {
        this.notes = notes;
    }

    public Set<User> getOwners() {
        return owners;
    }

    public void setOwners(Set<User> owners) {
        this.owners = owners;
    }

    public Set<String> getMembers() {
        return members;
    }

    public void setMembers(Set<String> members) {
        this.members = members;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String generateCode() {
        return String.valueOf(Objects.hash(name, description));
    }
}
