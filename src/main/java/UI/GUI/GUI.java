package UI.GUI;
import Parser.GUIParser;
import tasks.Deadline;
import tasks.TaskList;
import tasks.Todo;
import tasks.Event;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.*;

public class GUI extends JFrame{
    private ButtonPanel btnPanel = new ButtonPanel();
    private TitleBar title;
    private JScrollPane sp;
    private List list = new List();
    private GUIParser parser = new GUIParser();

    private JButton addTask;
    private JButton sortTask;
    private JButton doneTask;
    private JButton exitButton;
    private JButton findTask;

    private TaskList tasks = new TaskList();

    public GUI() {
        this.setSize(800, 630);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title = new TitleBar();
        this.add(title, BorderLayout.NORTH);
        this.add(btnPanel, BorderLayout.WEST);

        sp = new JScrollPane(list);
        this.add(sp, BorderLayout.CENTER);

        addTask = btnPanel.getTaskButton();
        addTask.addActionListener(e -> {
            String taskType = btnPanel.getTaskType(), taskName = btnPanel.getTaskName(), time = btnPanel.getTime();

            switch (taskType) {
            case "todo":
                this.tasks.addTask(new Todo(taskName, false));
                break;
            case "deadline":
                this.tasks.addTask(new Deadline(taskName, LocalDate.parse(time), false));
                break;
            case "event":
                this.tasks.addTask(new Event(taskName, LocalDateTime.parse(time), false));
                break;
            }
            list.listTask(tasks);
        });

        doneTask = btnPanel.getDoneTask();
        doneTask.addActionListener(e -> {
            int taskIndex = list.getSelectedRow();
            this.tasks.markAsDone(taskIndex);
            list.listTask(tasks);
        });

        exitButton = btnPanel.getExitButton();
        exitButton.addActionListener(e -> System.exit(1));

        sortTask = btnPanel.getSortTaskButton();
        sortTask.addActionListener(e -> {
            this.tasks.sort();
            list.listTask(tasks);
        });


        findTask = btnPanel.findTaskButton();
        findTask.addActionListener(e -> {
            String keyword = btnPanel.getFindKeyWord();
            list.listTask(tasks.findTask(keyword));
        });
        this.setVisible(true);
        //this.pack();
    }
}


