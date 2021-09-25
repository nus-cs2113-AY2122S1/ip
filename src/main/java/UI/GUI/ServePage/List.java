package UI.GUI.ServePage;


import tasks.Task;
import tasks.TaskList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;

public class List extends JTable{
    public boolean isCellEditable (int row, int column) {
        return false;
    }

    List() {
        super(0,4);
        this.setSize(new Dimension(500, 440));
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
        this.getColumnModel().getColumn(0).setHeaderValue("task type");
        this.getColumnModel().getColumn(1).setHeaderValue("task name");
        this.getColumnModel().getColumn(2).setHeaderValue("deadline");
        this.getColumnModel().getColumn(3).setHeaderValue("completed");
        this.getColumnModel().getColumn(0).setPreferredWidth(80);
        this.getColumnModel().getColumn(1).setPreferredWidth(200);
        this.getColumnModel().getColumn(2).setPreferredWidth(150);
        this.getColumnModel().getColumn(3).setPreferredWidth(70);
    }




}
