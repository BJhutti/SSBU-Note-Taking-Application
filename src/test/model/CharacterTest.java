package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CharacterTest {
    private Character character = new Character("New Character");
    private final Enemy enemy = new Enemy("New Enemy");

    @BeforeEach
    public void  create() {
        character = new Character("New Character");
        this.character.addEnemyToList(enemy);
    }

    @Test
    public void emptyListOfEnemiesTest() {
        character = new Character("Empty Character");
        assertTrue(character.getListOfEnemyCharacters().isEmpty());
        assertEquals(character.getListOfEnemyCharacters().size(), 0);
    }

    @Test
    public void addNewEnemyTest() {
        assertEquals(this.character.getListOfEnemyCharacters().size(), 1);
        assertEquals(this.character.getListOfEnemyCharacters().get(0).getName(), "New Enemy" );
    }

    @Test
    public void addMultipleEnemiesTest() {
        Enemy enemy2 = new Enemy("Enemy2");
        Enemy enemy3 = new Enemy("Enemy3");
        this.character.addEnemyToList(enemy2);
        this.character.addEnemyToList(enemy3);
        assertEquals(this.character.getListOfEnemyCharacters().size(), 3);
    }

    @Test
    public void deleteEnemyTest() {
        character.deleteEnemyFromList("New Enemy");
        assertEquals(character.getListOfEnemyCharacters().size(), 0);
    }

}
