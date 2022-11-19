package ui;

import model.Enemy;
import model.Notes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//a panel that lets the user create a new note
public class CreateNote extends JPanel implements ActionListener {

    private JTextField field = new JTextField();
    private NoteTakingApplication app;
    private Enemy enemy;
    private int index;
    private String para;
    private String title;

    public CreateNote(Enemy enemy, int index, NoteTakingApplication app) {
        super(null);
        this.enemy = enemy;
        this.app = app;
        this.index = index;
        this.setPreferredSize(new Dimension(400, 400));
        this.setBounds(new Rectangle(400, 400));

        JLabel label = new JLabel("Enter Title of the Note");
        label.setBounds(100, 50, 200, 100);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(label);

        JButton yesButton = new JButton("Confirm");
        yesButton.addActionListener(this);
        yesButton.setActionCommand("Confirm");
        yesButton.setBounds(80, 200, 100, 25);
        this.add(yesButton);

        this.field.setBounds(200, 200, 100, 25);
        this.add(field);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Confirm")) {
            title = field.getText();
            para();
        }
        if (e.getActionCommand().equals("Confirm2")) {
            para = field.getText();
            enemy.addNoteToList(new Notes(title, para));
            app.accessCharacter(index, app.getData().getCharacters().get(index));
        }
    }

    private void para() {
        this.removeAll();
        revalidate();
        repaint();
        JLabel label = new JLabel("Enter Notes");
        label.setBounds(150, 50, 150, 100);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(label);

        JButton yesButton = new JButton("Confirm");
        yesButton.addActionListener(this);
        yesButton.setActionCommand("Confirm2");
        yesButton.setBounds(80, 200, 100, 25);
        this.add(yesButton);

        this.field.setBounds(200, 200, 100, 25);
        this.add(field);
    }
}
