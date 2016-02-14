package com.wordpress.ilyaps.entity;

/**
 * Created by ilyap on 20.09.2015.
 */
public class Team {
    private int id;
    private String name;
    private int coachId;
    private int cityId;
    private String sep = ",";

    @Override
    public String toString() {
        return id + sep + name + sep + coachId + sep + cityId;
    }

    public static String getHat() {
        return "ID,NAME,COACHID,CITYID";
    }


    public Team(int id, String name, int coachId, int cityId) {
        this.id = id;
        this.name = name;
        this.coachId = coachId;
        this.cityId = cityId;
    }
}
