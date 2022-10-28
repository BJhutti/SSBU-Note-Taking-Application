package model;

import org.json.JSONObject;
import persistence.Writable;

//Represents a note that has a title for the note, and a paragraph containing the notes
public class Notes implements Writable {
    private String title; // Title of the note
    private String paragraph; // Contents of the note


    //REQUIRES: title to be unique and not empty;
    //EFFECTS: Creates a new note with a title and paragraph
    public Notes(String title, String paragraph) {
        this.title = title;
        this.paragraph = paragraph;
    }

    //REQUIRES: newTitle is not empty
    //MODIFIES: this
    //EFFECTS: changed the title of the note
    public void editTitle(String newTitle) {
        this.title = newTitle;
    }

    //REQUIRES: newParagraph is not empty
    //MODIFIES: this
    //EFFECTS: changed the paragraph of the note
    public void editParagraph(String newParagraph) {
        this.paragraph = newParagraph;
    }

    public String getTitle() {
        return title;
    }

    public String getParagraph() {
        return paragraph;
    }

    //EFFECTS: returns a JSON object that represents this note
    @Override
    public JSONObject convertToJson() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("Title", title);
        jsonObj.put("Paragraph", paragraph);
        return jsonObj;
    }
}
