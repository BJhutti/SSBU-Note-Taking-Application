package ui;

import model.UltCharacter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//a panel that lists enemies to view notes for
public class ListEnemiesForNotes extends JPanel implements ActionListener {

    private UltCharacter character;
    private NoteTakingApplication app;

    public ListEnemiesForNotes(UltCharacter character, NoteTakingApplication app) {
        super(null);
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
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = Integer.parseInt(e.getActionCommand());
        app.viewNotes(character.getListOfEnemyCharacters().get(index), character, index);
    }
}
