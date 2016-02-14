package com.wordpress.ilyaps.dataset;

/**
 * Created by ilyap on 21.11.2015.
 */

public class Referee {
    private long id;
    private String firstname;
    private String lastname;
    private String middlename;
    private int cityId;

    public Referee(long id, String firstname, String lastname, String middlename, int cityId) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.cityId = cityId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public int getCity() {
        return cityId;
    }

    public void setCity(int cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "Referee{" +
                "city=" + cityId +
                ", middlename='" + middlename + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", id=" + id +
                '}';
    }
}
