package com.wordpress.ilyaps.entity;

/**
 * Created by ilyap on 20.09.2015.
 */
public class Stadium {
    private int id;
    private String name;
    private int capacity;
    private int cityId;
    private String sep = ",";

    @Override
    public String toString() {
        return id + sep + name + sep + capacity + sep + cityId;
    }

    public static String getHat() {
        return "ID,NAME,CAPACITY,CITYID";
    }


    public Stadium(int id, String name, int capacity, int cityId) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.cityId = cityId;
    }
}
