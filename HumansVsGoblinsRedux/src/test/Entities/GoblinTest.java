package Entities;

import Equipment.Weapon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoblinTest {

    Goblin testGobbo;

    @BeforeEach
    void setUp() {
        int[] pos = {0,0};
        testGobbo = new Goblin(pos, 0);
    }

    @Test
    void setMaxHP() {
        testGobbo.setMaxHP(1);
        assertTrue(testGobbo.getMaxHp() > 1 && testGobbo.getMaxHp() <= 6, "setMaxHp 1d6 failed");
        testGobbo.setMaxHP(2);
        assertTrue(testGobbo.getMaxHp() > 1 && testGobbo.getMaxHp() <= 12, "setMaxHp 2d6 failed");
        testGobbo.setMaxHP(3);
        assertTrue(testGobbo.getMaxHp() > 1 && testGobbo.getMaxHp() <= 18, "setMaxHp 3d6 failed");
        testGobbo.setMaxHP(4);
        assertTrue(testGobbo.getMaxHp() > 1 && testGobbo.getMaxHp() <= 24, "setMaxHp 4d6 failed");

    }

    @Test
    void attack() {
        testGobbo.attack(new Human());
        assertTrue(testGobbo.isInCombat());
    }

    @Test
    void getDrop() {
        assertTrue(testGobbo.getDrop().getSymbol() == 'D');
    }

    @Test
    void pickUp() {
        Weapon testItem = new Weapon(10);
        testGobbo.pickUp(testItem);
        assertEquals(10, testGobbo.getDrop().getBaseDmg(), "goblin pickup failed");
    }

    @Test
    void readyForCombat() {
        testGobbo.attack(new Human());
        testGobbo.readyForCombat();
        assertFalse(testGobbo.isInCombat());
    }

    @Test
    void testToString(){
        String output = "Str: " + testGobbo.getStrength() + " (" + (testGobbo.getStrength()-10)/2 + ")\n" +
                "Dex: " + testGobbo.getDexterity() + " (" + (testGobbo.getDexterity()-10)/2 + ")\n" +
                "Con: " + testGobbo.getConstitution() + " (" + (testGobbo.getConstitution()-10)/2 + ")\n" +
                "HP: " + testGobbo.getHealth() + "/"+ testGobbo.getMaxHp() + "\n" +
                "AC: " + testGobbo.getArmorClass() + "\n";

        assertEquals(output, testGobbo.toString(), "Goblin.toString Fail");
    }

    @AfterEach
    void tearDown() {
    }
}