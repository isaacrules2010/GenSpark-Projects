import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;

class HangmanTest {
    Hangman testGame;
    @BeforeEach
    void setUp() {
        testGame = new Hangman();
    }

    @Test
    void normalGuessTest() {
        String testInput = "h";
        testGame.setScanner(new ByteArrayInputStream(testInput.getBytes()));
        assertEquals('h', testGame.getInputFromStream(), "normalGuessTestFailed");
    }

    @Test
    void guessTooLongTest() {
        String testInput = "long";
        testGame.setScanner(new ByteArrayInputStream(testInput.getBytes()));
        assertEquals('l', testGame.getInputFromStream(), "guessTooLongTestFailed");
    }

    @Test
    void guessNotLetterTest() {
        String testInput = "1";
        testGame.setScanner(new ByteArrayInputStream(testInput.getBytes()));
        assertEquals('?', testGame.getInputFromStream(), "guessNotLetterTest failed");
    }

    @Test
    void testInWord() {
        String testWord = "hello";
        testGame.setWord(testWord);
        char testInput = 'h';
        assertEquals(0, testGame.checkGuess(testWord, testInput), "testInWord failed");
    }

    @Test
    void testNotInWord() {
        String testWord = "hello";
        testGame.setWord(testWord);
        char testInput = 'a';
        assertEquals(0, testGame.checkGuess(testWord, testInput), "testNotInWord failed");
    }

    @Test
    void testAlreadyGuessed() {
        String testWord = "hello";
        testGame.setWord(testWord);
        char testInput = 'a';
        testGame.checkGuess(testWord, testInput);
        assertEquals(1, testGame.checkGuess(testWord, testInput), "testAlreadyGuessed failed");
    }
    @Test
    void testWin() {
        String testWord = "hello";
        testGame.setWord(testWord);
        testGame.checkGuess(testWord, 'h');
        testGame.checkGuess(testWord, 'e');
        testGame.checkGuess(testWord, 'l');
        assertEquals(2, testGame.checkGuess(testWord, 'o'), "testWin failed");
    }

    @Test
    void userAckYes() {
        String testInput = "yes";
        testGame.setScanner(new ByteArrayInputStream(testInput.getBytes()));
        assertTrue(testGame.getUserAck(), "userAckYesFail");
    }

    @Test
    void userAckNo() {
        String testInput = "no";
        testGame.setScanner(new ByteArrayInputStream(testInput.getBytes()));
        assertFalse(testGame.getUserAck(), "userAckNoFail");
    }

    @AfterEach
    void tearDown() {
    }
}