package ui;

import model.Enemy;
import model.Notes;
import model.UltCharacter;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class NoteTakingApplication {
    private final ArrayList<UltCharacter> characters = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    //EFFECTS: starts the application
    public NoteTakingApplication() {
        UltCharacter char1 = new UltCharacter("ZSS");
        UltCharacter char2 = new UltCharacter("Wolf");
        characters.add(char1);
        characters.add(char2);

        boolean check = true;
        while (check) {
            start();
            if (quitQuestion()) {
                check = false;
            }
        }
    }

    //EFFECTS: enters the start menu of the application
    public void start() {
        boolean notQuit = true;
        while (notQuit) {
            String option1 = "|1: Create new character file. | ";
            String option2 = "2: Access created characters. | ";
            String option3 = "3: Quit. |";
            System.out.println("What would you like to do?\n" + option1 + option2 + option3);
            notQuit = assessInput();
        }
    }

    //EFFECTS: assesses the input given and goes to the correlated menu
    public boolean assessInput() {
        int choiceInt = getUserInputsInt();
        if (choiceInt == 1) {
            createNewCharacter();
            return true;
        } else if (choiceInt == 2) {
            displayCharacters();
            return true;
        } else if (choiceInt == 3) {
            return false;
        }
        System.out.println("Invalid input! Select 1, 2, or 3.");
        return assessInput();
    }

    //MODIFIES: this
    //EFFECTS: creates a new UltCharacter object in characters
    public void createNewCharacter() {
        System.out.println("Choose a name:");
        String name = this.scanner.nextLine();
        UltCharacter newChar = new UltCharacter(name);
        this.characters.add(newChar);
        System.out.println("New character " + name + " created! Returning to menu.");
    }

    //EFFECTS: displays a list of characters, and allows user to select one
    public void displayCharacters() {
        if (this.characters.isEmpty()) {
            System.out.println("No characters currently in system!");
            return;
        }
        System.out.println("Which character would you like to access?");
        StringBuilder listOfCharacters = new StringBuilder("| ");
        for (int i = 0; i < this.characters.size(); i++) {
            listOfCharacters.append(i).append(": ").append(this.characters.get(i).getName()).append(" | ");
        }
        System.out.println(listOfCharacters);
        int charIndex = getUserInputsInt();
        UltCharacter character = this.characters.get(charIndex);
        accessCharacter(charIndex, character);
    }

    //REQUIRES: character
    //MODIFIES: this
    /*EFFECTS: creates a new menu; creates a new enemy and allows user to add notes; adds a note to an existing enemy;
    deletes an enemy; allows user to view or delete created notes; allows user to exit this menu;

     */
    @SuppressWarnings("methodlength")
    public void accessCharacter(int charIndex, UltCharacter character) {
        menuForCharacterOptions();
        int choiceInt = getUserInputsInt();
        if (choiceInt == 1) {
            Enemy enemy = addEnemyToCharacter(character);
            System.out.println("Add notes? Y/N");
            boolean bool = getUserInputsBool();
            while (bool) {
                createNewNote(enemy);
                System.out.println("Create another? Y/N");
                bool = getUserInputsBool();
            }
            accessCharacter(charIndex, character);
            return;
        } else if (choiceInt == 2) {
            int enemyIndex = displayEnemies(charIndex);
            if (enemyIndex == -1) {
                System.out.println("No enemies for this character, returning to menu.");
                accessCharacter(charIndex, character);
                return;
            } else {
                Enemy enemy = character.getListOfEnemyCharacters().get(enemyIndex);
                createNewNote(enemy);
            }
            return;
        } else if (choiceInt == 3) {
            delete(charIndex);
            return;
        } else if (choiceInt == 4) {
            int enemyIndex = displayEnemies(charIndex);
            if (enemyIndex == -1) {
                System.out.println("No enemies for this character, returning to menu.");
                accessCharacter(charIndex, character);
                return;
            }
            notesChoice(enemyIndex, charIndex);
            return;
        } else if (choiceInt == 5) {
            return;
        }
        System.out.println("Invalid input! Returning to menu.");
    }

    //EFFECTS: displays a menu for character options
    private void menuForCharacterOptions() {
        System.out.println("What would you like to do with this character?");
        String option1 = "| 1: Add new enemy. | ";
        String option2 = " 2: Add a note to old enemy. | ";
        String option3 = " 3: Delete character. |";
        String option4 = " 4: See or delete created notes |";
        String option5 = " 5: Return. |";
        System.out.println(option1 + option2 + option3 + option4 + option5);
    }

    //MODIFIES: this
    //EFFECTS: adds a new enemy to characters
    private Enemy addEnemyToCharacter(UltCharacter character) {
        System.out.println("Enter the name of new enemy:");
        String enemyName = this.scanner.nextLine();
        Enemy enemy = new Enemy(enemyName);
        character.addEnemyToList(enemy);
        return enemy;
    }

    //EFFECTS: displays enemies and allows user to select one
    private int displayEnemies(int charIndex) {
        ArrayList<Enemy> enemies = characters.get(charIndex).getListOfEnemyCharacters();
        if (enemies.isEmpty()) {
            return -1;
        }
        System.out.println("Which enemy would you like to access?");
        StringBuilder listOfEnemies = new StringBuilder("|");
        for (int i = 0; i < enemies.size(); i++) {
            listOfEnemies.append((i)).append(": ").append(enemies.get(i).getName()).append(" |");
        }
        System.out.println(listOfEnemies);
        return getUserInputsInt();
    }

    //MODIFIES: this
    //EFFECTS: deletes a character from characters
    public void delete(int index) {
        System.out.println("Deleting "  + this.characters.get(index).getName() + "...");
        System.out.println("Returning to menu.");
        this.characters.remove(index);
    }

    //MODIFIES: this
    //EFFECTS: creates a new note in for an enemy
    private void createNewNote(Enemy enemy) {
        System.out.println("Enter a title for the note:");
        String title = this.scanner.nextLine();
        System.out.println("Enter your notes:");
        String paragraph = this.scanner.nextLine();
        Notes note = new Notes(title, paragraph);
        enemy.addNoteToList(note);
        System.out.println("Note created! Returning to character menu.");
    }

    //MODIFIES: this
    //EFFECTS: deletes a note for an enemy given the title of the note
    private void deleteNote(Enemy enemy) {
        System.out.println("Which note would you like to delete?");
        StringBuilder notesList = new StringBuilder("| ");
        for (int i = 0; i < enemy.getListOfNotes().size(); i++) {
            Notes note = enemy.getListOfNotes().get(i);
            notesList.append(" ").append(note.getTitle()).append(" | ");
        }
        System.out.println(notesList);
        String notesName = getUserInputsString();
        boolean bool = enemy.deleteNote(notesName);
        if (bool) {
            System.out.println("Successfully deleted!");
        } else {
            System.out.println("Invalid title.");
        }
    }

    //EFFECTS: creates a menu for notes
    private void notesChoice(int enemyIndex, int charIndex) {
        Enemy enemy = characters.get(charIndex).getListOfEnemyCharacters().get(enemyIndex);
        if (enemy.getListOfNotes().isEmpty()) {
            System.out.println("No notes for this enemy");
        }
        System.out.println("| 1: See created notes. | 2: Delete existing notes. |");
        int choiceInt = getUserInputsInt();
        if (choiceInt == 1) {
            viewNotes(enemy);
        } else if (choiceInt == 2) {
            deleteNote(enemy);
        }
    }

    //EFFECTS: allows user to view already created notes for an enemy
    private void viewNotes(Enemy enemy) {
        System.out.println("Notes for " + enemy.getName());
        for (int i = 0; i < enemy.getListOfNotes().size(); i++) {
            Notes note = enemy.getListOfNotes().get(i);
            System.out.println(note.getTitle() + "\n" + note.getParagraph());
            System.out.println("View Next Note? Y/N");
            if (!getUserInputsBool()) {
                i = enemy.getListOfNotes().size() + 1;
            }
        }
        System.out.println("No more notes, returning to menu.");
    }

    //EFFECTS: displays the exit message for the application, and returns true or false
    public boolean quitQuestion() {
        System.out.println("End Application? Y/N");
        return getUserInputsBool();
    }

    //EFFECTS: returns an integer given by user
    public int getUserInputsInt() {
        return Integer.parseInt(this.scanner.nextLine());
    }

    //EFFECTS: returns a boolean given by the user; Y = true; N = false;
    public boolean getUserInputsBool() {
        String choiceStr = this.scanner.nextLine();
        return Objects.equals(choiceStr, "Y");
    }

    //EFFECTS: returns a string given by the user
    public String getUserInputsString() {
        return this.scanner.nextLine();
    }
}
