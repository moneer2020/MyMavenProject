package com.hrms.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class DatabaseUtils {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(ConfigurationReader.getPropertyValue("databaseUrl"),
                    ConfigurationReader.getPropertyValue("databaseUsername"),
                    ConfigurationReader.getPropertyValue("databasePassword"));
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static ResultSet getResultSet(String sqlQuery) {
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlQuery);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static List<Map<String, String>> databaseListOfMaps(String query) {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map;

        try {
            ResultSetMetaData metaData = getResultSet(query).getMetaData();
            while (resultSet.next()) {
                map = new LinkedHashMap<>();
                for (int i = 1; i < metaData.getColumnCount(); i++) {
                    map.put(metaData.getColumnName(i), resultSet.getObject(i).toString());
                }
                list.add(map);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    public static ArrayList<String> databaseListOfStrings(String query) {
        ArrayList<String> list = new ArrayList<>();
        String columnData;
        try {
            ResultSet resultSet = getResultSet(query);
            while (resultSet.next()) {
                columnData = resultSet.getString("job_title");
                list.add(columnData);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return list;
    }

    public static void closeConnection() {
        if (resultSet != null) {
            try {
                resultSet.close();
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
