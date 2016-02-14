package com.wordpress.ilyaps.entity;

/**
 * Created by ilyap on 20.09.2015.
 */
public class Match {
    private int id;
    private int team1Id;
    private int team2Id;
    private int stadiumId;
    private int refereeId;
    private String date;
    private String sep = ",";

    @Override
    public String toString() {
        return id +sep + team1Id +sep + team2Id +sep + stadiumId +sep + refereeId +sep + date;
    }

    public static String getHat() {
        return "ID,TEAM1ID,TEAM2ID,STADIUMID,REFEREEID,DATE";
    }


    public Match(int id, int team1Id, int team2Id, int stadiumId, int refereeId, String date) {
        this.id = id;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.stadiumId = stadiumId;
        this.refereeId = refereeId;
        this.date = date;
    }
}
