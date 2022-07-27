import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner userInput = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Hello! What is your name?\n");
        String userName = userInput.nextLine();
        System.out.printf("Well, %s, I am thinking of a number between 1 and 20.\nTake a guess.\n", userName);
        int num = random.nextInt(19) + 1;
        int guess = userInput.nextInt();
        int guessCount = 0;

        while(guessCount < 6){
            guessCount++;

            if(guess > num){
                System.out.println("Your guess is too high.\nTake a guess.");
                guess = userInput.nextInt();
            }
            else if(guess < num){
                System.out.println("Your guess is too low.\nTake a guess.");
                guess = userInput.nextInt();
            }
            else {
                System.out.printf("Nice job, %s! You guessed my number in %d guesses!", userName, guessCount);
                return;
            }
        }
        System.out.printf("Sorry. You only had 6 guesses. Better luck next time, %s! SCrew you!!!", userName);
    }
}
