package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserDataTest {

    private final UserData data = new UserData("Bryan's Data");
    private final UltCharacter character = new UltCharacter("Character");

    @Test
    void addCharacterToListTest() {
        assertTrue(data.getCharacters().isEmpty());
        UltCharacter character2 = new UltCharacter("Character2");
        data.addCharacterToList(character);
        data.addCharacterToList(character2);
        assertEquals(data.getCharacters().get(0).getName(), "Character");
        assertEquals(data.getCharacters().get(1).getName(), "Character2");
        assertEquals(data.getCharacters().size(), 2);

    }
}
