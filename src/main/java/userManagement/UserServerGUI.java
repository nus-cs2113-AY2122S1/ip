package userManagement;

import Parser.GUIParser;
import UI.GUI.ServePage.List;
import UI.GUI.ServePage.ServeGUI;
import UI.GUI.WelcomePage.WelcomeGUI;
import tasks.TaskList;

import javax.swing.*;

public class UserServerGUI {
    private WelcomeGUI welcomePage;
    private ServeGUI servePage;
    private TaskList tasks = new TaskList();
    private JButton twoPageTransitionButton;

    private JButton sortTaskButton;
    private JButton finishTaskButton;
    private JButton findTaskButton;
    private JButton deleteTaskButton;
    private JButton exitButton;
    private JButton addTaskButton;
    private JComboBox taskType;
    private JTextArea taskName;
    private JTextField time;
    private JTextField findKeyword;
    private List taskList;

    private GUIParser guiParser;
    public UserServerGUI() {
        welcomePage = new WelcomeGUI();
        welcomePage.setVisible(true);
        servePage = new ServeGUI();
        servePage.setVisible(false);

        this.sortTaskButton = servePage.getSortTaskButton();
        this.findTaskButton = servePage.getFindTaskButton();
        this.finishTaskButton = servePage.getFinishTaskButton();
        this.deleteTaskButton = servePage.getDeleteTaskButton();
        this.exitButton = servePage.getExitButton();
        this.addTaskButton = servePage.getAddTaskButton();
        this.taskType = servePage.getTaskType();
        this.taskName = servePage.getTaskNameTextArea();
        this.time = servePage.getTaskTimeTextField();
        this.findKeyword = servePage.getKeywordJTextField();
        this.taskList = servePage.getTable();

        this.guiParser = new GUIParser(addTaskButton, findTaskButton, taskType, taskName, time, taskList, tasks);

        addHandlerForTransitionButton();
        addHandlerForSortButton();
        addHandlerForDeleteButton();
        addHandlerForFinishButton();
        addHandlerForExitButton();
        addHandlerForFindButton();
    }

    private void addHandlerForTransitionButton () {
        this.twoPageTransitionButton = welcomePage.getStartButton();
        twoPageTransitionButton.addActionListener(e -> {
            welcomePage.setVisible(false);
            servePage.setVisible(true);
        });
    }


    private void addHandlerForSortButton() {
        this.sortTaskButton.addActionListener(e -> {
            this.tasks.sort();
            taskList.listTask(this.tasks);
        });
    }

    private void addHandlerForDeleteButton() {
        this.deleteTaskButton.addActionListener(e -> {
            int taskIndex = taskList.getSelectedRow();
            this.tasks.deleteTask(taskIndex + 1);
            this.taskList.listTask(tasks);
        });

    }

    private void addHandlerForFinishButton() {
        this.finishTaskButton.addActionListener(e -> {
            int taskIndex = taskList.getSelectedRow();
            this.tasks.markAsDone(taskIndex + 1);
            this.taskList.listTask(tasks);
        });
    }

    private void addHandlerForExitButton() {
        this.exitButton.addActionListener(e -> {
            System.exit(1);
        });
    }

    private void addHandlerForFindButton() {
        this.findTaskButton.addActionListener(e -> {
            String keyword = this.findKeyword.getText().strip();
            this.taskList.listTask(this.tasks.findTask(keyword));
        });
    }
}
