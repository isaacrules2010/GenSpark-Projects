import java.util.Random;
import java.util.Scanner;

public class DragonCave {
    private static final Random num = new Random();
    //clean up cave gen
    private int winCave;
    private int playerChoice;

    //*
    public static void main(String[] args){
        DragonCave game = new DragonCave();
        game.playGame();
    }
    //*/
    public int playGame(){

        //Intro Sequence
        System.out.println("You are in a land full of Dragons.\n" +
                "In front of you, you see two caves.\n" +
                "In one cave, the Dragon is friendly and will share his treasure with you...\n" +
                "The other Dragon is greedy and hungry and will eat you on sight.\n" +
                "Which cave will you go into? (1 or 2)");
        setCave(num.nextInt(2)+1);
        setPlayerChoice();
        playerWin();
        return 0;
    }

    //Did the player win? let's find out.
    public int playerWin() {
        System.out.println("You approach the cave...\n" +
                "It is Dark and spooky...\n" +
                "A large dragon jumps out in front of you! He opens his jaws and...");
        if(this.playerChoice == this.winCave) {
            System.out.println("Offers you some of his Treasure!");
            return 1;
        }
        else {
            System.out.println("Gobbles you down in one bite!");
            return 0;
        }
    }
    //sets winCave
    public void setCave(int cave){
        this.winCave = cave;
    }
    //sets playerChoice to input from InputStream
    public int setPlayerChoice() {
        try{
            Scanner userInput = new Scanner(System.in);
            this.playerChoice = Integer.parseInt(userInput.next());
            if(!(this.playerChoice > 0 && this.playerChoice <3)){
                throw new Exception("wrongChoice");
            }
            return 0;
        }
        catch(Exception wrongChoice){
            System.out.println("That clearly didn't work...\n" +
                    "Since you don't want to play by the rules, I'll choose for you!\n\n" +
                    "Okay... and... DONE.\n" +
                    "Let's hope you're lucky, yeah?\n\n");
            this.playerChoice = 1;
            return 1;
        }
    }
}
