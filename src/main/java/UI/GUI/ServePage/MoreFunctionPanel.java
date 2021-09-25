package UI.GUI.ServePage;

import javax.swing.*;
import java.awt.*;

public class MoreFunctionPanel extends JPanel {
    private JButton sortTask;
    private JButton finishTask;
    private JButton findTask;
    private JButton deleteTask;
    private JButton exit;
    private JTextField findKeyword;
    private JLabel moreFunction;
    private GridBagConstraints c = new GridBagConstraints();

    MoreFunctionPanel() {
        this.setPreferredSize(new Dimension(300, 260));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setLayout(new GridBagLayout());
        initialComponent();
    }


    public JButton getSortTaskButton() {
        return this.sortTask;
    }

    public JButton getFinishTaskButton() {
        return this.finishTask;
    }

    public JButton getFindTaskButton() {
        return this.findTask;
    }

    public JButton getDeleteTaskButton() {
        return this.deleteTask;
    }

    public JButton getExitButton() {
        return this.exit;
    }

    public JTextField getKeywordTextField () {
        return this.findKeyword;
    }

    private void initialComponent() {
        initializeMoreLabel();
        initialSortButton();
        initialDeleteButton();
        initialCompleteButton();
        initialFindField();
        initialFindButton();
        initialExitButton();
    }

    private void initializeMoreLabel() {
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.insets = new Insets(5, 0, 0, 5);

        moreFunction = new JLabel("More >>");
        moreFunction.setPreferredSize(new Dimension(250, 40));
        moreFunction.setFont(new Font("Mv Boli", Font.BOLD, 16));
        moreFunction.setForeground(Color.orange);

        this.add(moreFunction, c);
    }

    private void initialSortButton() {
        sortTask = new JButton("Sort Task");

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;

        this.add(sortTask, c);
    }

    private void initialDeleteButton() {
        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 3;

        deleteTask = new JButton("Delete Task");
        this.add(deleteTask, c);
    }

    private void initialCompleteButton() {
        c.gridy = 3;
        c.gridx = 0;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        finishTask = new JButton("Complete Task");
        this.add(finishTask, c);
    }

    private void initialFindField() {
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.ipadx = 170;

        findKeyword = new JTextField();
        this.add(findKeyword, c);
    }

    private void initialFindButton() {
        c.ipadx = 0;
        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;

        findTask = new JButton("Find task");
        this.add(findTask, c);
    }

    private void initialExitButton() {
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;

        exit = new JButton("Exit");
        this.add(exit, c);
    }

}
