import dao.ListDao;
import dao.TaskDao;
import db.DatabaseManager;
import db.SingleConnectionManager;
import model.Task;
import swing.controller.TaskListController;
import swing.ui.TaskObserverFrame;

import javax.swing.*;

public class Main {

    private TaskObserverFrame form;
    // модель списка задач
    private DefaultListModel<Task> jListModel;
    // обьект, отвечающий за загрузку данных
    private TaskDao taskDao;
    // обьект, отвечающий за загрузку листов задач
    private ListDao listDao;

    // конструктор для ухода от статического контекста
    public Main() throws Exception {
        new DatabaseManager(SingleConnectionManager.getConnection(false)).checkDatabase();

        taskDao = new TaskDao(null);
        listDao = new ListDao();

        jListModel = new DefaultListModel<>();

        // старт UI в отдельном потоке
        SwingUtilities.invokeLater(() -> {
            form = new TaskObserverFrame();
            form.setVisible(true);
            postGUIConstruction();
        });
    }

    private void postGUIConstruction() {
        new TaskListController(form, jListModel, taskDao, listDao);
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }
}
