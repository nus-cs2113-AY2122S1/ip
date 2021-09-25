package UI.GUI.ServePage;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class ButtonPanel extends JPanel{
    private AddTaskPanel addTaskPanel;
    private MoreFunctionPanel explorePanel;

    ButtonPanel() {
        this.setPreferredSize(new Dimension(300, 80));
        this.setLayout(new GridBagLayout());

        addTaskPanel = new AddTaskPanel();
        addTaskPanel.setPreferredSize(new Dimension(300, 260));
        addTaskPanel.setBorder(BorderFactory.createEmptyBorder());

        explorePanel = new MoreFunctionPanel();
        explorePanel.setPreferredSize(new Dimension(300, 260));
        explorePanel.setBorder(BorderFactory.createEmptyBorder());

        GridBagConstraints c = new GridBagConstraints();
        this.add(addTaskPanel, c);
        c.gridy = 1;
        this.add(explorePanel, c);
    }

    public JButton getSortTaskButton() {
        return explorePanel.getSortTaskButton();
    }

    public JButton getFinishTaskButton() {
        return explorePanel.getFinishTaskButton();
    }

    public JButton getFindTaskButton() {
        return explorePanel.getFindTaskButton();
    }

    public JButton getDeleteTaskButton() {
        return explorePanel.getDeleteTaskButton() ;
    }

    public JButton getExitButton() {
        return explorePanel.getExitButton();
    }

    public String getKeyword() {
        return explorePanel.getKeyword();
    }

    public JButton getAddTaskButton() {
        return addTaskPanel.getAddTaskButton();
    }

    public String getTaskType() {
        return addTaskPanel.getTaskType();
    }

    public String getTaskName() {
        return addTaskPanel.getTaskName();
    }

    public String getTaskTime() {
        return addTaskPanel.getTaskTime();
    }
}
