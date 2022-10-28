package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class UserData implements Writable {
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("Characters", charactersToJSon());
        return json;
    }

    private JSONArray charactersToJSon() {
        JSONArray jsonArray = new JSONArray();
        for (UltCharacter character : characters) {
            jsonArray.put(character.toJson());
        }
        return jsonArray;
    }
}
