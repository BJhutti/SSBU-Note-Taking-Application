package ui;

import model.Enemy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//a panel that lets the user choose to delete a specific note
public class DeleteNote extends JPanel implements ActionListener {

    private Enemy enemy;
    private NoteTakingApplication app;

    public DeleteNote(Enemy enemy, NoteTakingApplication app) {
        super(null);
        this.app = app;
        this.enemy = enemy;
        this.setPreferredSize(new Dimension(400, 400));
        this.setBounds(new Rectangle(400, 400));

        JLabel label = new JLabel("Choose to Delete");
        label.setBounds(150, 50, 150, 100);
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
        int index = Integer.parseInt(e.getActionCommand());
        this.removeAll();
        revalidate();
        repaint();
        deleteNote(index);
        app.start();
    }

    //MODIFIES: app
    //EFFECTS: deletes a specific note within enemy
    private void deleteNote(int index) {
        enemy.getListOfNotes().remove(index);
    }
}
