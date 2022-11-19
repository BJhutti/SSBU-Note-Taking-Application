package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//A panel that lets the user choose which character they want to access
public class CharacterSelectMenu extends JPanel implements ActionListener {

    private NoteTakingApplication app;

    public CharacterSelectMenu(NoteTakingApplication app) {
        super(null);
        this.app = app;
        this.setPreferredSize(new Dimension(400, 400));
        this.setBounds(new Rectangle(400, 400));

        JLabel label = new JLabel("Character Menu");
        label.setBounds(125, 50, 150, 100);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(label);

        JPanel panel = new JPanel();
        panel.setBounds(0, 200, 400, 200);
        for (int i = 0; i < app.getData().getCharacters().size(); i++) {
            JButton button = new JButton(app.getData().getCharacters().get(i).getName());
            button.addActionListener(this);
            button.setActionCommand(Integer.toString(i));
            panel.add(button);
        }
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = Integer.parseInt(e.getActionCommand());
        app.accessCharacter(index, app.getData().getCharacters().get(index));
    }
}