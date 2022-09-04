package Equipment;

import java.util.Random;

public class Potion extends Item {

    private int strAdd;
    private int dexAdd;
    private int conAdd;

    public Potion(){
        super();
        setName("Potion of Stat Increase");
        setBaseArmor(0);
        setBaseDmg(0);
        strAdd = 0;
        dexAdd = 0;
        conAdd = 0;
        generatePotion(0);
    }

    public Potion(int str, int dex, int con){
        super();
        setName("Prized Potion of Stat Increase");
        setBaseArmor(0);
        setBaseDmg(0);
        strAdd = str;
        dexAdd = dex;
        conAdd = con;
    }

    public Potion(int round){
        Random randBonus = new Random();
        setName("Potion of Stat Increase +(" + round +")");
        setBaseArmor(0);
        setBaseDmg(0);
        generatePotion(round);
    }

    public void generatePotion(int round){
        Random effectGen = new Random();
        int whichStat = effectGen.nextInt(2)+1;
        switch(whichStat){
            case 1:
                strAdd = effectGen.nextInt(4)+1+round;
                break;
            case 2:
                dexAdd = effectGen.nextInt(4)+1+round;
                break;
            case 3:
                conAdd = effectGen.nextInt(4)+1+round;
                break;
        }
    }

    public int getStrAdd() {
        return strAdd;
    }

    public int getDexAdd() {
        return dexAdd;
    }

    public int getConAdd() {
        return conAdd;
    }

    public String toString(){
        return getName() +
                "\n---This potion increases your stats by anywhere between 1 and 5 points!\n" +
                "---Could be one, or could be all 3!!!\n" +
                "---(to a max of 20... don't wanna be TOO broken, now do we?)\n";
    }
}
