import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hangman {

    private ArrayList<String> words = new ArrayList<>(){{
        add("super");
        add("cat");
        add("magazine");
        add("pernicious");
        add("hangman");
        add("code");
        add("interview");
        add("hello");
        add("world");
        add("dog");
        add("genspark");
        add("job");
        add("dope");
        add("school");
    }};

    private String userName;        //name of the player

    Scanner userInput;              //reads user input from console

    private String word = "";       // the selected word the player is trying to guess
    private String wordDisplay = "";// the display that shows what letters are still 'missing' from the chosen word
    private String missed = "";     // the letters that the player has guessed that weren't in the selected word
    private String guesses = "";    // the letters that the player has guessed
    private int guessCount = 0;     // how many guesses the player has made
    boolean playing = true;         // is the player playing the game?
    private File displayFile;       //Hangman_Display.txt

    private File scoreFile;
    private FileWriter scoreWriter; //writes the usernames and scores to the file
    private int gameScore = 0;
    private int totalScore = 0;

    public static void main(String[] args){
        /*
        * CONSTRAINTS AND EXPECTATIONS:
        * - Single loop
        * - all processing done through streams
        * - read file containing names and scores "Scores.txt"
        * - write to file player name and score
        * - Hangman 'art' must be read from file "Hangman_Display.txt"
        */
        System.out.println("|||||||||||||||||||||");
        System.out.println("||| H A N G M A N |||");
        System.out.println("|||||||||||||||||||||\n");
        Hangman game = new Hangman();
        game.initDisplay();
        game.initScores();
        game.initInput();

        System.out.println("What is your name?");
        System.out.printf("Okay, %s, let's play!\n\n", game.getUserNameFromStream());
        game.playGame();
    }

    private void playGame() {
        word = chooseWord();
        missed = "";
        guesses = "";
        guessCount = 0;
        gameScore = 0;
        playing = true;
        //print gallows
        System.out.println(gallows(guessCount));
        while(playing) {
            if (word.equals(hideWord(word, guesses)) && guessCount < 7)
            {
                System.out.println("Congrats! You guessed the word!");
                gameScore = word.length() - guessCount;
                if(gameScore < 0)
                    gameScore = 1;
                playing = false;
            }
            else if(guessCount >= 7){
                //do other things
                System.out.println(
                        "||||||||||||||||||||||\n" +
                        "||| Y O U  D I E D |||\n" +
                        "||||||||||||||||||||||\n" +
                        "\n" +
                        "The word was... " + word);
                gameScore = 0;
                playing = false;
            }
            else {
                System.out.println("guessCount: "+guessCount);
                System.out.println("misses: "+missed);
                //System.out.println("guesses: "+guesses);
                System.out.println("Guess a letter.");
                checkGuess(getCharFromStream());
                System.out.println(gallows(guessCount));
            }
        }
        System.out.println("\nWould you like to play again? (y or n)");
        playAgain(getCharFromStream());
    }

    public void initDisplay(){
        System.out.println("Searching for \"Hangman_Display.txt\"...");
        try{
            displayFile = new File("Hangman_Display.txt");
            System.out.println("Successfully found \"Hangman_Display.txt\"");
        }
        catch(Exception e){
            System.out.println("Error finding art file");
        }
    }

    public void initScores(){
        System.out.println("Searching for scores file...");
        try{
            scoreFile = new File("Scores.txt");
            if(scoreFile.isFile()){
                System.out.println("Successfully found \"Scores.txt\"");
            }
            else{
                throw new Exception("HavinIsshus");
            }
        }
        catch(Exception e){
            System.out.println("Error finding scores file");
        }
    }

    public void initInput(){
        System.out.println("Setting up user input...");
        try{
            userInput = new Scanner(System.in);
        }
        catch(Exception e){
            System.out.println("Error getting user input");
        }
    }

    private String chooseWord(){
        Random num = new Random();
        return words.get(num.nextInt(words.size()));
    }
    private String hideWord(String word, String guessed){
        String output = word.chars()
                .mapToObj(c -> (char) c)
                .map(letter -> guessed.indexOf(letter)==-1?'*':letter)
                .map(Object::toString)
                .collect(Collectors.joining());
        System.out.println(output);
        return output;
    }

    private String gallows(int guessCount){
        String output = "";
        try {
            String FULL_DISPLAY = Files.readString(displayFile.toPath());
            String tmp = Arrays.stream(FULL_DISPLAY.split("guesses"))
                    .filter(e-> e.contains(Integer.toString(guessCount)))
                    .reduce("",(a,b) -> a+b);
            output += tmp.substring(1,tmp.length()-3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return output;
    }

    private void appendPlayer(String name, int score){
       try{
           Files.writeString(scoreFile.toPath(), name + " - " + score + "\n", StandardOpenOption.APPEND);
       }
       catch(Exception e){
           System.out.println("Failed to record score.");
       }
    }
    private int getTopScore(){
        try{
            int topScore = Files.readAllLines(scoreFile.toPath()).stream()
                    .map(line->line.split(" ", 3)[2])
                    .map(strNum -> Integer.valueOf(strNum))
                    .filter(a -> a > totalScore)
                    .mapToInt(num -> num)
                    .max()
                    .orElse(0);
            if(topScore > totalScore){
                System.out.println("Sorry... you dont have the top score... you have to beat: "+topScore);
            }
            else{
                System.out.println("Congrats!!! You have the new High score of: "+totalScore);
                topScore = totalScore;
            }
            return topScore;
        }
        catch(Exception e) {
            System.out.println("Something went wrong fetching top scores...");
            return 0;
        }
    }

    private void playAgain(char ack){
        totalScore+=gameScore;
        if(ack == 'y'){
            playGame();
        }
        else{
            System.out.println("Okay, goodbye...");
            appendPlayer(userName, totalScore);
            getTopScore();
        }
    }

    public char getCharFromStream(){
        //System.out.println("Guess a letter");
        char guessedLetter;
        try{
            if(userInput.hasNext("[A-Za-z]")){
                String tmp = userInput.nextLine();
                guessedLetter =  Character.toLowerCase(tmp.charAt(0));
            }
            else{
                userInput.nextLine();
                throw new Exception("NotALetter");
            }
        }
        catch(Exception e){
            System.out.println("Invalid Input");
            return '?';
        }
        return guessedLetter;
    }

    public String getUserNameFromStream(){
        String input;
        try{
            input = userInput.nextLine();
        }
        catch(Exception e){
            System.out.println("Error getting username...");
            input = "John Smith";
        }
        userName = input;
        return input;
    }

    public void checkGuess(char guess){
        try{
            if(guess == '?'){
                // do nothing... not even acknowledging non-letter guesses
            }
            else{
                if(guesses.indexOf(guess) == -1){ //if not already guessed
                    guesses+=guess;
                    //if not in word
                    missed = guesses.chars()
                            .mapToObj(c -> (char) c)
                            .filter(letter -> word.indexOf(letter) == -1)
                            .map(Object::toString)
                            .collect(Collectors.joining());
                    if(missed.indexOf(guess) != -1) //if not in word, increment guess count
                        guessCount++;
                }
                else{
                    System.out.println("You've already guessed that!");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error checking guess.");
        }
    }

    public int getGuessCount() {
        return guessCount;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setGuessCount(int guessCount) {
        this.guessCount = guessCount;
    }

    public String getGuesses() {
        return guesses;
    }

    public int getGameScore() {
        return gameScore;
    }

    public void setGuesses(String guesses) {
        this.guesses = guesses;
    }

    public void setUserInput(InputStream is) {
        this.userInput = new Scanner(is);
    }
}