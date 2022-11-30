package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//a panel that lets the user choose if they would like to quit or not
public class QuitQuestion extends JPanel implements ActionListener {
    private final NoteTakingApplication app;

    public QuitQuestion(NoteTakingApplication app) {


        super(null);
        this.app = app;
        this.setPreferredSize(new Dimension(400,400));
        this.setBounds(new Rectangle(400,400));

        JLabel label = new JLabel("Quit? Y/N");
        label.setBounds(150,50,150,100);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Y")) {
            quit();
        }
        if (e.getActionCommand().equals("N")) {
            app.start();
        }
        if (e.getActionCommand().equals("Y2")) {
            app.saveFile();
            app.quit();

        }
        if (e.getActionCommand().equals("N2")) {
            app.quit();
        }
    }

    //EFFECTS: resets current panel, and asks user if they would like to save
    public void quit() {
        this.removeAll();
        revalidate();
        repaint();
        JLabel label = new JLabel("Save? Y/N");
        label.setBounds(150,50,150,100);
        label.setFont(new Font("Arial",Font.BOLD,18));
        this.add(label);

        JButton yesButton = new JButton("Yes");
        yesButton.addActionListener(this);
        yesButton.setActionCommand("Y2");
        yesButton.setBounds(80,200,100,25);
        this.add(yesButton);

        JButton noButton = new JButton("No");
        noButton.addActionListener(this);
        noButton.setActionCommand("N2");
        noButton.setBounds(200,200,100,25);
        this.add(noButton);

    }
}
