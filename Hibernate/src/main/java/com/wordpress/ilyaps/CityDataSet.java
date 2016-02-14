package com.wordpress.ilyaps;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ilyap on 20.11.2015.
 */
@Entity
@Table(name = "CITY2")
public class CityDataSet implements Serializable { // Serializable Important to Hibernate!
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "CITY_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "NAME")
    private String name;

    //Important to Hibernate!
    public CityDataSet() {
    }

    public CityDataSet(String name) {
        this.id = -1;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "com.wordpress.ilyaps.CityDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
