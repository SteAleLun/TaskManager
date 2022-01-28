package swing.controller.action;

import dao.TaskDao;
import model.Task;
import swing.ui.TaskObserverFrame;

import javax.swing.*;
import java.util.List;

public class SelectListAction {
    private final TaskObserverFrame form;
    private final DefaultListModel<Task> jListModel;
    private final TaskDao taskDao;

    public SelectListAction(TaskObserverFrame form, DefaultListModel<Task> jListModel, TaskDao taskDao) {
        this.form = form;
        this.jListModel = jListModel;
        this.taskDao = taskDao;
    }

    public void loadDefaultList() {
        List<Task> taskList = taskDao.getTasks();

        for(Task task : taskList) {
            jListModel.addElement(task);
        }

    }
}
