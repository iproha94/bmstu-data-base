package com.wordpress.ilyaps;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ilyap on 21.11.2015.
 */

@Entity
@Table(name = "REFEREE3")
public class RefereeDataSet implements Serializable {
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "REFEREE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "LASTNAME")
    private String lastname;
    @Column(name = "MIDDLENAME")
    private String middlename;

    @ManyToOne
    @JoinColumn(name = "CITY")
    private CityDataSet city;

    @Override
    public String toString() {
        return "RefereeDataSet{" +
                "city=" + city.toString() +
                ", middlename='" + middlename + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", id=" + id +
                '}';
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

    public CityDataSet getCity() {
        return city;
    }

    public void setCity(CityDataSet city) {
        this.city = city;
    }

    //Important to Hibernate!
    public RefereeDataSet() {
    }
}
