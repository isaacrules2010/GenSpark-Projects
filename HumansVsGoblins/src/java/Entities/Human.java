package Entities;

import Equipment.Armor;
import Equipment.Item;
import Equipment.Weapon;
import Equipment.Potion;

import java.util.Random;

public class Human extends Creature {

    private Weapon weapon;
    private Armor armor;

    public Human(){
        super();
        setSymbol(player);
        weapon = new Weapon();
        armor = new Armor();
    }

    //adds proficiency bonus (+2) to toHit roll and critical hits!!!
    public int rollToHit(){
        Random d20 = new Random();
        int roll = d20.nextInt(20)+1;
        int toHit = (roll) + ((strength - 10) / 2) + 2;

        //sets roll arbitrarily high to indicate Crit
        if(roll == 20){
            toHit = 99;
        }

        return toHit;
    }

    //adds critical hit logic & heal on kill
    public void attack(Creature target) {
        Random d4 = new Random();
        int toHit = rollToHit();
        int strScore = (strength - 10) / 2;

        int targetHealth = target.getHealth();
        int atk = (d4.nextInt(4) + 1 + strScore) + weapon.getBaseDmg();

        if(toHit >= target.getArmorClass() + 5){
            atk += 4;
            System.out.println("Critical Hit! You did "+(strScore+atk)+" damage!");
            targetHealth = targetHealth - (strScore + atk);
        }
        else if(toHit >= target.armorClass){
            System.out.println("You hit for "+(strScore+atk)+" damage!");
            targetHealth = targetHealth - (strScore + atk);
        }
        else{
            System.out.println("Whiff! You missed!");
        }
        target.setHealth(targetHealth);
        if(target.getHealth() == 0){
            healAll();
        }
    }

    public void heal(){
        Random d4 = new Random();
        setHealth(health + (d4.nextInt(4)+1));
    }

    public void healAll(){
        health = maxHp;
    }

    public void pickUp(Item item){
        if(item instanceof Weapon){
            if(item.getBaseDmg() > weapon.getBaseDmg()){
                System.out.println("You picked up a new weapon!");
                weapon = (Weapon)item;
            }
            else{
                System.out.println("Ehh... you look at the item on the ground, but it doesn't look better than your current equipment.\n" +
                        "You decide to leave it on the forest floor... surely that won't come back to haunt you.");
            }
        }
        else if(item instanceof Armor){
            if(item.getBaseArmor() > armor.getBaseArmor()){
                System.out.println("You picked up new armor!");
                armor = (Armor)item;
                armorClass = armor.getBaseArmor() + ((dexterity - 10) / 2);
            }
            else{
                System.out.println("Ehh... you look at the item on the ground, but it doesn't look better than your current equipment.\n" +
                        "You decide to leave it on the forest floor... surely that won't come back to haunt you.");
            }
        }
        else{
            System.out.println("You've picked up a "+item.toString());
            Potion potion = (Potion)item;
            setStrength(strength+potion.getStrAdd());
            setDexterity(dexterity+potion.getDexAdd());
            setConstitution(constitution+potion.getConAdd());
            setMaxHP();
            armorClass = armor.getBaseArmor() + ((dexterity - 10) / 2);
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public String toString(){
        return  "Str: " + strength + " (" + (strength-10)/2 + ")\n" +
                "Dex: " + dexterity + " (" + (dexterity-10)/2 + ")\n" +
                "Con: " + constitution + " (" + (constitution-10)/2 + ")\n" +
                "HP: " + health + "/"+ maxHp + "\n" +
                "AC: " + armorClass + "\n" +
                "Currently Equipped: \n" +
                weapon.toString() +
                armor.toString();
    }
}
