package com.seacam.cryptovalue;

/**
 * Created by jensensc on 3/16/18.
 */

public class Rate {
    private String name;
    private double cost;
    private String site;

    public Rate(String name, double cost, String site){
        this.name = name;
        this.cost = cost;
        this.site = site;
    }

    public String getName(){
        return name;
    }

    public double getCost(){
        return cost;
    }

    public String getSite() {
        return site;
    }

}
