package model;

import java.util.ArrayList;

public class UserData {
    private final ArrayList<UltCharacter> characters = new ArrayList<>();
    private final String name;

    public UserData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<UltCharacter> getCharacters() {
        return characters;
    }

    public void addCharacterToList(UltCharacter character) {
        characters.add(character);

    }
}
