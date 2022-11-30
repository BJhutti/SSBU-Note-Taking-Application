package ui;

import model.Enemy;
import model.EventLog;
import model.UltCharacter;
import model.UserData;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

//CITATION
//parts of this class were helped created using JsonReader.java in JsonSerializationDemo
//given by UBC in the course CPSC 210. This also includes other classes in the model package that use Json


//a class that creates the NoteTakingApplication with GUI
public class NoteTakingApplication extends JFrame implements ActionListener {

    private UserData data = new UserData("Bryan's application.");

    private static final String LOCATION_STORAGE = "./data/Bryan's_Notes.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Timer timer = new Timer(4000, this);
    JPanel panel;

    //EFFECTS: starts the application, creates new storage places for writer and reader,
    public NoteTakingApplication() {
        super("Smash Ultimate Note Taker");
        setLayout(null);
        this.setPreferredSize(new Dimension(400,400));
        this.setSize(400, 400);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        SplashScreen panel = new SplashScreen();
        panel.setBounds(0, 0, 400, 400);
        this.add(panel);
        this.setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                quit();
            }

        });

        timer.start();

    }

    //EFFECTS: starts the note-taking app after splashscreen
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.stop();
        noteTakingApplicationStart();
    }

    //MODIFIES: this
    //EFFECTS: starts the note application with the load or save screen
    public void noteTakingApplicationStart() {
        jsonWriter = new JsonWriter(LOCATION_STORAGE);
        jsonReader = new JsonReader(LOCATION_STORAGE);
        loadQuestion();
    }

    //MODIFIES: this
    //EFFECTS: loads a file from the given location storage
    public void loadFile() {
        try {
            data = jsonReader.read();
//            System.out.println("Loaded " + data.getName() + " from " + LOCATION_STORAGE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + LOCATION_STORAGE + ". Creating a new file.");
        }
    }

    //MODIFIES: LOCATION_STORAGE file
    //EFFECTS: saves data to the file location
    public void saveFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(data);
            jsonWriter.close();
//            System.out.println("Saved " + data.getName() + " to " + LOCATION_STORAGE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable add to the file located at: " + LOCATION_STORAGE + ". Data was not saved.");
        }
    }

    //EFFECTS: asks the user if they would like to load from a save file
    private void loadQuestion()  {
        panel = new LoadScreen(this);
        setContentPane(panel);
    }

    //EFFECTS: enters the start/home menu of the application
    public void start() {
        panel = new MainScreen(this);
        setContentPane(panel);
    }

    //MODIFIES: this
    //EFFECTS: creates a new UltCharacter object in characters
    public void createNewCharacter() {
        panel = new CreateCharacter(this);
        setContentPane(panel);
    }


    //MODIFIES: this
    //EFFECTS: adds a character with a given name to list  of characters
    public void addCharacter(String name) {
        UltCharacter newChar = new UltCharacter(name);
        this.data.addCharacterToList(newChar);
    }

    //EFFECTS: displays a list of characters, and allows user to select one
    public void displayCharacters() {
        panel = new CharacterSelectMenu(this);
        setContentPane(panel);

    }

    public UserData getData() {
        return data;
    }

    //REQUIRES: character
    //MODIFIES: this
    /*EFFECTS: creates a new menu; creates a new enemy and allows user to add notes; adds a note to an existing enemy;
    deletes an enemy; allows user to view or delete created notes; allows user to exit this menu;
     */
    public void accessCharacter(int charIndex, UltCharacter character) {
        panel = new CharacterMenu(this, character, charIndex);
        setContentPane(panel);
    }

    //MODIFIES: this
    //EFFECTS: adds a new enemy to characters
    public void addEnemyToCharacter(UltCharacter character, int index) {
        panel = new CreateEnemy(character, index, this);
        setContentPane(panel);
    }

    //MODIFIES: this
    //EFFECTS: adds a node to an existing enemy
    public void addNoteToExistingEnemy(int charIndex, UltCharacter character) {
        panel = new ListEnemies(character, charIndex, this);
        setContentPane(panel);
    }

    //EFFECTS: shows a menu that allows user to view or delete notes on a particular enemy
    public void viewOrDeleteNotes(UltCharacter character) {
        panel = new ViewOrDeleteNotes(this, character);
        setContentPane(panel);
    }

    //MODIFIES: this
    //EFFECTS: deletes a character from characters
    public void delete(int index) {
        this.data.remove(index);
    }



    //EFFECTS: creates a menu for notes; allows user to view notes; allows user to delete a note;
    public void notesChoice(UltCharacter character) {
        panel = new ListEnemiesForNotes(character, this);
        setContentPane(panel);
    }

    //EFFECTS: allows user to view already created notes for an enemy
    public void viewNotes(Enemy enemy, UltCharacter character, int index) {
        panel = new ViewNotes(enemy, this, character, index);
        setContentPane(panel);
    }

    //EFFECTS: displays the exit message for the application, and returns true or false
    public void quitQuestion() {
        panel = new QuitQuestion(this);
        setContentPane(panel);
    }

    //EFFECTS: displays a panel that lets the user choose an enemy
    public void chooseEnemy(UltCharacter character) {
        panel = new ChooseEnemy(character, this);
        setContentPane(panel);
    }

    //MODIFIES: this
    //EFFECTS: deletes a note for an enemy given the enemy; shows titles of notes;
    public void deleteNote(Enemy enemy) {
        panel = new DeleteNote(enemy, this);
        setContentPane(panel);
    }

    //MODIFIES: this
    //EFFECTS: create a note for an enemy given the enemy
    public void createNote(Enemy enemy, int index) {
        panel = new CreateNote(enemy, index, this);
        setContentPane(panel);
    }


    //EFFECTS: shows the event log and exits the app
    public void quit() {
        data.printLog(EventLog.getInstance());
        System.exit(0);
    }
}
