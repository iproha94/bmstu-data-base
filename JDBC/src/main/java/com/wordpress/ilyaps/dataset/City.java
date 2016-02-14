package com.wordpress.ilyaps.dataset;

/**
 * Created by ilyap on 20.11.2015.
 */
public class City {

    private long id;
    private String name;

//    public City(String name) {
//        this.id = -1;
//        this.name = name;
//    }

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
        return "com.wordpress.ilyaps.dataset.City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
