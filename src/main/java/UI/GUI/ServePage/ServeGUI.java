package UI.GUI.ServePage;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServeGUI extends JFrame{
    private ButtonPanel btnPanel = new ButtonPanel();
    private List list = new List();

    public ServeGUI () {
        this.setSize(800, 630);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TitleBar title = new TitleBar();
        this.add(title, BorderLayout.NORTH);
        this.add(btnPanel, BorderLayout.WEST);

        JScrollPane sp = new JScrollPane(list);
        this.add(sp, BorderLayout.CENTER);
        this.setVisible(true);
    }


   public List getTable() {
        return this.list;
   }

    public JButton getSortTaskButton() {
        return btnPanel.getSortTaskButton();
    }

    public JButton getFindTaskButton() {
        return btnPanel.getFindTaskButton();
    }

    public JButton getFinishTaskButton() {
        return btnPanel.getFinishTaskButton();
    }

    public JButton getDeleteTaskButton() {
        return btnPanel.getDeleteTaskButton() ;
    }

    public JButton getExitButton() {
        return btnPanel.getExitButton();
    }

    public JTextField getKeywordJTextField () {
        return btnPanel.getKeywordJTextField();
    }

    public JButton getAddTaskButton() {
        return btnPanel.getAddTaskButton();
    }

    public JComboBox<String> getTaskType() {
        return btnPanel.getTaskTypeJCombox();
    }

    public JTextArea getTaskNameTextArea () {
        return btnPanel.getTaskNameTextArea();
    }

    public JTextField getTaskTimeTextField () {
        return btnPanel.getTaskTimeTextField();
    }
}


