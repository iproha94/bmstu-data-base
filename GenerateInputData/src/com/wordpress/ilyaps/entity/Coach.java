package com.wordpress.ilyaps.entity;

/**
 * Created by ilyap on 20.09.2015.
 */
public class Coach {
    private int id;
    private String firstname;
    private String surname;
    private String middlename;
    private String sep = ",";

    public Coach(int id, String firstname, String surname, String middlename) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.middlename = middlename;
    }

    public static String getHat() {
        return "ID,FIRSTNAME,SURENAME,MIDDLENAME";
    }


    @Override
    public String toString() {
        return id + sep + firstname  + sep + surname  + sep + middlename;
    }
}
