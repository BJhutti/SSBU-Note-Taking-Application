package persistence;

import org.json.JSONObject;

//CITATION: this interface was directly taken from JsonReader.java in JsonSerializationDemo
////given by UBC in the course CPSC 210. The implementations of this interface were all my own.
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject convertToJson();
}
