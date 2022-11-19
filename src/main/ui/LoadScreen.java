package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//a panel that asks the user if they would like to load from a save file
public class LoadScreen extends JPanel implements ActionListener {
    private NoteTakingApplication app;

    public LoadScreen(NoteTakingApplication app) {

        super(null);
        this.app = app;
        this.setPreferredSize(new Dimension(400,400));
        this.setBounds(new Rectangle(400,400));

        JLabel label = new JLabel("Load From Save File? Y/N");
        label.setBounds(75,50,250,100);
        label.setFont(new Font("Arial",Font.BOLD,18));
        this.add(label);

        JButton yesButton = new JButton("Yes");
        yesButton.addActionListener(this);
        yesButton.setActionCommand("Y");
        yesButton.setBounds(80,200,100,25);
        this.add(yesButton);

        JButton noButton = new JButton("No");
        noButton.addActionListener(this);
        noButton.setActionCommand("N");
        noButton.setBounds(200,200,100,25);
        this.add(noButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Y")) {
            app.loadFile();
        }
        app.start();
    }
}
