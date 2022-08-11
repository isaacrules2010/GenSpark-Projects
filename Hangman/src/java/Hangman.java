import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    private final ArrayList<String> words;
    private String word;
    private String wordDisplay;
    private String missed = "";
    private String guesses ="";
    private Scanner userInput;
    private int misses;
    private boolean isGameOver = false;

    public Hangman(){
        words = new ArrayList<>();
        words.add("super");
        words.add("cat");
        words.add("magazine");
        words.add("pernicious");
        words.add("hangman");
        words.add("code");
        words.add("interview");
        words.add("hello");
        words.add("world");
        words.add("dog");
        words.add("genspark");
        words.add("job");
        words.add("dope");
        words.add("school");
    }

    public void playGame(){
        chooseWord();
        isGameOver = false;
        misses = 0;
        missed = "";
        guesses = "";

        System.out.println("H A N G M A N");
        buildGallows();
        System.out.println("missed letters:");
        System.out.println(wordDisplay);
       do{
           char userGuess = getInputFromStream();
           checkGuess(word, userGuess);
        }
       while(!isGameOver && misses < 5);
        GameOver();
    }
    //*
    public static void main(String[] args){
        Hangman game = new Hangman();
        game.setScanner(System.in);
        game.playGame();
    }
    //*/

    public void setScanner(InputStream userInput){
        this.userInput = new Scanner(userInput);
    }

    public char getInputFromStream(){
        System.out.println("Guess a letter");
        try{
            //try things
            while(!userInput.hasNext("[A-Za-z]+")){
                System.out.println("Please guess a letter.");
                userInput.next();
            }
            String tmp = userInput.nextLine();
            if(tmp.length() > 1)
                System.out.println("Only taking first character... \nTry only guessing ONE letter next time...");
            return Character.toLowerCase(tmp.charAt(0));
        }
        catch(Exception e){
            System.out.println("Something went wrong.");
            return '?';
        }
    }

    public int checkGuess(String word, char guess){
        boolean guessInGuesses = (guesses.indexOf(guess)) != -1;
        boolean guessInWord = (word.indexOf(guess)) != -1;
        if(guessInGuesses){
            System.out.println("You've already guessed \"" + guess + "\"");
            return 1;
        }
        if(!guessInWord){
            missed += guess+" ";
            misses++;
            buildGallows();
        }
        else{
            for(int i = 0; i < word.length(); i++){
                if (word.charAt(i) == guess && wordDisplay.charAt(i) != guess) {

                    wordDisplay = wordDisplay.replaceAll("_ ", "_");
                    String tmp;
                    tmp = wordDisplay.substring(0, i) + guess + wordDisplay.substring(i + 1);
                    tmp = tmp.replaceAll("_", "_ ");
                    wordDisplay = tmp;
                }
            }
        }
        if(wordDisplay.contains(word)){
            System.out.println("Yes! The secret word is \""+word+"\"! You win!");
            isGameOver = true;
            return 2;
        }
        System.out.println("missed letters:" + missed);
        System.out.println(wordDisplay);
        guesses += guess;
        return 0;
    }

    private void chooseWord(){
        Random r = new Random();
        word = words.get(r.nextInt(this.words.size()));
        word = word.toLowerCase();
        wordDisplay = word.replaceAll("[a-z]", "_ ");
    }

    public void setWord(String word){
        this.word = word.toLowerCase();
        wordDisplay = this.word.replaceAll("[a-z]", "_ ");
    }

    private void buildGallows(){
        switch (misses){
            case 0:
                System.out.println("" +
                        "+---+\t\tyou have "+ (5-misses)+" mistakes left\n" +
                        "    |\n" +
                        "    |\n" +
                        "    |\n" +
                        "    |\n" +
                        "=====\n");
                break;
            case 1:
                System.out.println("" +
                        "+---+\t\tyou have "+ (5-misses)+" mistakes left\n" +
                        "O   |\n" +
                        "    |\n" +
                        "    |\n" +
                        "    |\n" +
                        "=====\n");
                break;
            case 2:
                System.out.println("" +
                        "+---+\t\tyou have "+ (5-misses)+" mistakes left\n" +
                        "O   |\n" +
                        "Y   |\n" +
                        "    |\n" +
                        "    |\n" +
                        "=====\n");
                break;
            case 3:
                System.out.println("" +
                        "+---+\t\tyou have "+ (5-misses)+" mistakes left\n" +
                        "O   |\n" +
                        "Y   |\n" +
                        "|   |\n" +
                        "    |\n" +
                        "=====\n");
                break;
            case 4:
                System.out.println("" +
                        "+---+\t\tyou have "+ (5-misses)+" mistakes left\n" +
                        "O   |\n" +
                        "Y   |\n" +
                        "|   |\n" +
                        "^   |\n" +
                        "=====\n");
                System.out.println("You can still cut the rope!");
                break;
            default:
                System.out.println("" +
                        "Y O U  A R E  D E A D\n" +
                        "--G A M E  O V E R--");
                isGameOver = true;
        }
    }
    private void GameOver(){
        System.out.println("Do you want to play again? (yes or no)");
        if(getUserAck()){
            userInput.nextLine();
            playGame();
        }
    }
    public boolean getUserAck(){
        try{
            while(!userInput.hasNext("yes") && !userInput.hasNext("no")) {
                System.out.println("Please just say yes or no.");
                userInput.next();
            }
            String tmp = userInput.next();
            return tmp.contains("yes");
        }
        catch(Exception failedAck){

        }
        return false;
    }
}
