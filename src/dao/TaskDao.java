package dao;

import model.Task;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskDao {
    public static final String TABLE_NAME = "Task";
    private Connection dbConnection;

    public TaskDao(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public List<Task> getTasks(){
        return createDemo();
    }

    // тестовый список задач
    private List<Task> createDemo() {
        List<Task> list = new ArrayList<>();
        list.add(new Task("Send email", new Date()));
        list.add(new Task("Send message", new Date()));
        list.add(new Task("Create app", new Date()));
        list.add(new Task("Prepare new report", new Date()));
        list.add(new Task("Stop working", new Date()));
        list.add(new Task("Relax", new Date()));

        return list;
    }
}
