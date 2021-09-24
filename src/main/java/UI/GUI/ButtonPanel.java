package UI.GUI;

import java.awt.*;

import javax.swing.*;

public class ButtonPanel extends JPanel{
    private JPanel addTaskPanel;
    private JPanel explorePanel;
    private JButton addTask;

    private JTextField findKeyword;
    private JButton sortTask;
    private JButton finishTask;
    private JButton findTask;
    private JButton deleteTask;
    private JButton exit;

    private JLabel addTaskHint;
    private JLabel moreFunction;

    private JComboBox taskType;
    private JTextArea taskName;
    private JTextField time;

    private GridBagConstraints constraints = new GridBagConstraints();
    ButtonPanel() {
        this.setPreferredSize(new Dimension(300, 80));
        this.setLayout(new GridBagLayout());

        addTaskPanel = new JPanel();
        addTaskPanel.setPreferredSize(new Dimension(300, 260));
        addTaskPanel.setBorder(BorderFactory.createEmptyBorder());
        explorePanel = new JPanel();
        explorePanel.setPreferredSize(new Dimension(300, 260));
        explorePanel.setBorder(BorderFactory.createEmptyBorder());

        GridBagConstraints c = new GridBagConstraints();

        this.add(addTaskPanel, c);

        c.gridy = 1;
        this.add(explorePanel, c);

        initComponent1();
        initComponent2();
    }



    private void initComponent1() {
        addTaskPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.HORIZONTAL;

        addTaskHint = new JLabel("Create New Task");
        taskType = new JComboBox(new String[] {"todo", "deadline", "event"});
        taskName = new JTextArea("Put the task name here");
        time = new JTextField("Put the time");
        addTask = new JButton("Add task");

        addTaskHint.setPreferredSize(new Dimension(250, 40));
        addTaskHint.setFont(new Font("Mv Boli", Font.BOLD, 16));
        addTaskHint.setForeground(Color.orange);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.ipadx = 20;
        addTaskPanel.add(addTaskHint, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.ipady = 30;
        addTaskPanel.add(taskType, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 3;
        c.ipady = 90;
        addTaskPanel.add(taskName, c);

        c.gridy = 5;
        c.gridx = 0;
        c.gridheight = 1;
        c.gridwidth = 2;
        c.ipady = 0;
        c.insets = new Insets(10, 0, 0, 10);
        addTaskPanel.add(time, c);

        c.gridx = 2;
        c.gridwidth = 1;
        addTaskPanel.add(addTask, c);
    }


    private void initComponent2() {
        explorePanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        c.ipadx = 60;
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridwidth = 2;
        moreFunction = new JLabel("More >>");
        moreFunction.setPreferredSize(new Dimension(250, 40));
        moreFunction.setFont(new Font("Mv Boli", Font.BOLD, 16));
        moreFunction.setForeground(Color.orange);
        explorePanel.add(moreFunction, c);

        c.gridwidth = 3;
        c.gridy = 1;
        sortTask = new JButton("Sort Task");
        explorePanel.add(sortTask, c);

        c.gridy = 2;
        deleteTask = new JButton("Delete Task");
        explorePanel.add(deleteTask, c);

        c.gridy = 3;
        finishTask = new JButton("Complete Task");
        explorePanel.add(finishTask, c);

        c.gridy = 4;
        c.gridwidth = 2;
        findKeyword = new JTextField();
        explorePanel.add(findKeyword, c);

        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 1;
        findTask = new JButton("Find task");
        explorePanel.add(findTask, c);

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 3;
        exit = new JButton("Exit");
        explorePanel.add(exit, c);

    }

        

    public JButton getTaskButton() {
        return this.addTask;
    }
//
//    public JButton getSortTaskButton () {
//        return this.sortTask;
//    }
//
    public String getTaskType () {
        return this.taskType.getSelectedItem().toString();
    }

    public String getTaskName() {
        return this.taskName.getText();
    }

    public String getTime() {
        return this.time.getText();
    }

    public JButton getDoneTask() {
        return this.finishTask;
    }

    public JButton getExitButton() {
        return this.exit;
    }


}
