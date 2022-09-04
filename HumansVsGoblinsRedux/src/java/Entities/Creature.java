package Entities;

import Equipment.Item;
import World.Unit;

import java.util.Random;

public abstract class Creature extends Unit {
    //determines attack damage
    protected int strength;
    protected int maxStr = 20;
    //determines how easy the creature is to hit
    protected int dexterity;
    protected int maxDex = 20;
    //determines jow much health the creature has
    protected int constitution;
    protected int maxCon = 20;

    protected int health;
    protected int maxHp;
    protected int armorClass;


    public Creature(){
        strength = 10;
        dexterity = 10;
        constitution = 10;
        setPosition(new int[] {0,0});

        maxHp = (10 + ((this.constitution - 10) / 2)); //default 10
        health = maxHp;
        armorClass = (10 + ((dexterity - 10) / 2)); //default 10
    }

    public Creature(int str, int dex, int con){
        strength = str;
        dexterity = dex;
        constitution = con;
        setPosition(new int[]{0,0});

        maxHp = (10 + ((this.constitution - 10) / 2)); //default 10
        health = maxHp;
        armorClass = (10 + ((dexterity - 10) / 2)); //default 10
    }

    public int rollToHit(){
        Random die = new Random();
        int d20 = die.nextInt(19) + 1;
        int strScore = (strength - 10) / 2;
        return d20 + strScore;
    }

    //attacks target creature
    public abstract String attack(Creature target);

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getHealth() {
        return this.health;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getArmorClass() {
        return armorClass;
    }

    //sets strength and returns the new value. Ensures Base strength is always [1-20]
    public int setStrength(int strength){
        if(strength < 1) {
            this.strength = 1;
        }
        else if(strength > this.maxStr) {
            this.strength = this.maxStr;
        }
        else {
            this.strength = strength;
        }
        return this.strength;
    }

    //sets dexterity and returns the new value. Ensures Base dexterity is always [1-20]
    public int setDexterity(int dexterity){
        if(dexterity < 1) {
            this.dexterity = 1;
        }
        else if(dexterity > this.maxDex) {
            this.dexterity = this.maxDex;
        }
        else {
            this.dexterity = dexterity;
        }
        armorClass = 10 +(this.dexterity - 10) / 2;
        return this.dexterity;
    }

    //sets dexterity and returns the new value. Ensures Base dexterity is always [1-20]
    public int setConstitution(int constitution){
        if(constitution < 1) {
            this.constitution = 1;
        }
        else if(constitution > maxCon) {
            this.constitution = maxCon;
        }
        else {
            this.constitution = constitution;
        }
        this.setMaxHP();
        return this.constitution;
    }

    public void setHealth(int health){
        if(health < 0) {
            this.health = 0;
        }
        else if(health > maxHp) {
            this.health = maxHp;
        }
        else {
            this.health = health;
        }
    }

    public void setMaxHP() {
        this.maxHp = 10 + (this.constitution - 10) / 2;
    }

    public abstract String pickUp(Item item);

    @Override
    public String toString(){
        return  "Creature - \n"+
                "Str: " + strength + " (" + (strength-10)/2 + ")\n" +
                "Dex: " + dexterity + " (" + (dexterity-10)/2 + ")\n" +
                "Con: " + constitution + " (" + (constitution-10)/2 + ")\n" +
                "HP: " + health + "/"+ maxHp + "\n" +
                "AC: " + armorClass + "\n";
    }
}
