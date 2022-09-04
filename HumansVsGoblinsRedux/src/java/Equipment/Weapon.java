package Equipment;

import java.util.Random;

public class Weapon extends Item{

    private ItemNames naming = new ItemNames();
    public Weapon() {
        super();
        setName("Common Dagger");
        setBaseArmor(0);
        setBaseDmg(0);
    }

    public Weapon(int bonus){
        super();
        setName(naming.getWeaponMat()+naming.getWeaponType()+naming.getModifier());
        setBaseArmor(0);
        setBaseDmg(bonus);
    }

    public String toString(){
        return "Name: "+getName()+"\n" +
                "Damage: "+getBaseDmg()+"\n";
    }
}
