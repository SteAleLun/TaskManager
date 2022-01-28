package db;

import dao.TaskDao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Scanner;

public class DatabaseManager {

    private static final String SQL_SCRIPT_PATH = "scripts";
    private Connection connection;

    public DatabaseManager(Connection connection) {
        this.connection = connection;
    }

    public void checkDatabase() throws Exception {
        if(!isSchemaCreated()){
            if(!createSchema()){
                throw new Exception("Database cannot be created");
            }
        }
    }

    // проверка существования схемы БД
    // и создание соединения если его нет
    private boolean isSchemaCreated() throws Exception {
        if (connection == null) {
            createDatabase();
        }
        ResultSet resultSet;
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            resultSet = databaseMetaData.getTables(SingleConnectionManager.TASK_MANAGER_DATABASE,
                    SingleConnectionManager.SCHEMA_NAME, TaskDao.TABLE_NAME, null);
            // если схема существует, возвращается true
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            // иначе false
            return false;
        }
    }

    // проверка была ли успешно создана схема
    private boolean createSchema() {
        boolean schemaCreated = false;

        try {
            executeScript(getScriptContent("drop_schema.sql"));
        } catch (IOException ignore) {
        }
        try {
            executeScript(getScriptContent("create_schema.sql"));
            schemaCreated = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return schemaCreated;
    }

    private String getScriptContent(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(SQL_SCRIPT_PATH +
                fileName)));
    }

    private void executeScript(String sql) {
        Scanner scanner = new Scanner(sql).useDelimiter(";");
        Statement statement = null;

        while (scanner.hasNext()){
            String rawStatement = scanner.next();
            try {
                statement = connection.createStatement();
                statement.execute(rawStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                statement = null;
            }
        }
    }

    private void createDatabase() throws Exception {
        String userHomeDir = System.getProperty("user.home", ".");
        String systemDir = userHomeDir + System.getProperty("file.separator")
                + "/.task_manager";
        System.setProperty("derby.system,home", systemDir);

        connection = SingleConnectionManager.getConnection(true);

        if (connection == null){
            throw new Exception("Critical error: database could not be created");
        }
    }

}
