package model;

public class TaskList {

    // идентификатор списка
    private long taskListId;
    // название задачи
    private String name;

    public long getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(long taskListId) {
        this.taskListId = taskListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
