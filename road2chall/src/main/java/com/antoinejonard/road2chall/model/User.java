package com.antoinejonard.road2chall.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User implements Comparable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String pwd;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "owners",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> teams;

    public User() {
    }

    public User(String name, String pwd, List<Team> teams) {
        this.name = name;
        this.pwd = pwd;
        this.teams = teams;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        return name.compareTo(((User) o).name);
    }
}
