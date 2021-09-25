package userManagement;

import UI.GUI.ServePage.ServeGUI;
import UI.GUI.WelcomePage.WelcomeGUI;

import javax.swing.*;

public class UserServerGUI {
    private WelcomeGUI welcomePage;
    private ServeGUI servePage;
    private JButton twoPageTransitionButton;

    public UserServerGUI() {
        welcomePage = new WelcomeGUI();
        welcomePage.setVisible(true);
        servePage = new ServeGUI();
        servePage.setVisible(false);

        addHandlerForTransitionButton();
    }

    private void addHandlerForTransitionButton () {
        this.twoPageTransitionButton = welcomePage.getStartButton();
        twoPageTransitionButton.addActionListener(e -> {
            welcomePage.setVisible(false);
            servePage.setVisible(true);
        });
    }


}
