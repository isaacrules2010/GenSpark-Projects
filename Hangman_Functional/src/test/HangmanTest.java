import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class HangmanTest {
    Hangman testGame;

    @BeforeEach
    void setUp() {
        testGame = new Hangman();
    }

    @Test
    void getCharFromStream() {
        String testInput = "a";
        testGame.setUserInput(new ByteArrayInputStream(testInput.getBytes()));
        assertEquals(testInput.charAt(0), testGame.getCharFromStream(), "getCharFailed");
    }

    @Test
    void getUserNameFromStream() {
        String testInput = "isaac";
        testGame.setUserInput(new ByteArrayInputStream(testInput.getBytes()));
        assertEquals(testInput, testGame.getUserNameFromStream(), "getUserName Failed");
    }

    @Test
    void checkGuess() {
        testGame.setWord("cat");
        String testInput = "a";
        String testInput2 = "b";
        testGame.setUserInput(new ByteArrayInputStream(testInput.getBytes()));
        testGame.checkGuess(testGame.getCharFromStream());
        assertEquals(testInput, testGame.getGuesses(), "Failed to append new guess");
        testGame.setUserInput(new ByteArrayInputStream(testInput.getBytes()));
        testGame.checkGuess(testGame.getCharFromStream());
        assertEquals(testInput, testGame.getGuesses(), "Failed to prevent double guesses");
        testGame.setUserInput(new ByteArrayInputStream(testInput2.getBytes()));
        testGame.checkGuess(testGame.getCharFromStream());
        assertEquals(testInput+testInput2, testGame.getGuesses(), "Failed to append new guess");
    }

    @Test
    void getGuessCount() {
        testGame.setWord("cat");
        testGame.checkGuess('b');
        assertEquals(1, testGame.getGuessCount(), "failed to increment failed guesses");
        testGame.checkGuess('a');
        assertEquals(1, testGame.getGuessCount(), "failed to verify correct guess");
    }

    @AfterEach
    void tearDown() {
    }
}