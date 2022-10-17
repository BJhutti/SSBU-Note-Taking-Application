package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NotesTest {

    Notes note = new Notes();

    @BeforeEach
    void reset() {
        note = new Notes("Title", "Paragraph");
    }

    @Test
    public void editTitleTest() {
        note.editTitle("newTitle");
        assertEquals("newTitle", note.getTitle());
    }

    @Test
    public void editParagraph() {
        note.editParagraph("New Paragraph");
        assertEquals("New Paragraph", note.getParagraph());
    }
}
