package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UltCharacterTest {
    private UltCharacter ultCharacter = new UltCharacter("New Character");
    private final Enemy enemy = new Enemy("New Enemy");

    @BeforeEach
    public void  create() {
        ultCharacter = new UltCharacter("New Character");
        this.ultCharacter.addEnemyToList(enemy);
    }

    @Test
    public void emptyListOfEnemiesTest() {
        ultCharacter = new UltCharacter("Empty Character");
        assertTrue(ultCharacter.getListOfEnemyCharacters().isEmpty());
        assertEquals(ultCharacter.getListOfEnemyCharacters().size(), 0);
    }

    @Test
    public void addNewEnemyTest() {
        assertEquals(this.ultCharacter.getName(), "New Character");
        assertEquals(this.ultCharacter.getListOfEnemyCharacters().size(), 1);
        assertEquals(this.ultCharacter.getListOfEnemyCharacters().get(0).getName(), "New Enemy" );
    }

    @Test
    public void addMultipleEnemiesTest() {
        Enemy enemy2 = new Enemy("Enemy2");
        Enemy enemy3 = new Enemy("Enemy3");
        this.ultCharacter.addEnemyToList(enemy2);
        this.ultCharacter.addEnemyToList(enemy3);
        assertEquals(this.ultCharacter.getListOfEnemyCharacters().size(), 3);
    }

    @Test
    public void deleteEnemyTest() {
        assertFalse(this.ultCharacter.getListOfEnemyCharacters().isEmpty());
        Enemy enemy2 = new Enemy("Enemy 2");
        ultCharacter.addEnemyToList(enemy2);
        ultCharacter.deleteEnemyFromList("Enemy 2");
        assertEquals(ultCharacter.getListOfEnemyCharacters().size(), 1);
        ultCharacter.deleteEnemyFromList("New Enemy");
        assertEquals(ultCharacter.getListOfEnemyCharacters().size(), 0);

    }

}
