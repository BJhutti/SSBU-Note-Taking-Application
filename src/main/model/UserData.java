package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// An object that represents the users data, which includes all characters, enemies
// and notes for the user
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


    //MODIFIES: this
    //EFFECTS: adds a new character to the list of characters
    public void addCharacterToList(UltCharacter character) {
        characters.add(character);
        EventLog.getInstance().logEvent(new Event("- Added " + character.getName() + " to list of characters -"));

    }

    //EFFECTS: creates a Json object that represents this UserData
    @Override
    public JSONObject convertToJson() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("name", name);
        jsonObj.put("Characters", charactersToJSon());
        return jsonObj;
    }

    //EFFECTS: creates a Json array that represents the array od characters within
    //this user data
    private JSONArray charactersToJSon() {
        JSONArray jsonArray = new JSONArray();
        for (UltCharacter character : characters) {
            jsonArray.put(character.convertToJson());
        }
        return jsonArray;
    }

    // EFFECTS: displays the events that were written to the EventLog
    public void printLog(EventLog instance) {
        for (Event event : instance) {
            System.out.println(event);
        }
    }


    // MODIFIES: characters
    // EFFECTS: removes a character from characters given its index
    public void remove(int index) {
        EventLog.getInstance().logEvent(new Event("- Removed " + characters.get(index).getName()
                + " from list of characters -"));
        this.characters.remove(index);
    }
}
