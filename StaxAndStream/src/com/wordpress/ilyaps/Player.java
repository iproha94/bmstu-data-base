package com.wordpress.ilyaps;

/**
 * Created by ilyap on 18.11.2015.
 */

public class Player  implements Comparable<Player>, Cloneable  {

    public enum Position {
        EMPTY,
        FORWARD,
        MIDFIELDER,
        DEFENDER,
        GOALKEEPER
    }

    private String name;
    private String team;
    private int age;
    private Position position;

    public Player() {
        name = "";
        team = "";
        age = 0;
        position = Position.EMPTY;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPosition() {
        return position.name();
    }

    public Player(String name, String team, int age, Position position) {
        this.name = name;
        this.team = team;
        this.age = age;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Player getCloneWithModifyTeam(String team) {
        Player player = null;
        try {
            player = (Player)this.clone();
            player.setTeam(team);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return player;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(Player o) {
        if (name.equals(o.getName())) {
            return Integer.compare(age, o.getAge());
        } else {
            return name.compareTo(o.getName());
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", team='" + team + '\'' +
                ", age=" + age +
                ", position=" + position +
                '}';
    }
}
