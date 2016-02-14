package com.wordpress.ilyaps;

import com.wordpress.ilyaps.dataset.Referee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilyap on 24.11.2015.
 */
public class FootballDBService extends DBService {

    public FootballDBService(String configurationFileName) {
        super(configurationFileName);
    }

    public int getMaxIdReferee() {
        int maxId = 0;
        try {
            maxId = getExecutor().execQueryT("SELECT MAX(referee_id) FROM referee3", result -> {
                result.next();
                return result.getInt(1);
            });

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return maxId;
    }

    public Referee readReferee(int id) {
        Referee referee;
        try {
            referee = getExecutor().execQueryT("select * from referee3 where referee_id = " + id, result -> {
                result.next();
                return new Referee(result.getInt("referee_id"),
                        result.getString("firstname"),
                        result.getString("lastname"),
                        result.getString("middlename"),
                        result.getInt("city"));
            });

        } catch (SQLException e) {
            referee = null;
        }
        return referee;
    }

    public List<Referee> readRefereeByCity(int id) {
        List<Referee> listReferee =  new ArrayList<>();

        try {
            getExecutor().execQuery("select * from referee3 where city = " + id, result -> {

                while (result.next()) {
                    listReferee.add(new Referee(result.getInt("referee_id"),
                            result.getString("firstname"),
                            result.getString("lastname"),
                            result.getString("middlename"),
                            result.getInt("city")));
                }

                return null;
            });

        } catch (SQLException ignored) {}

        return listReferee;
    }

    public int insertReferee(List<Referee> listReferee) {
        int count = 0;
        try {
            getConnection().setAutoCommit(false);

            PreparedStatement psttm = getConnection().prepareStatement("insert into referee3" +
                    "(REFEREE_ID, FIRSTNAME, LASTNAME, MIDDLENAME, CITY)" +
                    "values (?, ?, ?, ?, ?) ");

            for (Referee referee : listReferee) {

                psttm.setLong(1, referee.getId());
                psttm.setString(2, referee.getFirstname());
                psttm.setString(3, referee.getLastname());
                psttm.setString(4, referee.getMiddlename());
                psttm.setLong(5, referee.getCity());
                try {
                    count += psttm.executeUpdate();
                } catch (SQLException ignored) {}
            }
            getConnection().commit();
        } catch (SQLException ignored) {}

        return count;
    }

    public int updateCityName(long id, String city) {

        try {
            CallableStatement sttm = getConnection().prepareCall("{call UPDATE_NAME_CITY2(?, ?)}");
            sttm.setLong(1, id);
            sttm.setString(2, city);

            return sttm.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public ResultSet getRS() {
        ResultSet rs = null;
        try {
            Statement st = getConnection().createStatement();
            rs = st.executeQuery("select * from referee3 where city = 1");
        } catch (SQLException ignored) {
        }
        return rs;
    }
}
