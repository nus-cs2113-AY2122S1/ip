package UI.GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class ButtonPanel extends JPanel{
    private JButton addTask;
    private JButton sortTask;
    private JButton finishTask;

    private JComboBox taskType;
    private JTextField taskName;
    private JTextField time;

    ButtonPanel() {
        this.setPreferredSize(new Dimension(400, 60));
        this.setBackground(Color.red);

        taskType = new JComboBox(new String[] {"todo", "deadline", "event"});
        this.add(taskType);

        taskName = new JTextField("Put the task name here", 10);
        this.add(taskName);

        time = new JTextField("Put the time", 10);
        this.add(time);

        addTask = new JButton("Add Task");
        this.add(addTask);

        sortTask = new JButton("Sort Task");
        this.add(sortTask);

        finishTask = new JButton("Finish");
        this.add(finishTask);
        
    }


    public JButton getTaskButton() {
        return this.addTask;
    }

    public JButton getSortTaskButton () {
        return this.sortTask;
    }

    public String getTaskType () {
        return this.taskType.getSelectedItem().toString();
    }

    public String getTaskName() {
        return this.taskName.getText();
    }

    public String getTime() {
        return this.time.getText();
    }
}
