package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//a panel that represents the main menu of the app, and has 3 choices
public class MainScreen extends JPanel implements ActionListener {
    private NoteTakingApplication app;

    public MainScreen(NoteTakingApplication app) {
        super(null);
        this.app = app;
        this.setPreferredSize(new Dimension(400,400));
        this.setBounds(new Rectangle(400,400));

        JLabel label = new JLabel("Main Menu");
        label.setBounds(150,50,150,100);
        label.setFont(new Font("Arial",Font.BOLD,18));
        this.add(label);

        JButton button = new JButton("New Char");
        button.setBounds(30,200,100,25);
        button.addActionListener(this);
        button.setActionCommand("1");
        this.add(button);

        JButton button2 = new JButton("Access Char");
        button2.setBounds(140,200,120,25);
        button2.addActionListener(this);
        button2.setActionCommand("2");
        this.add(button2);

        JButton button3 = new JButton("Quit/Save");
        button3.setBounds(270,200,100,25);
        button3.addActionListener(this);
        button3.setActionCommand("3");
        this.add(button3);
    }

    // EFFECTS: creates a new frame given the input of the user for which menu they would like to go to
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("1")) {
            app.createNewCharacter();
        }
        if (e.getActionCommand().equals("2")) {
            if (app.getData().getCharacters().size() == 0) {
                JLabel errorLabel = new JLabel("Error, no characters");
                errorLabel.setBounds(140,100,150,100);
                this.add(errorLabel);
                this.repaint();
            } else {
                app.displayCharacters();
            }
        }
        if (e.getActionCommand().equals("3")) {
            app.quitQuestion();
        }
    }
}
