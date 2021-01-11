package com.developer.vinay22ji.gamerscoin_arena.Models;

public class PrizeModel {

    private String gcoin,name;

    public PrizeModel(String gcoin, String name) {
        this.gcoin = gcoin;
        this.name = name;
    }

    public PrizeModel() {
    }

    public String getGcoin() {
        return gcoin;
    }

    public void setGcoin(String gcoin) {
        this.gcoin = gcoin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
