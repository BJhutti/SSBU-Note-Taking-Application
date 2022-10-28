package persistence;
import model.Enemy;
import model.Notes;
import model.UltCharacter;
import model.UserData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

public class JsonReaderTest {
    @Test
    void fileDoesNotExistTest() {
        JsonReader jsonReader = new JsonReader("./data/thisFileDoesNoteExist.json");
        try{
            UserData data = jsonReader.read();
            fail("IOException expected");
        } catch (IOException ignored) {

        }
    }

    @Test
    void emptyUserDataTest() {
        JsonReader jsonReader = new JsonReader("./data/testEmptyUserData.json");
        try {
            UserData data = jsonReader.read();
            assertEquals(data.getName(),"Bryan's Data");
            assertEquals(data.getCharacters().size(), 0);
            try {
                assertEquals(data.getCharacters().get(0).getListOfEnemyCharacters().get(0).getListOfNotes().size(), 0);
                fail("Should throw an error as items don't exist");
            } catch (IndexOutOfBoundsException ignored) {
            }
        } catch (IOException exception) {
            fail("Could not read file");
        }
    }
    @Test
    void filledUserDataTest() {
        JsonReader jsonReader = new JsonReader("./data/testUserWithData.json");
        try {
            UserData data = jsonReader.read();
            assertEquals(data.getName(), "Bryan's Data");
            ArrayList<UltCharacter> characters = data.getCharacters();
            UltCharacter character = characters.get(0);
            assertEquals(character.getName(), "Character");
            ArrayList<Enemy> enemies = character.getListOfEnemyCharacters();
            Enemy enemy = enemies.get(0);
            assertEquals(enemy.getName(), "Enemy");
            ArrayList<Notes> notes = enemy.getListOfNotes();
            Notes note = notes.get(0);
            assertEquals(note.getTitle(), "Title");
            assertEquals(note.getParagraph(), "Paragraph");
            Notes note2 = notes.get(1);
            assertEquals(note2.getTitle(), "Title2");
            assertEquals(note2.getParagraph(), "Paragraph2");
        } catch (IOException exception) {
            fail("File did not open correctly");
        }
    }




}
