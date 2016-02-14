package com.wordpress.ilyaps.model;

import com.wordpress.ilyaps.database.DBService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by ilyap on 05.12.2015.
 */
public class RefereeListWrapper extends DBService {
    private ObservableList<Referee> list = FXCollections.observableArrayList();

    public RefereeListWrapper(String configurationFileName) {
        super(configurationFileName);
    }

    public ObservableList<Referee> getList() {
        return list;
    }

    public void add(Referee tempReferee) {
        list.add(tempReferee);
        insertReferee(tempReferee);
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

    public void insertReferee(Referee referee) {
        try {
            getConnection().setAutoCommit(false);

            PreparedStatement psttm = getConnection().prepareStatement("insert into referee3" +
                    "(REFEREE_ID, FIRSTNAME, LASTNAME, MIDDLENAME, CITY)" +
                    "values (?, ?, ?, ?, ?) ");


            psttm.setLong(1, getMaxIdReferee() + 1);
            psttm.setString(2, referee.getFirstName());
            psttm.setString(3, referee.getLastName());
            psttm.setString(4, referee.getMiddleName());
            psttm.setLong(5, referee.getCityId());
            psttm.executeUpdate();
            getConnection().commit();
        } catch (SQLException ignored) {}

    }

    public void loadData() {
        try {
            getExecutor().execQuery("select * from referee3", result -> {
                while (result.next()) {
                    Referee  referee = new Referee();
                    referee.setId(result.getLong("referee_id"));
                    referee.setFirstName(result.getString("firstname"));
                    referee.setLastName(result.getString("lastname"));
                    referee.setMiddleName(result.getString("middlename"));
                    referee.setCityId(result.getLong("city"));
                    list.add(referee);
                }
                return null;
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean update(Referee referee) {
        try {
            getExecutor().execUpdate("update referee3 set " +
                    " firstname = '" + referee.getFirstName() +
                    "', lastname = '" + referee.getLastName() +
                    "', middlename = '" + referee.getMiddleName() +
                    "', city = " + referee.getCityId() +
                    " where referee_id = " + referee.getId() + " ");

            return true;
        } catch (SQLException e) {
            return false;

        }
    }

    public boolean delete(long id) {
        try {
            getExecutor().execUpdate("delete from referee3 where referee_id = " + id);
            return true;
        } catch (SQLException e) {
            return false;

        }
    }
}
