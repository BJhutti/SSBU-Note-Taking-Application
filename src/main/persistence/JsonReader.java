package persistence;

import model.UltCharacter;
import model.Notes;
import model.Enemy;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.UserData;
import org.json.*;

public class JsonReader {
    private String fileLocation;

    // EFFECTS: constructs a reader object to read from the file destination
    public JsonReader(String destination) {
        this.fileLocation = destination;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public UserData read() throws IOException {
        String jsonData = readFile(fileLocation);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseApplication(jsonObject);
    }

    private UserData parseApplication(JSONObject jsonObject) {
        parseUltCharacter(jsonObject);
        parseUserData(jsonObject);
    }

    private UserData parseUserData(JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        UserData data = new UserData();
        addCharacters(jsonObject, data);
    }

    private JSONArray addCharacters(JSONObject jsonObject, UserData data) {

    }

    private UltCharacter parseUltCharacter(JSONObject jsonObject) {
        String name = jsonObject.getString("Character Name");
        UltCharacter character = new UltCharacter(name);
        addEnemies(character, jsonObject);
        return character;
    }

    private void addEnemies(UltCharacter character, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("listOfEnemies");
        for (Object json : jsonArray) {
            JSONObject nextEnemy = (JSONObject) json;
            addNotesToEnemy(character, nextEnemy);
        }
    }

    private void addNotesToEnemy(UltCharacter character, JSONObject nextEnemy) {
        String name = nextEnemy.getString("Enemy Name");
        Enemy enemy = new Enemy(name);
        JSONArray jsonArray = nextEnemy.getJSONArray("listOfNotes");
        for (Object json : jsonArray) {
            JSONObject nextNote = (JSONObject) json;
            String noteName = nextNote.getString("Title");
            String paragraph = nextNote.getString("Paragraph");
            Notes note = new Notes(noteName, paragraph);
            enemy.addNoteToList(note);
        }
        character.addEnemyToList(enemy);
    }

    private String readFile(String fileLocation) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(fileLocation), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }
}
