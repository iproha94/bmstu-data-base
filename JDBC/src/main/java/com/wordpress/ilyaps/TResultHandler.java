package com.wordpress.ilyaps;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ilyap on 23.11.2015.
 */
public interface TResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;
}
