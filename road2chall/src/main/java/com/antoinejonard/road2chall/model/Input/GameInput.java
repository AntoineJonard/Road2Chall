package com.antoinejonard.road2chall.model.Input;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GameInput {

    private int id;
    private String date;
    private boolean win;
    private String note;

    public GameInput(String date, boolean win, String note) {
        this.date = date;
        this.win = win;
        this.note = note;
    }

    public String getDate() throws ParseException {
        Date dateObject =new SimpleDateFormat("yyyy-MM-dd").parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateObject);
        return new SimpleDateFormat("dd/MM/yyyy").format(dateObject);
    }

    public boolean isWin() {
        return win;
    }

    public String getNote() {
        return note;
    }
}
