package com.wordpress.ilyaps.entity;

/**
 * Created by ilyap on 20.09.2015.
 */
public class City {
    private int id;
    private String name;
    private String sep = ",";

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String getHat() {
        return "ID,NAME";
    }


    @Override
    public String toString() {
        return id + sep + name;
    }
}
