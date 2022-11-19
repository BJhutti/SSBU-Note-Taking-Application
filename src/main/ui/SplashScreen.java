package ui;

import javax.swing.*;

//a screen that briefly displays the logo for super smash brothers
public class SplashScreen extends JPanel {

    private NoteTakingApplication app;

    public SplashScreen() {
        ImageIcon icon = new ImageIcon("./resources/logo.jpg");
        JLabel iconLabel = new JLabel(icon);
        this.add(iconLabel);
    }

}
