package com.antoinejonard.road2chall.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Game implements Comparable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String date;
    private boolean win;
    private String note;
    @ManyToOne(optional = false)
    @JoinColumn(name="team_id", referencedColumnName = "id",nullable=false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id",
            scope = int.class
    )
    private Team team;

    public Game() {
    }

    public Game(String date, boolean win, String note) {
        this.date = date;
        this.win = win;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public int compareTo(Object o) {
        return this.date.compareTo(((Game)o).date);
    }
}
