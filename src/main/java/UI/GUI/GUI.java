package UI.GUI;
import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame{
    private ButtonPanel btnPanel = new ButtonPanel();
    private TitleBar title;
    private JScrollPane sp;
    private List list = new List();


    private JButton addTask;
    private JButton sortTask;

    public GUI() {
        this.setSize(400, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        title = new TitleBar();
        this.add(title, BorderLayout.NORTH);

        this.add(btnPanel, BorderLayout.SOUTH);


        sp = new JScrollPane(list);
        this.add(sp, BorderLayout.CENTER);

        addTask = btnPanel.getTaskButton();
        addTask.addActionListener(e -> {
            String taskType = this.btnPanel.getTaskType();
            String taskName = this.btnPanel.getTaskName();
            String time = this.btnPanel.getTime();
            list.addRow(taskType, taskName, time);
            revalidate();
        });

        sortTask = btnPanel.getSortTaskButton();
        sortTask.addActionListener(e -> {
           // list.listTask(tasks.sort());
            revalidate();

        });


        this.setVisible(true);
        //this.pack();
    }


}