package World;

public class Unit {
    private int[] position = {0,0};
    private char symbol;

    //"tiles"
    protected char empty = '\u00b1';
    protected char player = '\u0398';
    protected char goblin = '\u029b';

    public int[] getPosition() {
        return position;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
