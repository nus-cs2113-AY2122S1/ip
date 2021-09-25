package UI.GUI;


import tasks.Task;
import tasks.TaskList;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class List extends JTable{
    public boolean isCellEditable (int row, int column) {
        return false;
    };

    List() {
        super(0,4);
        this.setSize(new Dimension(500, 440));
        this.getColumnModel().getColumn(0).setHeaderValue("task type");
        this.getColumnModel().getColumn(1).setHeaderValue("task name");
        this.getColumnModel().getColumn(2).setHeaderValue("deadline");
        this.getColumnModel().getColumn(3).setHeaderValue("completed");
    }

    

    public void listTask(TaskList tasks) {
        DefaultTableModel model = new DefaultTableModel(0, 4);
        for (int i = 0; i < tasks.getTotalTaskNumber(); i++) {
            Task newTask = tasks.getTask(i);
            String taskType = newTask.getTaskType(), taskName = newTask.getTaskName(),
                    status = newTask.getTaskStatus();
            String time = (taskType.equals("todo"))? "" : newTask.getTime().toString();
            model.addRow(new Object[]{taskType, taskName, time, status});
        }
        this.setModel(model);
    }




}
