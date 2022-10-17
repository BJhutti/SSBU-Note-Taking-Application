package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {
    private Enemy enemy = new Enemy("New Enemy");

    @BeforeEach
    public void  create() {
        Notes note = new Notes("New note", "A new note.");
        this.enemy.addNoteToList(note);
    }

    @Test
    public void emptyListOfNotesTest() {
        enemy = new Enemy("Empty Enemy");
        assertTrue(enemy.getListOfNotes().isEmpty());
        assertEquals(enemy.getListOfNotes().size(), 0);
    }

    @Test
    public void addNewNoteTest() {
        assertEquals(this.enemy.getListOfNotes().size(), 1);
        assertEquals(this.enemy.getListOfNotes().get(0).getTitle(), "New note" );
        assertEquals(this.enemy.getListOfNotes().get(0).getParagraph(), "A new note.");
    }

    @Test
    public void addMultipleNotesTest() {
        Notes note2 = new Notes("Note 2", "This is note 2.");
        Notes note3 = new Notes("Note 3", "This is note 3");
        this.enemy.addNoteToList(note2);
        this.enemy.addNoteToList(note3);
        assertEquals(this.enemy.getListOfNotes().size(), 3);
    }

    @Test
    public void editNoteTest() {
        this.enemy.editNote("New note", "Even Newer Note", "The Sequel");
        assertEquals(this.enemy.getListOfNotes().get(0).getTitle(), "Even Newer Note");
        assertEquals(this.enemy.getListOfNotes().get(0).getParagraph(), "The Sequel");
    }

    @Test
    public void deleteNoteTest() {
        boolean bool = enemy.deleteNote("Not a note");
        assertFalse(bool);
        bool = enemy.deleteNote("New note");
        assertEquals(enemy.getListOfNotes().size(), 0);
        assertTrue(bool);

    }
}
