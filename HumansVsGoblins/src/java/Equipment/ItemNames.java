package Equipment;

import java.util.ArrayList;
import java.util.Random;

public class ItemNames {
    private ArrayList<String> armorMats = new ArrayList<>(){{
        add("Leather ");
        add("Chain ");
        add("Plate ");
        add("Adamantine ");
    }};

    private ArrayList<String> weaponMats = new ArrayList<>(){{
        add("Iron ");
        add("Steel ");
        add("Ebony ");
        add("Cloth ");
    }};

    private ArrayList<String> weaponTypes = new ArrayList<>(){{
        add("Dagger");
        add("Sword");
        add("GreatSword");
        add("Axe");
        add("Staff");
        add("Stick");
        add("Rod");
        add("BattleAxe");
        add("Claws");
    }};

    private ArrayList<String> modifiers = new ArrayList<>(){{
       add(" of the Goblin Slayer");
       add(" of Fighting");
       add(" of Television Binging");
       add(" of Destruction");
       add(" of the Dragon");
       add(" of protection from Evil");
       add(" of Truth");
    }};

    public String getWeaponMat(){
        Random mat = new Random();
        return weaponMats.get(mat.nextInt(weaponMats.size()-1));
    }

    public String getWeaponType(){
        Random mat = new Random();
        return weaponTypes.get(mat.nextInt(weaponTypes.size()-1));
    }

    public String getArmorMat(){
        Random mat = new Random();
        return armorMats.get(mat.nextInt(armorMats.size()-1));
    }

    public String getModifier(){
        Random mod = new Random();
        return modifiers.get(mod.nextInt(modifiers.size()-1));
    }
}
