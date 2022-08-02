import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;

class GuessTheNumberTest {
    GuessTheNumber testGame;

    @BeforeEach
    void setUp() {
        testGame = new GuessTheNumber();
    }

    @Test
    void getUserNameTest() {
        String testInput = "Isaac\r\n";
        testGame.setScanner(new ByteArrayInputStream(testInput.getBytes()));
        assertEquals(0, testGame.getUserNameFromStream(), "usernameTestFailed");
    }

    @Test
    void getGuessPassCase() {
        String testInput;
        for(int i = 1; i < 21; i++) {
            testInput = i + "\r\n";
            testGame.setScanner(new ByteArrayInputStream(testInput.getBytes()));
            assertEquals(0, testGame.getUserGuessFromStream(), "userGuessTestFailed");
        }
    }
    @Test
    void getGuessFailCase() {
        String testInput = "Hello\r\n";
        testGame.setScanner(new ByteArrayInputStream(testInput.getBytes()));
        assertEquals(1, testGame.getUserGuessFromStream(), "userGuessTestFailed");
    }


    @Test
    void getAckPassCase() {
        String testInput = "y\r\n";
        testGame.setScanner(new ByteArrayInputStream(testInput.getBytes()));
        assertEquals(0, testGame.getUserAckFromStream(), "userAckTestFailed");
    }

    @Test
    void getAckFailCase() {
        String testInput1 = "This Should Fail\r\n";
        testGame.setScanner(new ByteArrayInputStream(testInput1.getBytes()));
        assertEquals(1, testGame.getUserAckFromStream(), "userAckTestFailed");

        String testInput2 = "No\r\n";
        testGame.setScanner(new ByteArrayInputStream(testInput2.getBytes()));
        assertEquals(1, testGame.getUserAckFromStream(), "userAckTestFailed");
    }

    @Test
    void setWinNumTest() {
        testGame.setWinNum(10);
        assertEquals(10, testGame.getWinNum(), "setWinNumTestFailed");
    }

    @Test
    void setUserNameTest() {
        testGame.setUserName("Abaddon, Lord of Darkness");
        assertEquals("Abaddon, Lord of Darkness", testGame.getUserName(), "setUserNameTestFailed");
    }



    @Test
    void setUserAckTest() {
        testGame.setUserAck('y');
        assertEquals('y', testGame.getUserAck(), "setUserAckTestFailed");
    }

    @Test
    void setGuessCountTest() {
        testGame.setGuessCount(99);
        assertEquals(99, testGame.getGuessCount(), "setGuessCountTestFailed");
    }

    @Test
    void incGuessCountTest() {
        testGame.setGuessCount(10);
        testGame.incGuessCount();
        assertEquals(11, testGame.getGuessCount(), "incGuessCountTestFailed");
    }

    @Test
    void setWinTest() {
        testGame.setWin(true);
        assertTrue(testGame.getWin(), "setWinTestFailed");

        testGame.setWin(false);
        assertFalse(testGame.getWin(), "setWinTestFailed");
    }

    @Test
    void checkGuessTooHigh(){
        String testInput = "20\r\n";
        testGame.setWinNum(10);
        testGame.setScanner(new ByteArrayInputStream(testInput.getBytes()));
        testGame.getUserGuessFromStream();
        assertEquals(2, testGame.checkGuess(), "checkGuessTooHighTestFailed");
    }

    @Test
    void checkGuessTooLow(){
        String testInput = "1\r\n";
        testGame.setWinNum(10);
        testGame.setScanner(new ByteArrayInputStream(testInput.getBytes()));
        testGame.getUserGuessFromStream();
        assertEquals(1, testGame.checkGuess(), "checkGuessTooLowTestFailed");
    }

    @Test
    void checkGuessEquals(){
        String testInput = "10\r\n";
        testGame.setWinNum(10);
        testGame.setScanner(new ByteArrayInputStream(testInput.getBytes()));
        testGame.getUserGuessFromStream();
        assertEquals(0, testGame.checkGuess(), "checkGuessEqualsTestFailed");
    }

    @AfterEach
    void tearDown() {
    }
}