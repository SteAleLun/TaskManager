package swing.controller;

import dao.ListDao;
import dao.TaskDao;
import model.Task;
import swing.controller.action.SelectListAction;
import swing.ui.TaskObserverFrame;

import javax.swing.*;

public class TaskListController {

    private final TaskObserverFrame form;
    private final DefaultListModel<Task> jListModel;
    private final TaskDao taskDao;
    private final ListDao listDao;
    private SelectListAction selectListAction;

    public TaskListController(TaskObserverFrame form, DefaultListModel<Task> jListModel, TaskDao taskDao, ListDao listDao) {
        this.form = form;
        this.jListModel = jListModel;
        this.taskDao = taskDao;
        this.listDao = listDao;
        init();
    }

    public void init(){
        initForm();
        initTaskInput();
        initActionButtons();
        loadDefaultList();
    }

    private void initForm() {
        form.getJListComponent().setModel(jListModel);
    }

    private void loadDefaultList() {
        selectListAction = new SelectListAction(form, jListModel, taskDao);
        selectListAction.loadDefaultList();
    }

    private void initActionButtons() {
        // Complete, Postpone
    }

    private void initTaskInput() {
        // new task focus logic
    }

}
