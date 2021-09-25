package Parser;

import UI.GUI.ServePage.List;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.Todo;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class GUIParser {
    private JButton addTaskButton;

    private JComboBox<String> taskTypeComboBox;
    private JTextArea taskNameTextArea;
    private JTextField taskTimeTextField;
    private TaskList tasks;
    private List taskJTable;

    public GUIParser(JButton addTaskButton, JComboBox<String> taskType,
                     JTextArea taskName, JTextField taskTime, List taskList) {
        this.addTaskButton = addTaskButton;
        this.taskTypeComboBox = taskType;
        this.taskNameTextArea = taskName;
        this.taskTimeTextField = taskTime;
        this.taskJTable = taskList;

        addHandlerForAddTaskButton();
    }

    public void setTaskJTable (TaskList tasks) {
        this.tasks = tasks;
        this.taskJTable.listTask(tasks);
    }

    private void addHandlerForAddTaskButton() {
        addTaskButton.addActionListener(e -> {
            String taskType = this.taskTypeComboBox.getSelectedItem().toString(),
                    taskName = this.taskNameTextArea.getText(), time = this.taskTimeTextField.getText();

            try {
                switch (taskType) {
                case "todo":
                    this.tasks.addTask(new Todo(taskName, false));
                    break;

                case "deadline":
                    String[] deadlineSplit = time.split(" ");

                    if (deadlineSplit.length == 1) {
                        LocalDate date = LocalDate.parse(time);
                        if (! date.isAfter(LocalDate.now())) {
                            throw new Exception();
                        }
                        this.tasks.addTask(new Deadline(taskName, date, false));
                    } else {
                        LocalDate date = LocalDate.parse(deadlineSplit[0].strip());
                        LocalTime minute = LocalTime.parse(deadlineSplit[1].strip());
                        LocalDateTime deadline = LocalDateTime.of(date, minute);

                        if (! deadline.isAfter(LocalDateTime.now())) {
                            throw new Exception();
                        }

                        this.tasks.addTask(new Deadline(taskName, deadline,  false));
                    }
                    break;

                case "event":
                    String[] split = time.strip().split(" ");
                    LocalDateTime eventTime = LocalDateTime.of(LocalDate.parse(split[0]), LocalTime.parse(split[1]));

                    if (! eventTime.isAfter(LocalDateTime.now())) {
                        throw new Exception();
                    }
                    this.tasks.addTask(new Event(taskName, eventTime,  false));
                    break;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(new JFrame(), "Time format is wrong or it is a past time. \n" +
                        "It should be YYYY-MM-DD or YYYY-MM-DD HH:MM for deadline type, " +
                        "YYYY-MM-DD HH:MM for event type");
            }

            this.taskJTable.listTask(this.tasks);
            this.taskNameTextArea.setText("Put the task name here");
            this.taskTimeTextField.setText("Put the time");
        });
    }




}
