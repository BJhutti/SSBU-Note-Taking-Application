package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//a panel that lets the user create a new character
public class CreateCharacter extends JPanel implements ActionListener {

    private NoteTakingApplication app;
    private JTextField field = new JTextField();

    public CreateCharacter(NoteTakingApplication app) {
        super(null);
        this.app = app;
        this.setPreferredSize(new Dimension(400, 400));
        this.setBounds(new Rectangle(400, 400));

        JLabel label = new JLabel("Enter Name of Character");
        label.setBounds(75, 50, 225, 100);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(label);

        JButton yesButton = new JButton("Confirm");
        yesButton.addActionListener(this);
        yesButton.setActionCommand("Y");
        yesButton.setBounds(80, 200, 100, 25);
        this.add(yesButton);

        this.field.setBounds(200, 200, 100, 25);
        this.add(field);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = field.getText();
        app.addCharacter(name);
        app.start();
    }
}
