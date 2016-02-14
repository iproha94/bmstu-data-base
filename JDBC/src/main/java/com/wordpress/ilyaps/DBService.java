package com.wordpress.ilyaps;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilyap on 23.11.2015.
 */
public class DBService {
    private Connection connection;
    private DBConfiguration dbConfiguration;
    private DBExecutor exec;

    public DBService(String configurationFileName) {
        dbConfiguration = new DBConfiguration(configurationFileName);
    }

    public Connection getConnection() {
        return connection;
    }

    public DBExecutor getExecutor() {
        return exec;
    }

    public Connection openConnection() {
        String nameDriver = dbConfiguration.getNameDriver();
        String jdbcUrl = dbConfiguration.getJdbcUrl();
        String userName = dbConfiguration.getUserName();
        String password = dbConfiguration.getPassword();

        try {
            Driver driver = (Driver) Class.forName(nameDriver).newInstance();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            exec = new DBExecutor(connection);
        } catch(Exception e){
            System.out.println(e);
        }

        return null;
    }

    public boolean isEmpty() {
        return connection == null;
    }

    public boolean closeConnection() {
        if (isEmpty()) {
            throw new NullPointerException();
        }

        try {
            connection.close();
            exec = null;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<String>  printInfoOfConnection() {
        List<String> list = new ArrayList<>();
        try {
            list.add("Autocommit: " + connection.getAutoCommit() + '\n');
            list.add("DB name: " + connection.getMetaData().getDatabaseProductName() + '\n');
            list.add("DB version: " + connection.getMetaData().getDatabaseProductVersion() + '\n');
            list.add("Driver name: " + connection.getMetaData().getDriverName() + '\n');
            list.add("Driver version: " + connection.getMetaData().getDriverVersion() + '\n');
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
