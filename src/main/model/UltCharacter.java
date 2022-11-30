package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Objects;

//Represents a character the user uses, and has a list of enemies that contains notes against them
public class UltCharacter implements Writable {
    private final String name; // Name of the character
    private final ArrayList<Enemy> listOfEnemyCharacters = new ArrayList<>(); // List of enemies

    public UltCharacter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Enemy> getListOfEnemyCharacters() {
        return listOfEnemyCharacters;
    }

    //REQUIRES: unique name that is not empty
    //MODIFIES: this
    //EFFECTS: adds new enemy to listOfEnemyCharacters
    public void addEnemyToList(Enemy name) {
        listOfEnemyCharacters.add(name);
        EventLog.getInstance().logEvent(new Event("- Added " + name.getName() + " to " + this.name + "'s enemies -"));
    }

    //REQUIRES: name of enemy to be in list
    //MODIFIES: this, enemy
    //EFFECTS: deletes an enemy from listOfEnemyCharacters
    public void deleteEnemyFromList(String name) {
        for (int i = 0; i < listOfEnemyCharacters.size(); i++) {
            if (Objects.equals(listOfEnemyCharacters.get(i).getName(), name)) {
                listOfEnemyCharacters.remove(i);
                i = listOfEnemyCharacters.size();
                EventLog.getInstance().logEvent(new Event("- Removed " + name + " from " + this.name + "'s enemies -"));
            }
        }
    }

    @Override
    //Creates a Json object that represents this UltCharacter
    public JSONObject convertToJson() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("Character Name", name);
        jsonObj.put("Enemies", enemiesToJSon());
        return jsonObj;
    }

    //Creates a JSon array that contains all the enemies within this character
    private JSONArray enemiesToJSon() {
        JSONArray jsonArray = new JSONArray();
        for (Enemy enemy : listOfEnemyCharacters) {
            jsonArray.put(enemy.convertToJson());
        }
        return jsonArray;
    }
}
