import java.util.Random;
import java.util.Scanner;
import java.io.InputStream;

public class GuessTheNumber {
    private static final Random num = new Random();
    private Scanner userInput;
    private int guessCount;
    private String userName;
    private int userGuess;
    private int winNum;
    private boolean playerWin;
    private char userAck;


    public void playGame(){
        //getName
        //getGuesses
        //win_lose
        setGuessCount(0);
        generateWinNum();
        System.out.printf("\nWell, %s, I am thinking of a number between 1 and 20.\nYou only have 6 guesses.\n", userName);
        while (guessCount < 6 && !playerWin){
            getUserGuessFromStream();
            checkGuess();
        }
        //win_lose();
        if(playerWin){
            System.out.printf("Nice Job, %s! You guessed my number in %d guesses!\n", userName, guessCount);
        }
        else{
            System.out.printf("Sorry. You only had 6 guesses. Better luck next time, %s!\n", userName);
        }
        //playAgain();
        getUserAckFromStream();
        if(userAck == 'y'){
            playGame();
        }
    }

    //*
    public static void main(String[] args){
        GuessTheNumber game = new GuessTheNumber();
        game.setScanner(System.in);
        game.getUserNameFromStream();
        game.playGame();
    }
    //*/

    public void setScanner(InputStream userInput){
        this.userInput = new Scanner(userInput);
    }

    public int getUserNameFromStream(){
        System.out.println("Hello! What is your name?\n");
        try{
            userName = userInput.nextLine();
            return 0;
        }
        catch(Exception notString){
            System.out.println("I don't know how you managed it, but here we are... um...\n" +
                    "I'm gonna call you \"MissingNo.\" after that glitched pokemon.\n\n");
            userName = "MissingNo.";
            return 1;
        }
    }

    public int getUserGuessFromStream(){
        System.out.println("Take a guess.\n");
        incGuessCount();
        try{
            userGuess = Integer.parseInt(userInput.next());
            if(!(userGuess <=20 && userGuess >= 1))
                throw new Exception("Out of Bounds");
            return 0;
        }
        catch (Exception notRight){
            System.out.println("\nThat... wasn't an option. So I'm just gonna say you meant 20.");
            userGuess = 20;
            return 1;
        }
    }
    public int getUserAckFromStream(){
        System.out.println("Would you like to play again? y or n\n\n");
        try{
            String tmp = userInput.next();
            if(tmp.length() > 1) {
                throw new Exception("Input Too Long");
            }
            userAck = tmp.charAt(0);
            if(!(userAck == 'y' || userAck == 'n')) {
                throw new Exception("Not An Option");
            }
            return 0;
        }
        catch(Exception notAnOption){
            System.out.println("That wasn't an option, so I'm gonna assume you meant 'n'\n" +
                    "Goodbye!\n");
            setUserAck('n');
            return 1;
        }
    }

    public int checkGuess() {
        if(userGuess < winNum){
            System.out.println("\nYour guess is too low.");
            return 1;
        }
        else if(userGuess > winNum){
            System.out.println("\nYour guess is to high.");
            return 2;
        }
        else {
            setWin(true);
            return 0;
        }
    }
    private void generateWinNum(){
        winNum = num.nextInt(19 + 1);
    }
    public void setWinNum(int winNum){
        this.winNum = winNum;
    }
    public int getWinNum(){
        return winNum;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return this.userName;
    }
    public void setUserAck(char userAck){
        this.userAck = userAck;
    }
    public char getUserAck(){
        return userAck;
    }
    public void setGuessCount(int guessCount){
        this.guessCount = guessCount;
    }
    public void incGuessCount(){
        guessCount = guessCount + 1;
    }

    public int getGuessCount(){
        return guessCount;
    }
    public void setWin(boolean playerWin){
        this.playerWin = playerWin;
    }
    public boolean getWin(){
        return playerWin;
    }
}
