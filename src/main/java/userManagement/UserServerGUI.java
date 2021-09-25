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
    private UserManager userManager;

    private TaskList tasks;
    private JButton twoPageTransitionButton;
    private JTextField usernameField;

    private JButton sortTaskButton;
    private JButton finishTaskButton;
    private JButton findTaskButton;
    private JButton deleteTaskButton;
    private JButton exitButton;
    private JButton addTaskButton;
    private JComboBox taskTypeComBox;
    private JTextArea taskNameTextArea;
    private JTextField timeTextField;
    private JTextField findKeyword;
    private List taskListJTable;

    private GUIParser guiParser;
    public UserServerGUI(UserManager userManager) {
        this.userManager = userManager;
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
        this.taskTypeComBox = servePage.getTaskType();
        this.taskNameTextArea = servePage.getTaskNameTextArea();
        this.timeTextField = servePage.getTaskTimeTextField();
        this.findKeyword = servePage.getKeywordJTextField();
        this.taskListJTable = servePage.getTable();
        this.usernameField = welcomePage.getUsername();

        this.guiParser = new GUIParser(addTaskButton, taskTypeComBox, taskNameTextArea, timeTextField, taskListJTable);

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
            String userName = this.usernameField.getText();
            try {
                TaskList userTask = userManager.loadUser(userName);
                System.out.println(userTask);
                if (userTask != null) {
                    this.tasks = userTask;
                } else {
                    this.tasks = new TaskList();
                }
            } catch (Exception exception) {
                this.tasks = new TaskList();
            }

            welcomePage.setVisible(false);
            servePage.setVisible(true);
            this.guiParser.setTaskList(this.tasks);
        });
    }


    private void addHandlerForSortButton() {
        this.sortTaskButton.addActionListener(e -> {
            this.tasks.sort();
            taskListJTable.listTask(this.tasks);
        });
    }

    private void addHandlerForDeleteButton() {
        this.deleteTaskButton.addActionListener(e -> {
            int taskIndex = taskListJTable.getSelectedRow();
            this.tasks.deleteTask(taskIndex + 1);
            this.taskListJTable.listTask(tasks);
        });

    }

    private void addHandlerForFinishButton() {
        this.finishTaskButton.addActionListener(e -> {
            int taskIndex = taskListJTable.getSelectedRow();
            this.tasks.markAsDone(taskIndex + 1);
            this.taskListJTable.listTask(tasks);
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
            this.taskListJTable.listTask(this.tasks.findTask(keyword));
            this.findKeyword.setText("");
        });
    }
}
