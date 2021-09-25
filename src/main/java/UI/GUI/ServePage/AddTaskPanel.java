package UI.GUI.ServePage;

import Parser.GUIParser;

import javax.swing.*;
import java.awt.*;

public class AddTaskPanel extends JPanel {
    private JButton addTask;
    private JLabel addTaskHint;

    private JComboBox taskType;
    private JTextArea taskName;
    private JTextField time;


    public JButton getAddTaskButton() {
        return this.addTask;
    }

    public String getTaskType() {
        return this.taskType.getSelectedItem().toString();
    }

    public String getTaskName() {
        return this.taskName.getText();
    }

    public String getTaskTime() {
        return this.time.getText();
    }

    public AddTaskPanel() {
        this.setPreferredSize(new Dimension(300, 260));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setLayout(new GridBagLayout());
        initComponent();

    }

    private void initComponent () {
        initializeLabel();
        initializeComboBox();
        initializeTaskNameField();
        initializeTimeField();
        initializeAddButton();
    }

    private void initializeLabel() {
        addTaskHint = new JLabel("Create New Task");
        addTaskHint.setPreferredSize(new Dimension(250, 40));
        addTaskHint.setFont(new Font("Mv Boli", Font.BOLD, 16));
        addTaskHint.setForeground(Color.orange);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.ipadx = 20;
        this.add(addTaskHint, c);
    }

    private void initializeComboBox() {
        GridBagConstraints c = new GridBagConstraints();
        taskType = new JComboBox(new String[] {"todo", "deadline", "event"});
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.ipady = 30;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(taskType, c);
    }

    private void initializeTaskNameField() {
        taskName = new JTextArea("Put the task name here");

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 3;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 90;

        this.add(taskName, c);
    }

    private void initializeTimeField() {
        time = new JTextField("Put the time");

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 5;
        c.gridx = 0;
        c.gridheight = 1;
        c.gridwidth = 2;
        c.ipady = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 0, 0, 10);

        this.add(time, c);

    }

    private void initializeAddButton() {
        addTask = new JButton("Add task");

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 5;
        c.gridx = 2;
        c.gridwidth = 1;
        c.insets = new Insets(10, 0, 0, 10);

        this.add(addTask, c);
    }
}
