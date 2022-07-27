import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner userInput = new Scanner(System.in);
    static Random random = new Random();
    public static void main(String[] args){
        System.out.println("Hello! What is your name?\n");
        String userName = userInput.nextLine();
        PlayGame(userName);
    }

    private static void PlayGame(String userName){
        char userAck;
        System.out.printf("Well, %s, I am thinking of a number between 1 and 20.\n", userName);
        int num = random.nextInt(19) + 1;
        int guess;
        int guessCount = 0;

        while(guessCount < 6){
            System.out.println("Take a guess.\n");
            guess = userInput.nextInt();
            guessCount++;
            if(guess > num){
                System.out.println("Your guess is too high.");
            }
            else if(guess < num){
                System.out.println("Your guess is too low.");
            }
            else {
                System.out.printf("Nice job, %s! You guessed my number in %d guesses!\n" +
                        "Would you like to play again? y or n\n", userName, guessCount);
                userAck = userInput.next().charAt(0);
                if (userAck == 'y')
                    PlayGame(userName);
                else if (userAck == 'y')
                    return;
                else {
                    System.out.println("If you're not gonna follow basic instructions then I don't want to play with you anyway.");
                    return;
                }
            }
        }
        System.out.printf("Sorry. You only had 6 guesses. Better luck next time, %s!\n" +
                "Would you like to play again? y or n\n", userName);
        userAck = userInput.next().charAt(0);;
        if (userAck == 'y')
            PlayGame(userName);
        else if (userAck == 'n')
            return;
        else {
            System.out.println("If you're not gonna follow basic instructions then I don't want to play with you anyway.");
            return;
        }
    }
}
