package UI.GUI.ServePage;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitleBar extends JPanel {
    JLabel titleText = new JLabel("Duke");

    TitleBar () {
        this.setPreferredSize(new Dimension(400, 80));

        titleText.setFont(new Font("Mv Boli", Font.BOLD, 20));
        titleText.setPreferredSize(new Dimension(200, 80));
        titleText.setHorizontalTextPosition(JLabel.CENTER);
        titleText.setBackground(Color.blue);
        this.add(titleText);
    }

}
