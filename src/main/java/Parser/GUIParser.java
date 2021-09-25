package Parser;

import javax.swing.*;

public class GUIParser {
    private JButton addTaskButton;
    private JButton finkTaskButton;

    private JComboBox taskType;
    private JTextArea taskName;
    private JTextField taskTime;

    public GUIParser(JButton addTaskButton, JButton finkTaskButton, JComboBox taskType,
                     JTextArea taskName, JTextField taskTime) {
        this.addTaskButton = addTaskButton;
        this.finkTaskButton = finkTaskButton;
        this.taskType = taskType;
        this.taskName = taskName;
        this.taskTime = taskTime;
    }



}
