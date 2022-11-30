package persistence;

import model.Event;
import model.EventLog;
import model.UserData;
import org.json.JSONObject;
import java.io.*;

//CITATION
//parts of this class were helped created using JsonReader.java in JsonSerializationDemo
//given by UBC in the course CPSC 210


//A json writer that when called, allows the user to write to a file given UserData
public class JsonWriter {
    private static final int TAB = 4; //tab length
    private PrintWriter writer;
    private final String fileLocation; //location of file you are writing to

    public JsonWriter(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    //MODIFIES: writer
    //EFFECTS: checks to see if the file exists, then opens to writer for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(fileLocation));
    }

    //MODIFIES: this
    // EFFECTS: writes the Json representation of UserData to a file
    public void write(UserData data) {
        EventLog.getInstance().logEvent(new Event("- Saving file to " + fileLocation));
        JSONObject json = data.convertToJson();
        saveToFile(json.toString(TAB));
    }

    //MODIFIES: this
    //EFFECTS: saves the written data to the file location
    private void saveToFile(String fileName) {
        writer.print(fileName);
    }

    //EFFECTS: closes the writer object
    public void close() {
        writer.close();
    }
}