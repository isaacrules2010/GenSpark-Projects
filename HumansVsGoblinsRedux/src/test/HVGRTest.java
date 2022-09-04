import Entities.Creature;
import Entities.Goblin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class HVGRTest {


    HVGR testGame;

    @BeforeEach
    void setUp() {
        testGame = new HVGR();
    }

    @Test
    void generateEmptyMap() {
        testGame.generateEmptyMap();
        String testAgainst ="";
        for(int x = 0; x < 20; x++){
            for(int y = 0; y < 20; y++){
                testAgainst +="\u00b1  ";
            }
            testAgainst+="\n";
        }
        testAgainst+="\n";
        assertEquals(testAgainst, testGame.printMap(), "printMap failed");
    }

    @Test
    void populateMap() {
        testGame.generateEmptyMap();
        testGame.populateMap(5);
        assertEquals(5, testGame.getGoblins().size(), "populateMap failed");
    }

    @Test
    void spawnGoblin() {
        testGame.generateEmptyMap();
        int[] testPos = {0,0};
        testGame.spawnGoblin(testPos);
        assertEquals(6, testGame.getGoblins().size(), "spawnGoblin failed");
        assertEquals(testPos.toString(), testGame.getGoblins().get(5).getPosition().toString(), "spawnGoblin incorrect setPosition");
    }

    @Test
    void spawnPlayer() {
        testGame.generateEmptyMap();
        int[] testPos = {0,0};
        testGame.spawnPlayer(testPos);
        assertEquals(testPos, testGame.getPlayer().getPosition(), "spawnPlayer incorrect setPosition\n");
    }

    @Test
    void removeGoblin() {
        testGame.generateEmptyMap();
        testGame.spawnGoblin(new int[] {0,0});
        testGame.removeGoblin(testGame.getGoblins().get(5));
        assertEquals(5, testGame.getGoblins().size(), "removeGoblin failed\n");
        assertEquals(1, testGame.getDrops().size(), "removeGoblin failed to drop item properly\n");
    }

    @Test
    void removeDrop() {
        testGame.generateEmptyMap();
        testGame.spawnGoblin(new int[] {0,0});
        testGame.removeGoblin(testGame.getGoblins().get(0));
        testGame.removeDrop(testGame.getDrops().get(0));
        assertEquals(0, testGame.getDrops().size(), "removeDrop failed");
    }

    @Test
    void moveCreature() {
        testGame.generateEmptyMap();
        int[] startPos = {5,5};
        testGame.spawnPlayer(startPos);
        testGame.moveCreature(testGame.getPlayer(), 'n');
        assertEquals(startPos[1]+1, testGame.getPlayer().getPosition()[1], "moveCreature NORTH failed");

        testGame.moveCreature(testGame.getPlayer(), 's');
        assertEquals(startPos[1], testGame.getPlayer().getPosition()[1], "moveCreature SOUTH failed");

        testGame.moveCreature(testGame.getPlayer(), 'e');
        assertEquals(startPos[0]+1, testGame.getPlayer().getPosition()[0], "moveCreature EAST failed");

        testGame.moveCreature(testGame.getPlayer(), 'w');
        assertEquals(startPos[0], testGame.getPlayer().getPosition()[0], "moveCreature WEST failed");

    }

    @Test
    void swapTile() {
        testGame.generateEmptyMap();
        int[] startPos = {5,5};
        testGame.spawnPlayer(startPos);
        assertEquals('\u00b1', testGame.getWorldMap()[5][6].getSymbol(), "SYMBOL FIND FAILURE");
        testGame.moveCreature(testGame.getPlayer(), 'n');
        assertEquals('\u00b1', testGame.getWorldMap()[5][5].getSymbol(), "swapTile failed");
    }

    @Test
    void combat() {
        testGame.generateEmptyMap();
        int[] testPos1 = {0,0};
        int[] testPos2 = {0,1};
        testGame.spawnPlayer(testPos1);
        testGame.spawnGoblin(testPos2);
        testGame.moveCreature(testGame.getPlayer(), 'n');
        assertEquals(true, testGame.getGoblins().get(5).isInCombat(), "combat failed");
    }

    @Test
    void pursue() {
        testGame.generateEmptyMap();
        int[] testPos1 = {0,0};
        int[] testPos2 = {0,5};
        testGame.spawnPlayer(testPos1);
        testGame.spawnGoblin(testPos2);
        testGame.pursue(testGame.getGoblins().get(5));
        assertEquals(4, testGame.getGoblins().get(5).getPosition()[1], "combat failed");
    }

    @AfterEach
    void tearDown() {
    }
}