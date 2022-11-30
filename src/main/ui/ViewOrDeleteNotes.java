package ui;

import model.UltCharacter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//a panel that lets the user choose if they would like to add or delete a note
public class ViewOrDeleteNotes extends JPanel implements ActionListener {
    private final UltCharacter character;
    private NoteTakingApplication app;

    public ViewOrDeleteNotes(NoteTakingApplication app, UltCharacter character) {

        super(null);
        this.app = app;
        this.character = character;
        this.setPreferredSize(new Dimension(400,400));
        this.setBounds(new Rectangle(400,400));

        JLabel label = new JLabel("View or Delete Notes?");
        label.setBounds(100,50,200,100);
        label.setFont(new Font("Arial",Font.BOLD,18));
        this.add(label);

        JButton yesButton = new JButton("View");
        yesButton.addActionListener(this);
        yesButton.setActionCommand("V");
        yesButton.setBounds(80,200,100,25);
        this.add(yesButton);

        JButton noButton = new JButton("Delete");
        noButton.addActionListener(this);
        noButton.setActionCommand("D");
        noButton.setBounds(200,200,100,25);
        this.add(noButton);

    }


    // EFFECTS: creates a new frame for viewing or deleting a note
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("V")) {
            app.notesChoice(character);
        }
        if (e.getActionCommand().equals("D")) {
            app.chooseEnemy(character);
        }
    }
}
