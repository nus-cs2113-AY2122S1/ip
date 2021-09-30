package UI.GUI.ServePage;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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

    public JButton getFindTaskButton() {
        return explorePanel.getFindTaskButton();
    }

    public JButton getDeleteTaskButton() {
        return explorePanel.getDeleteTaskButton() ;
    }

    public JButton getExitButton() {
        return explorePanel.getExitButton();
    }

    public JTextField getKeywordJTextField () {
        return explorePanel.getKeywordTextField();
    }

    public JButton getAddTaskButton() {
        return addTaskPanel.getAddTaskButton();
    }

    public JComboBox<String> getTaskTypeJCombox () {
        return addTaskPanel.getTaskTypeJCombox();
    }

    public JTextArea getTaskNameTextArea () {
        return addTaskPanel.getTaskNameTextArea();
    }

    public JTextField getTaskTimeTextField () {
        return addTaskPanel.getTaskTimeTextArea();
    }

    public JButton getFinishTaskButton() {
        return explorePanel.getFinishTaskButton();
    }
}
