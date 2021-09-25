package UI.GUI;


import UI.GUI.ServePage.ServeGUI;
import UI.GUI.WelcomePage.WelcomeGUI;

import javax.swing.*;

public class GUI {
    private WelcomeGUI welcomePage;
    private ServeGUI servePage;
    private JButton twoPageTransitionButton;

    public GUI() {
        welcomePage = new WelcomeGUI();
        welcomePage.setVisible(true);
        servePage = new ServeGUI();
        servePage.setVisible(false);

        this.twoPageTransitionButton = welcomePage.getStartButton();
        twoPageTransitionButton.addActionListener(e -> {
            welcomePage.setVisible(false);
            servePage.setVisible(true);
        });
    }

}
