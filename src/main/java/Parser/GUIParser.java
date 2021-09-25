package Parser;

import UI.GUI.ServePage.List;
import commands.AddTaskCommand;
import exceptions.TimeException;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.Todo;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class GUIParser {
    private JButton addTaskButton;

    private JComboBox taskType;
    private JTextArea taskName;
    private JTextField taskTime;
    private TaskList tasks;
    private List taskList;

    public GUIParser(JButton addTaskButton, JComboBox taskType,
                     JTextArea taskName, JTextField taskTime, List taskList) {
        this.addTaskButton = addTaskButton;
        this.taskType = taskType;
        this.taskName = taskName;
        this.taskTime = taskTime;
        this.taskList = taskList;

        addHandlerForAddTaskButton();
    }

    public void setTaskList(TaskList tasks) {
        this.tasks = tasks;
        this.taskList.listTask(tasks);
    }

    private void addHandlerForAddTaskButton() {
        addTaskButton.addActionListener(e -> {
            String taskType = this.taskType.getSelectedItem().toString(),
                    taskName = this.taskName.getText(), time = this.taskTime.getText();

            switch (taskType) {
            case "todo":
                this.tasks.addTask(new Todo(taskName, false));
                break;

            case "deadline":
                String[] deadlineSplit = time.split(" ");

                if (deadlineSplit.length == 1) {
                    LocalDate date = LocalDate.parse(time);
                    this.tasks.addTask(new Deadline(taskName, date, false));
                } else {
                    LocalDate date = LocalDate.parse(deadlineSplit[0].strip());
                    LocalTime minute = LocalTime.parse(deadlineSplit[1].strip());
                    this.tasks.addTask(new Deadline(taskName, date, minute, false));
                }
                break;

            case "event":
                String[] split = time.strip().split(" ");
                this.tasks.addTask(new Event(taskName, LocalDate.parse(split[0]),
                        LocalTime.parse(split[1]), false));
                 break;
            }

            this.taskList.listTask(this.tasks);
            this.taskName.setText("Put the task name here");
            this.taskTime.setText("Put the time");
        });
    }




}
