package com.antoinejonard.road2chall.model.Input;

public class BO {
    private int target;
    private int wins;
    private int losses;
    private String progress;

    public BO() {
    }

    public BO(int target, int wins, int losses, String progress) {
        this.target = target;
        this.wins = wins;
        this.losses = losses;
        this.progress = progress;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
