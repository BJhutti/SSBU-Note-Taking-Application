package ui;

import model.Enemy;
import model.UltCharacter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//a panel that lets the user create a new enemy
public class CreateEnemy extends JPanel implements ActionListener {
    private NoteTakingApplication app;
    private UltCharacter character;
    JTextField field = new JTextField();
    private Enemy enemy;
    private int index;

    public CreateEnemy(UltCharacter character, int index, NoteTakingApplication app) {
        super(null);
        this.character = character;
        this.app = app;
        this.index = index;
        this.setPreferredSize(new Dimension(400, 400));
        this.setBounds(new Rectangle(400, 400));

        JLabel label = new JLabel("Enter Name of Enemy");
        label.setBounds(100, 50, 200, 100);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(label);

        JButton yesButton = new JButton("Confirm");
        yesButton.addActionListener(this);
        yesButton.setActionCommand("Confirm");
        yesButton.setBounds(80, 200, 100, 25);
        this.add(yesButton);

        field.setBounds(200, 200, 100, 25);
        this.add(field);
    }

    // EFFECTS: creates a new frame for adding an enemy, or note
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Confirm")) {
            enemy = new Enemy(field.getText());
            character.addEnemyToList(enemy);
            notes();
        }
        if (e.getActionCommand().equals("Y")) {
            this.removeAll();
            revalidate();
            repaint();
            createNote(enemy, app, index);
        }
        if (e.getActionCommand().equals("N")) {
            app.accessCharacter(index, character);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new frame for adding a note
    private void createNote(Enemy enemy, NoteTakingApplication app, int index) {
        JPanel panel = new CreateNote(enemy, index, app);
        this.add(panel);
    }

    // MODIFIES: this
    // EFFECTS: creates a new frame for asking the user to add a note or not
    public void notes() {
        this.removeAll();
        revalidate();
        repaint();
        JLabel label = new JLabel("Create note?");
        label.setBounds(130,50,220,100);
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
}
