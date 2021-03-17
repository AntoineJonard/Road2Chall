package com.antoinejonard.road2chall.model.Input;

import java.util.List;

public class SummonerStatsList {

    private List<SummonerStats> summonerStatsList;

    public SummonerStatsList(){

    }

    public SummonerStatsList(List<SummonerStats> summonerStatsList) {
        this.summonerStatsList = summonerStatsList;
    }

    public List<SummonerStats> getSummonerStatsList() {
        return summonerStatsList;
    }

    public void setSummonerStatsList(List<SummonerStats> summonerStatsList) {
        this.summonerStatsList = summonerStatsList;
    }
}
