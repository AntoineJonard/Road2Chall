package com.antoinejonard.road2chall.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.SortNatural;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.util.*;

@Entity
public class Team implements Comparable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> notes;
    @ManyToMany(mappedBy = "teams", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id",
            scope = int.class
    )
    private Set<User> owners;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> members;
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id",
            scope = int.class
    )
    @SortNatural
    private Set<Game> games;
    private String code;

    public Team() {
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
        return String.valueOf(Math.abs(Objects.hash(name, description)));
    }

    @Override
    public int compareTo(Object o) {
        return getName().compareTo(((Team) o).getName());
    }
}
