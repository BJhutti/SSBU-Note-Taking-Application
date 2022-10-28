package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserData {
    protected final ArrayList<UltCharacter> characters = new ArrayList<>(); //UI shouldnt have these, create a new class
    protected static final String name = "Bryan's app";

    public ArrayList<UltCharacter> getCharacters() {
        return characters;
    }
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("listOfCharacters", charactersToJson());
        return json;
    }

    private JSONArray charactersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (UltCharacter character : characters) {
            jsonArray.put(character.toJson());
        }
        return jsonArray;
    }


    public void addCharToList(UltCharacter character) {
        this.characters.add(character);
    }
}
