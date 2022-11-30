package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Objects;

// Represents an enemy character, and contains the notes created or that character
public class Enemy implements Writable {
    private final String name; // the name of the enemy character
    private final ArrayList<Notes> listOfNotes = new ArrayList<>(); // list of notes against the enemy

    public Enemy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Notes> getListOfNotes() {
        return listOfNotes;
    }

    //REQUIRES: note with title that does not currently exist within listOfNotes
    //MODIFIES: this
    //EFFECTS: adds a new note to the end of listOfNotes
    public void addNoteToList(Notes note) {
        this.listOfNotes.add(note);
        EventLog.getInstance().logEvent(new Event("- Added " + note.getTitle() + " to "
                + getName() + "'s notes -"));
    }

    //REQUIRES: name of note that already exists, new title that does not exist within listOfNotes
    //MODIFIES: this
    //EFFECTS: edits an already existing note
    public void editNote(String name, String title, String paragraph) {
        Notes noteEdit = new Notes("Title", "Paragraph");
        for (int i = 0; i < listOfNotes.size(); i++) {
            if (Objects.equals(listOfNotes.get(i).getTitle(), name)) {
                noteEdit = listOfNotes.get(i);
                i = listOfNotes.size();
            }
        }
        noteEdit.editTitle(title);
        noteEdit.editParagraph(paragraph);
    }

    //REQUIRES: name of the note that already exists
    //MODIFIES: this
    //EFFECTS: deletes a note using the title of the note
    public boolean deleteNote(String name) {
        for (int i = 0; i < listOfNotes.size(); i++) {
            if (Objects.equals(listOfNotes.get(i).getTitle(), name)) {
                EventLog.getInstance().logEvent(new Event("- Removed " + listOfNotes.get(i).getTitle() + " from "
                        + getName() + "'s notes -"));
                listOfNotes.remove(i);
                return true;
            }
        }
        return false;
    }

    //Returns a json object that contains this enemy object
    @Override
    public JSONObject convertToJson() {
        JSONObject json = new JSONObject();
        json.put("Enemy Name", name);
        json.put("Notes", notesToJson());
        return json;
    }

    //Returns a JSONArray of the notes within this enemy object's list of notes
    private JSONArray notesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Notes note : listOfNotes) {
            jsonArray.put(note.convertToJson());
        }

        return jsonArray;
    }
}
