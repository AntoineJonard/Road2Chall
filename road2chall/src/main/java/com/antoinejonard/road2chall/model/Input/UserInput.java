package com.antoinejonard.road2chall.model.Input;

public class UserInput {

    private Integer id;
    private String name;
    private String pwd;

    public UserInput(int id, String name, String pwd) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }
}
