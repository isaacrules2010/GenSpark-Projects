package Entities;

import Equipment.Armor;
import Equipment.Potion;
import Equipment.Weapon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest {

    Human testHuman;
    @BeforeEach
    void setUp() {
        testHuman = new Human();
    }

    @Test
    void rollToHit() {
        int test = testHuman.rollToHit();
        assertTrue(test <= 20 || test == 99, "rollTOHit Failed");
    }

    @Test
    void attack() {
        Goblin enemy = new Goblin(new int[]{0,0}, 0);

        int oldHp = enemy.getHealth();

        testHuman.attack(enemy);
        testHuman.attack(enemy);
        testHuman.attack(enemy);
        testHuman.attack(enemy);
        testHuman.attack(enemy);
        testHuman.attack(enemy);
        testHuman.attack(enemy);
        testHuman.attack(enemy);
        testHuman.attack(enemy);
        testHuman.attack(enemy);
        testHuman.attack(enemy);
        testHuman.attack(enemy);

        int newHp = enemy.getHealth();
        assertTrue(oldHp > newHp, "Human attack Failed");

    }

    @Test
    void heal() {
        testHuman.setHealth(0);
        testHuman.heal();
        assertTrue(testHuman.getHealth() > 0, "Human healing failed");
    }

    @Test
    void healAll() {
        testHuman.setHealth(0);
        testHuman.healAll();
        assertTrue(testHuman.getHealth() == testHuman.getMaxHp(), "Human healAll() Failed");
    }

    @Test
    void pickUp() {
        Weapon testWeaponGood = new Weapon(20);
        Weapon testWeaponBad = new Weapon();
        Armor testArmorGood = new Armor(20);
        Armor testArmorBad = new Armor();
        Potion testPotion = new Potion(5, 0, 0);

        testHuman.pickUp(testWeaponGood);
        testHuman.pickUp(testArmorGood);
        testHuman.pickUp(testPotion);

        assertTrue(testHuman.getWeapon() == testWeaponGood, "pickup WEAP_BETTER fail");
        assertTrue(testHuman.getArmor() == testArmorGood, "pickup ARMO_BETTER Fail");
        assertTrue(testHuman.getStrength() == 15, "pickup POTION Fail");

        testHuman.pickUp(testWeaponBad);
        testHuman.pickUp(testArmorBad);

        assertTrue(testHuman.getWeapon() == testWeaponGood, "pickup WEAP_WORSE fail");
        assertTrue(testHuman.getArmor() == testArmorGood, "pickup ARMO_WORSE Fail");
    }

    @Test
    void testToString() {
        String output = "Str: " + testHuman.getStrength() + " (" + (testHuman.getStrength()-10)/2 + ")\n" +
                "Dex: " + testHuman.getDexterity() + " (" + (testHuman.getDexterity()-10)/2 + ")\n" +
                "Con: " + testHuman.getConstitution() + " (" + (testHuman.getConstitution()-10)/2 + ")\n" +
                "HP: " + testHuman.getHealth() + "/"+ testHuman.getMaxHp() + "\n" +
                "AC: " + testHuman.getArmorClass() + "\n"+
                "Currently Equipped: \n" +
                testHuman.getWeapon().toString() +
                testHuman.getArmor().toString();

        assertEquals(output, testHuman.toString(), "Goblin.toString Fail");
    }

    @AfterEach
    void tearDown() {
    }
}