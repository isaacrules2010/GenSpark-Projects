package World;

public class Land extends Unit{

    public Land(int[] position){
        setPosition(position);
        setSymbol(empty);
    }

    public String toString(){
        return "x: "+getPosition()[0]+"\n"+
                "y: "+getPosition()[1]+"\n"+
                "symbol: "+getSymbol();
    }
}
