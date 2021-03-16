package com.antoinejonard.road2chall.model.Input;

import java.util.Date;

public class GameInput {

    private int id;
    private Date date;
    private boolean win;
    private String note;

    public GameInput(Date date, boolean win, String note) {
        this.date = date;
        this.win = win;
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public boolean isWin() {
        return win;
    }

    public String getNote() {
        return note;
    }
}
