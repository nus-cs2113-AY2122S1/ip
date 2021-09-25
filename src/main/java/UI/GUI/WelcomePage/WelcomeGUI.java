package UI.GUI.WelcomePage;

import javax.swing.*;
import java.awt.*;

public class WelcomeGUI extends JFrame {
    private JLabel welcome = new JLabel("Welcome to use Duke!");
    private JLabel usernameLabel;
    private JTextField inputArea;
    private JButton startButton;

    public WelcomeGUI () {
        initializeFrame();
        initializeWelcomeTitle();
        initializeUsernameLabel();
        initializeUsernameInput();
        initializeStartButton();
    }


    public JTextField getUsername() {
        return this.inputArea;
    }

    public JButton getStartButton() {
        return this.startButton;
    }

    private void initializeFrame() {
        this.setSize(400, 180);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(false);
    }

    private void initializeWelcomeTitle() {
        welcome.setFont(new Font("Mv Boli", Font.BOLD, 20));
        welcome.setBounds(80, 20, 250, 40);
        this.add(welcome);
    }

    private void initializeUsernameLabel() {
        this.usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(40, 70, 80, 40);
        usernameLabel.setFont(new Font("Mv Boli", Font.PLAIN, 14));
        this.add(usernameLabel);
    }

    private void initializeUsernameInput() {
        this.inputArea = new JTextField(20);
        inputArea.setBounds(140, 70, 200, 40);
        this.add(inputArea);
    }

    private void initializeStartButton() {
        this.startButton = new JButton("Start");
        startButton.setBounds(130, 120, 100, 30);
        startButton.setFont(new Font("Mv Boli", Font.BOLD, 20));
        this.add(startButton);
    }
}
