package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SingleConnectionManager {

    public static final String TASK_MANAGER_DATABASE = "TaskManager";
    public static final String SCHEMA_NAME = "tm";
    private static Connection connection;

    public static Connection getConnection(boolean create) {
        if(connection == null){
            Properties properties = new Properties();
            properties.setProperty("user", SCHEMA_NAME);
            properties.setProperty("password", "tm");

            if(create){
                properties.setProperty("create", "true");
            }

            try {
                connection = DriverManager.getConnection(getURL(), properties);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

    private static String getURL() {
        return "jdbc:derby:" + TASK_MANAGER_DATABASE;
    }

}
