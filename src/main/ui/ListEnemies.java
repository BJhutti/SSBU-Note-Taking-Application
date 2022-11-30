package ui;

import model.Enemy;
import model.UltCharacter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//a panel that shows the list of enemies a user can create a note for
public class ListEnemies extends JPanel implements ActionListener {

    private NoteTakingApplication app;
    private Enemy enemy;
    private int index;
    private UltCharacter character;

    public ListEnemies(UltCharacter character, int index, NoteTakingApplication app) {
        super(null);
        this.index = index;
        this.app = app;
        this.character = character;
        this.setPreferredSize(new Dimension(400, 400));
        this.setBounds(new Rectangle(400, 400));

        JLabel label = new JLabel("Enemy Menu");
        label.setBounds(150, 50, 150, 100);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(label);

        JPanel panel = new JPanel();
        panel.setBounds(0, 200, 400, 200);
        for (int i = 0; i < character.getListOfEnemyCharacters().size(); i++) {
            JButton button = new JButton(character.getListOfEnemyCharacters().get(i).getName());
            button.addActionListener(this);
            button.setActionCommand(Integer.toString(i));
            panel.add(button);
        }
        System.out.println(character.getListOfEnemyCharacters().size());
        add(panel);
        System.out.println("test");
    }

    // MODIFIES: this
    // EFFECTS: shows a new menu for creating a note
    @Override
    public void actionPerformed(ActionEvent e) {
        int index2 = Integer.parseInt(e.getActionCommand());
        enemy = character.getListOfEnemyCharacters().get(index2);
        createNote(enemy, index);
    }


    //MODIFIES: enemy
    //EFFECTS: creates a new note in enemy
    public void createNote(Enemy enemy, int index) {
        app.createNote(enemy, index);
    }
}
