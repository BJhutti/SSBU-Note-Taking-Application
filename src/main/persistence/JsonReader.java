package persistence;


import model.Enemy;
import model.Notes;
import model.UltCharacter;
import model.UserData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//CITATION
//parts of this class were helped created using JsonReader.java in JsonSerializationDemo
//given by UBC in the course CPSC 210

//A class that represents a reader that reads UserData from a JSON file, and return a
// UserData object with all the data stored in the file
public class JsonReader {
    private final String fileLocation; //Location of file within computer

    // EFFECTS: Constructs a reader object given the file location
    public JsonReader(String fileLocation) {
        this.fileLocation = fileLocation;
    }


    // EFFECTS: reads from the file and returns UserData
    // throws IOException if an error occurs with reading the file location
    public UserData read() throws IOException {
        String jsonData = readFile(this.fileLocation);
        JSONObject jsonObject = new JSONObject(jsonData);
        return readUserData(jsonObject);
    }

    //EFFECTS: creates a user data object by reading the Character list in
    // the Json file, and returns it
    private UserData readUserData(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        UserData data = new UserData(name);
        addCharacters(data, jsonObject);
        return data;
    }

    //EFFECTS: creates a new character array that is stored in UserData
    // by reading the Json file and adding characters, then returns it.
    private void addCharacters(UserData data, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Characters");
        for (Object json : jsonArray) {
            JSONObject nextCharacter = (JSONObject) json;
            addCharacter(data, nextCharacter);
        }
    }

    //MODIFIES: data
    //EFFECTS: creates a character object by reading the Json file, adds the Enemy
    // list to it, then returns the character object
    private void addCharacter(UserData data, JSONObject jsonObject) {
        String name = jsonObject.getString("Character Name");
        UltCharacter character = new UltCharacter(name);
        JSONArray jsonArray = jsonObject.getJSONArray("Enemies");
        for (Object json : jsonArray) {
            JSONObject nextEnemy = (JSONObject) json;
            addEnemy(character, nextEnemy);
        }
        data.addCharacterToList(character);
    }

    //MODIFIES: character
    //EFFECTS: creates a new Enemy array using the Json file,
    // gets enemies, and adds it to the character. Returns the character with array of enemies.
    private void addEnemy(UltCharacter character, JSONObject jsonObject) {
        String name = jsonObject.getString("Enemy Name");
        Enemy enemy = new Enemy(name);
        JSONArray jsonArray = jsonObject.getJSONArray("Notes");
        for (Object json : jsonArray) {
            JSONObject nextNote = (JSONObject) json;
            addNote(enemy, nextNote);
        }
        character.addEnemyToList(enemy);
    }

    //MODIFIES: enemy
    //EFFECTS: Creates a new note from the JSON file, and adds it to the list of notes in
    // an enemy. Returns the enemy with notes.
    private void addNote(Enemy enemy, JSONObject jsonObject) {
        String name = jsonObject.getString("Title");
        String paragraph = jsonObject.getString("Paragraph");
        Notes note = new Notes(name, paragraph);
        enemy.addNoteToList(note);
    }


    //EFFECTS: reads the file in fileLocation and converts it to a string
    private String readFile(String fileLocation) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(fileLocation), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }
}
