package Equipment;

public class Armor extends Item{

    private ItemNames naming = new ItemNames();

    public Armor(){
        super();
        setName("Common Clothes");
        setBaseArmor(10);
        setBaseDmg(0);
    }

    public Armor(int bonus){
        super();
        setName(naming.getArmorMat()+"Armor"+naming.getModifier());
        setBaseArmor(10+bonus);
        setBaseDmg(0);
    }

    public String toString(){
        return "Name: "+getName()+"\n" +
                "Armor: "+getBaseArmor()+"\n";
    }
}
