import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner userInput = new Scanner(System.in);
    static Random random = new Random();
    public static void main(String[] args){
        System.out.println("Hello! What is your name?\n");
        String userName = null;
        userName = GetUserName(userName);
        PlayGame(userName);
        userInput.close();
    }

    private static void PlayGame(String userName){
        System.out.printf("Well, %s, I am thinking of a number between 1 and 20.\n", userName);
        int num = random.nextInt(19) + 1;
        int guess = 0;
        int guessCount = 0;

        while(guessCount < 6){
            System.out.println("Take a guess.\n");
            guess = GetUserGuess(guess);
            guessCount++;
            if(guess > num){
                System.out.println("Your guess is too high.");
            }
            else if(guess < num){
                System.out.println("Your guess is too low.");
            }
            else {
                System.out.printf("Nice job, %s! You guessed my number in %d guesses!\n", userName, guessCount);
                userInput.nextLine();
                PlayAgain(userName);
                return;
            }
        }
        System.out.printf("Sorry. You only had 6 guesses. Better luck next time, %s!\n", userName);
        userInput.nextLine();
        PlayAgain(userName);
        return;
    }

    private static void PlayAgain(String userName){
        String userAck = "";
        System.out.println("Would you like to play again? y or n\n");
        userAck = GetUserAck(userAck);
        if (userAck.length() > 1) {
            System.out.println("Please just use the options I give you...\n");
            PlayAgain(userName);
            return;
        }
        if(userAck.charAt(0) != 'y' && userAck.charAt(0) != 'n'){
            System.out.println("I'd like to take that as a no, but I'll be nice...\n");
            PlayAgain(userName);
        }
        else if (userAck.charAt(0) == 'y')
            PlayGame(userName);
        else if (userAck.charAt(0) == 'n')
            return;
    }
    private static String GetUserName(String userName){
        try{
            userName = userInput.nextLine();
        }
        catch(Exception notString){
            System.out.println("I don't know how you managed it, but here we are... um...\n" +
                    "Let's try again.\n" +
                    "What's your name?\n");
            userName = GetUserName(userName);
        }
        return userName;
    }
    private static int GetUserGuess(int guess){
        try {
            guess = userInput.nextInt();
        }
        catch(Exception notInt){
            System.out.println("I don't know what to do with that... um...\n" +
                    "Let's try again, yeah? I won't even count this one against you.\n" +
                    "Okay? Okay.\n" +
                    "Take a guess.\n");
            userInput.next();
            guess = GetUserGuess(guess);
        }
        return guess;
    }
    private static String GetUserAck(String userAck){
        try{
            userAck = userInput.nextLine();
        }
        catch (Exception whatEven){
            System.out.println("Let's try again.\n" +
                    "Would you like to play again? y or n\n");
            userAck = GetUserAck(userAck);
        }
        return userAck;
    }

}
