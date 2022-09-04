package Equipment;

import World.Unit;

public class Item extends Unit {
    private String name;
    private int baseDmg;
    private int baseArmor;

    public Item(){
        setSymbol('D');
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBaseDmg(int baseDmg) {
        this.baseDmg = baseDmg;
    }

    public int getBaseDmg() {
        return baseDmg;
    }

    public void setBaseArmor(int baseArmor) {
        this.baseArmor = baseArmor;
    }

    public int getBaseArmor() {
        return baseArmor;
    }
}
