package model;

import java.util.ArrayList;
import java.util.Objects;

//Represents a character the user uses, and has a list of enemies that contains notes against them
public class Character {
    private String name; // Name of the character
    private final ArrayList<Enemy> listOfEnemyCharacters = new ArrayList<>(); // List of enemies

    public Character(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Enemy> getListOfEnemyCharacters() {
        return listOfEnemyCharacters;
    }

    //REQUIRES: unique name that is not empty
    //MODIFIES: this
    //EFFECTS: adds new enemy to lisOfEnemyCharacters
    public void addEnemyToList(Enemy name) {
        listOfEnemyCharacters.add(name);
    }

    //REQUIRES: name of enemy to be in list
    //MODIFIES: this, enemy
    //EFFECTS: deletes an enemy from listOfEnemyCharacters
    public void deleteEnemyFromList(String name) {
        for (int i = 0; i < listOfEnemyCharacters.size(); i++) {
            if (Objects.equals(listOfEnemyCharacters.get(i).getName(), name)) {
                listOfEnemyCharacters.remove(i);
                i = listOfEnemyCharacters.size();
            }
        }
    }


}
