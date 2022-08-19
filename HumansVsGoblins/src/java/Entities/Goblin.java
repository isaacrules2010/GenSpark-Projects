package Entities;

import Equipment.Armor;
import Equipment.Item;
import Equipment.Potion;
import Equipment.Weapon;

import java.util.Random;

public class Goblin extends Creature {

    private Item drop;
    private boolean inCombat;
    public Goblin(int[] position, int round){
        super(8, 14, 10);
        setMaxHP(2);
        setHealth(maxHp);
        setSymbol(goblin);
        setPosition(position);
        generateDrop(round);
    }

    public void setMaxHP(int num) {
        maxHp = 0;
        Random d6 = new Random();
        for(int i = 1; i <= num; i++){
            maxHp += (d6.nextInt(5)+1);
        }
        maxHp += (getConstitution() - 10) / 2;
    }

    public void attack(Creature target) {
        inCombat = true;
        Random d4 = new Random();
        int toHit = rollToHit();
        int strScore = (strength - 10) / 2;

        int targetHealth = target.getHealth();

        if(toHit >= target.armorClass){
            int atk = d4.nextInt(3) + 1 + drop.getBaseDmg();
            System.out.print("The Creature hit");
            if((strScore + atk) <= 0)
                System.out.println(", but was too weak to do any damage...");
            else System.out.println(" for "+(strScore+atk)+" damage!");
            targetHealth = targetHealth - (strScore + atk);
        }
        else{
            System.out.println("Whiff! The Creature missed!");
        }
        target.setHealth(targetHealth);
    }

    private void generateDrop(int round){
        Random dropGen = new Random();
        switch(dropGen.nextInt(3)){
            case 0:
                drop = new Weapon(dropGen.nextInt(4)+1+round);
                break;
            case 1:
                drop = new Armor(dropGen.nextInt(4)+1+round);
                armorClass = drop.getBaseArmor() + ((dexterity - 10) / 2);
                break;
            case 2:
                Potion potion;
                if(round > 0)
                    potion = new Potion(round);
                else {
                    potion = new Potion();
                }
                drop = potion;
                setStrength(strength+potion.getStrAdd());
                setDexterity(dexterity+potion.getDexAdd());
                setConstitution(constitution+potion.getConAdd());
                setMaxHP();
                armorClass = drop.getBaseArmor() + ((dexterity - 10) / 2);
        }
    }

    public Item getDrop(){
        return drop;
    }

    public boolean isInCombat(){
        return inCombat;
    }
    public void pickUp(Item item){
        if(item.getBaseDmg() > drop.getBaseDmg() || item.getBaseArmor() > drop.getBaseArmor()){
            drop = item;
        }
    }

    public String toString(){
        return  "Str: " + strength + " (" + (strength-10)/2 + ")\n" +
                "Dex: " + dexterity + " (" + (dexterity-10)/2 + ")\n" +
                "Con: " + constitution + " (" + (constitution-10)/2 + ")\n" +
                "HP: " + health + "/"+ maxHp + "\n" +
                "AC: " + armorClass + "\n";
    }

    public void readyForCombat() {
        inCombat = false;
    }
}
