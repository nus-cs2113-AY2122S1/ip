package userManagement;

import FileManager.FileSaver;
import Parser.GUIParser;
import UI.GUI.ServePage.List;
import UI.GUI.ServePage.ServeGUI;
import UI.GUI.WelcomePage.WelcomeGUI;
import tasks.TaskList;

import javax.swing.*;


public class UserServerGUI {
    private String userName;

    private WelcomeGUI welcomePage;
    private ServeGUI servePage;
    private UserManager userManager;

    private TaskList tasks;
    private JTextField usernameField;

    private JButton sortTaskButton;
    private JButton finishTaskButton;
    private JButton findTaskButton;
    private JButton deleteTaskButton;
    private JButton exitButton;
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
        JButton addTaskButton = servePage.getAddTaskButton();
        JComboBox<String> taskTypeComBox = servePage.getTaskType();
        JTextArea taskNameTextArea = servePage.getTaskNameTextArea();
        JTextField timeTextField = servePage.getTaskTimeTextField();
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
        JButton twoPageTransitionButton = welcomePage.getStartButton();
        twoPageTransitionButton.addActionListener(e -> {
            this.userName = this.usernameField.getText().strip();

            if (userName.isEmpty()) {
                JOptionPane.showMessageDialog(new JFrame(), "username cannot be empty!");
                return;
            }


            try {
                TaskList userTask = userManager.loadUser(userName);
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
            this.guiParser.setTaskJTable(this.tasks);
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
            this.tasks.markAsDone(taskIndex);
            this.taskListJTable.listTask(tasks);
        });
    }

    private void addHandlerForExitButton() {
        this.exitButton.addActionListener(e -> {
            FileSaver saver = new FileSaver(userName);
            saver.save(this.tasks);
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
