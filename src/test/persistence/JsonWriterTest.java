package persistence;

import model.Enemy;
import model.Notes;
import model.UltCharacter;
import model.UserData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class JsonWriterTest {
    @Test
    void invalidFileLocationTest () {
        try {
            UserData data = new UserData("Bryan's Data");
            JsonWriter writer = new JsonWriter("notfile\n-_-~file|name/.json");
            writer.open();
            fail("Should have not opened a file location");
        } catch (FileNotFoundException ignored) {
            //passes!
        }
    }

    @Test
    void createEmptyUserDataTest() {
        try {
            UserData data = new UserData("Bryan's Data");
            JsonWriter writer = new JsonWriter("./data/testWriteEmptyUserData.json");

            writer.open();
            writer.write(data);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteEmptyUserData.json");
            data = reader.read();

            assertEquals(data.getName(), "Bryan's Data");
            assertEquals(data.getCharacters().size(), 0);

        } catch (FileNotFoundException e) {
            fail("Should have not thrown an exception");
        } catch (IOException e) {
            fail("File was not created properly");
        }
    }

    @Test
    void UserDataFilledTest() {
        try {
            UserData data = new UserData("Bryan's Data");
            UltCharacter character = new UltCharacter("Character");
            Enemy enemy = new Enemy("Enemy");
            Notes note = new Notes("Title", "Paragraph");
            enemy.addNoteToList(note);
            character.addEnemyToList(enemy);
            data.addCharacterToList(character);

            JsonWriter writer = new JsonWriter("./data/writeFilledUserDataTest.json");
            writer.open();
            writer.write(data);
            writer.close();

            JsonReader reader = new JsonReader("./data/writeFilledUserDataTest.json");

            data = reader.read();
            assertEquals(data.getName(), "Bryan's Data");
            assertEquals(data.getCharacters().size(), 1);

            character = data.getCharacters().get(0);
            assertEquals(character.getName(), "Character");
            assertEquals(character.getListOfEnemyCharacters().size(), 1);

            enemy = character.getListOfEnemyCharacters().get(0);
            assertEquals(enemy.getName(), "Enemy");
            assertEquals(enemy.getListOfNotes().size(), 1);

            note = enemy.getListOfNotes().get(0);
            assertEquals(note.getTitle(), "Title");
            assertEquals(note.getParagraph(), "Paragraph");

        } catch (FileNotFoundException e) {
            fail("Should have not thrown an exception");
        } catch (IOException e) {
            fail("File was not created properly");
        }
    }

}
