package com.wordpress.ilyaps.entity;

/**
 * Created by ilyap on 20.09.2015.
 */
public class Referee {
    private int id;
    private String firstname;
    private String surname;
    private String middlename;
    private int cityId;
    private String sep = ",";

    @Override
    public String toString() {
        return id +sep + firstname +sep+ surname +sep+ middlename + sep + cityId;
    }

    public static String getHat() {
        return "ID,FIRSTNAME,SURENAME,MIDDLENAME,CITYID";
    }


    public Referee(int id, String firstname, String surname, String middlename, int cityId) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.middlename = middlename;
        this.cityId = cityId;
    }

}
