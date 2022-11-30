package ui;

import model.UltCharacter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//A panel that shows what options you can do with a specific character
public class CharacterMenu extends JPanel implements ActionListener {

    private NoteTakingApplication app;
    private UltCharacter character;
    private int index;

    public CharacterMenu(NoteTakingApplication app, UltCharacter character, int index) {
        super(null);
        this.index = index;
        this.character = character;
        this.app = app;
        this.setPreferredSize(new Dimension(400,400));
        this.setBounds(new Rectangle(400,400));

        JLabel label = new JLabel("Character Menu");
        label.setBounds(125,50,150,100);
        label.setFont(new Font("Arial",Font.BOLD,18));
        this.add(label);
        JButton button = new JButton("Add enemy");
        button.setBounds(5,200,100,25);
        button.addActionListener(this);
        button.setActionCommand("1");
        this.add(button);
        placeButtons();
    }

    private void placeButtons() {
        JButton button2 = new JButton("Add note to enemy");
        button2.setBounds(110,200,140,25);
        button2.addActionListener(this);
        button2.setActionCommand("2");
        this.add(button2);
        JButton button3 = new JButton("Delete character");
        button3.setBounds(255,200,130,25);
        button3.addActionListener(this);
        button3.setActionCommand("3");
        this.add(button3);
        JButton button4 = new JButton("Manage Notes");
        button4.setBounds(70,250,125,25);
        button4.addActionListener(this);
        button4.setActionCommand("4");
        this.add(button4);
        JButton button5 = new JButton("Return");
        button5.setBounds(210,250,100,25);
        button5.addActionListener(this);
        button5.setActionCommand("5");
        this.add(button5);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("1")) {
            app.addEnemyToCharacter(character, index);
        }
        if (e.getActionCommand().equals("2")) {
            if (character.getListOfEnemyCharacters().isEmpty()) {
                JLabel errorLabel = new JLabel("Error, no enemies");
                errorLabel.setBounds(140,100,150,100);
                this.add(errorLabel);
                this.repaint();
            } else {
                app.addNoteToExistingEnemy(index, character);
            }
        }
        if (e.getActionCommand().equals("3")) {
            app.delete(index);
            app.start();
        }
        if (e.getActionCommand().equals("4")) {
            app.viewOrDeleteNotes(character);
        }
        if (e.getActionCommand().equals("5")) {
            app.start();
        }
    }
}
