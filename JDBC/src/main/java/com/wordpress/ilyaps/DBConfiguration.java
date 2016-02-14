package com.wordpress.ilyaps;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by ilyap on 23.11.2015.
 */
public class DBConfiguration {
    private String nameDriver;
    private String jdbcUrl;
    private String userName;
    private String password;

    public DBConfiguration(String fileName) {
        if (fileName == null) {
            throw new NullPointerException("fileName");
        }

        try (final FileInputStream fis = new FileInputStream(fileName)) {
            final Properties properties = new Properties();
            properties.load(fis);
            jdbcUrl = properties.getProperty("jdbc_url");
            nameDriver = properties.getProperty("name_driver");
            userName = properties.getProperty("user_name");
            password = properties.getProperty("user_password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jdbcUrl == null) {
            throw new NullPointerException("jdbcUrl");
        }
        if (nameDriver == null) {
            throw new NullPointerException("nameDriver");
        }
        if (userName == null) {
            throw new NullPointerException("userName");
        }
        if (password == null) {
            throw new NullPointerException("password");
        }
    }

    public String getNameDriver() {
        return nameDriver;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
