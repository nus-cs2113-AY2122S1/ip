package UI.GUI;
import Parser.GUIParser;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class GUI extends JFrame{
    private ButtonPanel btnPanel = new ButtonPanel();
    private TitleBar title;
    private JScrollPane sp;
    private List list = new List();
    private GUIParser parser = new GUIParser();

    private JButton addTask;
    private JButton sortTask;
    private JButton doneTask;
    private JButton exitButton;

    public GUI() {
        this.setSize(800, 630);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title = new TitleBar();
        this.add(title, BorderLayout.NORTH);
        this.add(btnPanel, BorderLayout.WEST);

        sp = new JScrollPane(list);
        this.add(sp, BorderLayout.CENTER);

        addTask = btnPanel.getTaskButton();
        addTask.addActionListener(e -> {
            list.addRow(parser.parseTask(btnPanel.getTaskType(), btnPanel.getTaskName(), btnPanel.getTime()));
            revalidate();
        });

        doneTask = btnPanel.getDoneTask();
        doneTask.addActionListener(e -> {list.setDone(list.getSelectedRow());});

        exitButton = btnPanel.getExitButton();
        exitButton.addActionListener(e -> System.exit(1));

        this.setVisible(true);
        //this.pack();
    }
}


//        sortTask = btnPanel.getSortTaskButton();
//        sortTask.addActionListener(e -> {
//           // list.listTask(tasks.sort());
//            revalidate();
//
//        });