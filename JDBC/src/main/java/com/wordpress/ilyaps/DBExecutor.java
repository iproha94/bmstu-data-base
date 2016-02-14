package com.wordpress.ilyaps;

import java.sql.*;
import java.util.Map;

/**
 * Created by ilyap on 23.11.2015.
 */
public class DBExecutor {
    Connection connection;

    public DBExecutor(Connection connection) {
        this.connection = connection;
    }

    public <T> T execQueryT(String query,
                            TResultHandler<T> handler)
            throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeQuery(query);
        ResultSet result = stmt.getResultSet();
        T value = handler.handle(result);
        result.close();
        stmt.close();

        return value;
    }

    public void execQuery(String query, TResultHandler handler)
            throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeQuery(query);
        ResultSet result = stmt.getResultSet();
        handler.handle(result);
        result.close();
        stmt.close();
    }

    public int execUpdate(String update) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(update);
        int count = stmt.getUpdateCount();
        stmt.close();
        return count;
    }

    @Deprecated
    public int execUpdate(Map<Integer, String> idToName, String update) throws SQLException {
        int count = 0;

        try(PreparedStatement stmt = connection.prepareStatement(update)) {
            connection.setAutoCommit(false);

            for (Integer id : idToName.keySet()) {
                stmt.setString(1, Integer.toString(id));
                stmt.setString(2, idToName.get(id));
                stmt.executeUpdate();
                count += stmt.getUpdateCount();
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException();
        }

        return count;
    }

}