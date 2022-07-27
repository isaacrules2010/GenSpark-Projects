import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner userInput = new Scanner(System.in);
        Random num = new Random();

        //Intro Sequence
        System.out.println("You are in a land full of Dragons.\n" +
                "In front of you, you see two caves.\n" +
                "In one cave, the Dragon is friendly and will share his treasure with you...\n" +
                "The other Dragon is greedy and hungry and will eat you on sight.\n" +
                "Which cave will you go into? (1 or 2)");

        //generate "winning" cave and "losing" cave
        int caveGen = num.nextInt(), winCave, loseCave;
        if(caveGen%2 == 0){
            winCave = 1;
            loseCave = 2;
        }
        else {
            winCave = 2;
            loseCave = 1;
        }

        //User Input and win/lose
        int input = userInput.nextInt();

        while (input  != winCave && input != loseCave){
            System.out.println("You chose: " + input + "\n...Please choose 1 or 2...\n");
            input = userInput.nextInt();
        }

        System.out.println("You approach the cave...\n" +
                "It is Dark and spooky...\n" +
                "A large dragon jumps out in front of you! He opens his jaws and...");
        if(input == winCave){
            //win text
            System.out.println("Offers you some of his Treaure!");
        }
        else if (input == loseCave) {
            //lose condition
            System.out.println("Gobbles you down in one bite!");
        }
        else {
            System.out.println("Asks you how you even found a third cave...\n" +
                    "There's only two; this shouldn't even be an option.");
        }
    }
}
