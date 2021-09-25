package Parser;

import UI.GUI.ServePage.List;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.Todo;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class GUIParser {
    private JButton addTaskButton;
    private JButton finkTaskButton;

    private JComboBox taskType;
    private JTextArea taskName;
    private JTextField taskTime;
    private TaskList tasks;
    private List taskList;

    public GUIParser(JButton addTaskButton, JButton finkTaskButton, JComboBox taskType,
                     JTextArea taskName, JTextField taskTime, List taskList, TaskList tasks) {
        this.addTaskButton = addTaskButton;
        this.finkTaskButton = finkTaskButton;
        this.taskType = taskType;
        this.taskName = taskName;
        this.taskTime = taskTime;
        this.tasks = tasks;
        this.taskList = taskList;

        addHandlerForAddTaskButton();
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
                this.tasks.addTask(new Deadline(taskName, LocalDate.parse(time), false));
                break;

            case "event":
                this.tasks.addTask(new Event(taskName, LocalDateTime.parse(time), false));
                 break;
            }

            this.taskList.listTask(this.tasks);

        });
    }




}
