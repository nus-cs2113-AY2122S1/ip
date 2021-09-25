package UI.GUI.ServePage;
import Parser.GUIParser;
import tasks.Deadline;
import tasks.TaskList;
import tasks.Todo;
import tasks.Event;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.*;

public class ServeGUI extends JFrame{
    private ButtonPanel btnPanel = new ButtonPanel();
    private TitleBar title;
    private JScrollPane sp;
    private List list = new List();

    private TaskList tasks = new TaskList();

    public ServeGUI () {
        this.setSize(800, 630);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title = new TitleBar();
        this.add(title, BorderLayout.NORTH);
        this.add(btnPanel, BorderLayout.WEST);

        sp = new JScrollPane(list);
        this.add(sp, BorderLayout.CENTER);
        this.setVisible(true);
    }


    public JButton getSortTaskButton() {
        return btnPanel.getSortTaskButton();
    }

    public JButton getFinishTaskButton() {
        return btnPanel.getFinishTaskButton();
    }

    public JButton getFindTaskButton() {
        return btnPanel.getFindTaskButton();
    }

    public JButton getDeleteTaskButton() {
        return btnPanel.getDeleteTaskButton() ;
    }

    public JButton getExitButton() {
        return btnPanel.getExitButton();
    }

    public String getKeyword() {
        return btnPanel.getKeyword();
    }

    public JButton getAddTaskButton() {
        return btnPanel.getAddTaskButton();
    }

    public String getTaskType() {
        return btnPanel.getTaskType();
    }

    public String getTaskName() {
        return btnPanel.getTaskName();
    }

    public String getTaskTime() {
        return btnPanel.getTaskTime();
    }

//        addTask = btnPanel.getTaskButton();
//        addTask.addActionListener(e -> {
//            String taskType = btnPanel.getTaskType(), taskName = btnPanel.getTaskName(), time = btnPanel.getTime();
//
//            switch (taskType) {
//            case "todo":
//                this.tasks.addTask(new Todo(taskName, false));
//                break;
//            case "deadline":
//                this.tasks.addTask(new Deadline(taskName, LocalDate.parse(time), false));
//                break;
//            case "event":
//                this.tasks.addTask(new Event(taskName, LocalDateTime.parse(time), false));
//                break;
//            }
//            list.listTask(tasks);
//        });
//
//        doneTask = btnPanel.getDoneTask();
//        doneTask.addActionListener(e -> {
//            int taskIndex = list.getSelectedRow();
//            this.tasks.markAsDone(taskIndex);
//            list.listTask(tasks);
//        });
//
//        exitButton = btnPanel.getExitButton();
//        exitButton.addActionListener(e -> System.exit(1));
//
//        sortTask = btnPanel.getSortTaskButton();
//        sortTask.addActionListener(e -> {
//            this.tasks.sort();
//            list.listTask(tasks);
//        });
//
//
//        findTask = btnPanel.findTaskButton();
//        findTask.addActionListener(e -> {
//            String keyword = btnPanel.getFindKeyWord();
//            list.listTask(tasks.findTask(keyword));
//        });
}


