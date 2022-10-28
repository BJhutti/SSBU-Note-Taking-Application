package persistence;

import model.UltCharacter;
import  org.json.JSONObject;
import ui.NoteTakingApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//CITATION: parts of this code were taken from the course CPSC 210 at UBC

public class JsonWriter {
    private static final int TABLENGTH = 4;
    private PrintWriter writer;
    private String fileLocation;

    public JsonWriter(String destination) {
        this.fileLocation = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens the Json writer; throws FileNotFoundException error if file is not found
    // or cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(fileLocation));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of UltCharacter to the Json file
    public void write(NoteTakingApplication application) {
        JSONObject json = application.toJson();
        saveToFile(json.toString(TABLENGTH));
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

    // MODIFIES: this
    // EFFECTS: closes the writer object
    public void close() {
        writer.close();
    }

}
