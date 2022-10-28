package persistence;



import model.UserData;
import org.json.JSONObject;



import java.io.*;

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private final String fileLocation;

    public JsonWriter(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(fileLocation));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(UserData data) {
        JSONObject json = data.toJson();
        saveToFile(json.toString(TAB));
    }

    private void saveToFile(String fileName) {
        writer.print(fileName);
    }

    public void close() {
        writer.close();
    }
}