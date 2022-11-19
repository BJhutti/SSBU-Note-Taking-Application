package ui;

import model.Enemy;
import model.Notes;
import model.UltCharacter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//a panel that allows the user to view notes
public class ViewNotes extends JPanel implements ActionListener {

    private Enemy enemy;
    private NoteTakingApplication app;
    private UltCharacter character;
    private int index;

    public ViewNotes(Enemy enemy, NoteTakingApplication app, UltCharacter character, int index) {
        super(null);
        this.app = app;
        this.character = character;
        this.index = index;
        this.enemy = enemy;
        this.setPreferredSize(new Dimension(400, 400));
        this.setBounds(new Rectangle(400, 400));

        JLabel label = new JLabel("Notes");
        label.setBounds(170, 50, 150, 100);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(label);

        JPanel panel = new JPanel();
        panel.setBounds(0, 200, 400, 200);
        for (int i = 0; i < enemy.getListOfNotes().size(); i++) {
            JButton button = new JButton(enemy.getListOfNotes().get(i).getTitle());
            button.addActionListener(this);
            button.setActionCommand(Integer.toString(i));
            panel.add(button);
        }
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Return")) {
            app.accessCharacter(index, character);
        }
        this.removeAll();
        revalidate();
        repaint();
        viewNote();
    }

    //EFFECTS: displays the note selected
    private void viewNote() {
        Notes note = enemy.getListOfNotes().get(0);
        JLabel label = new JLabel(note.getTitle());
        label.setBounds(120, 50, 150, 100);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(label);

        JTextArea textArea = new JTextArea(note.getParagraph());
        textArea.setBounds(100, 150, 200, 100);
        this.add(textArea);

        JButton button = new JButton("Return");
        button.setBounds(100, 300, 80, 40);
        button.setActionCommand("Return");
        button.addActionListener(this);
        this.add(button);
    }

}
