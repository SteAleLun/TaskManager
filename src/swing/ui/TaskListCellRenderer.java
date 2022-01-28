package swing.ui;

import model.Task;

import javax.swing.*;
import java.awt.*;

public class TaskListCellRenderer extends JPanel implements ListCellRenderer<Task> {

    private JLabel description;
    private JLabel dueDate;

    public TaskListCellRenderer(){
        setLayout(new GridBagLayout());
        setBackground(Color.LIGHT_GRAY);

        description = new JLabel();
        dueDate = new JLabel();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.WEST;
        add(description, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.EAST;
        add(dueDate, gbc);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Task> list, Task value, int index, boolean isSelected, boolean cellHasFocus) {

        description.setText(value.getDescription());
        dueDate.setText(value.getDueDate().toString());

        if(isSelected){
            setForeground(Color.WHITE);
            setBackground(Color.GRAY);
        } else {
            setForeground(Color.BLACK);
            setBackground(Color.LIGHT_GRAY);
        }

        return this;
    }
}
