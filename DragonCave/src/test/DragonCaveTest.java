import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

class DragonCaveTest {
    DragonCave testCave;
    InputStream stdin = System.in;

    @BeforeEach
    void setUp() {
        testCave = new DragonCave();
    }

    @DisplayName("input: 1, winCave: 1")
    @Test
    void testCase1(){
        String testInput = "1\r\n";
        testCave.setCave(1);
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        testCave.setPlayerChoice();
        assertEquals(1, testCave.playerWin(), "Test1 failed - in:1 win:1");
    }

    @DisplayName("input: 1, winCave: 2")
    @Test
    void testCase2(){
        String testInput = "1\r\n";
        testCave.setCave(2);
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        testCave.setPlayerChoice();
        assertEquals(0, testCave.playerWin(), "Test1 failed - in:1 win:1");
    }

    @DisplayName("input: 2, winCave: 1")
    @Test
    void testCase3(){
        String testInput = "2\r\n";
        testCave.setCave(1);
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        testCave.setPlayerChoice();
        assertEquals(0, testCave.playerWin(), "Test1 failed - in:1 win:1");
    }

    @DisplayName("input: 2, winCave: 2")
    @Test
    void testCase4(){
        String testInput = "2\r\n";
        testCave.setCave(2);
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        testCave.setPlayerChoice();
        assertEquals(1, testCave.playerWin(), "Test1 failed - in:1 win:1");
    }

    @DisplayName("input a string")
    @Test
    void testString(){
        String testInput = "Hello World!\r\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        assertEquals(1, testCave.setPlayerChoice(), "String Test Failed");
    }

    @DisplayName("input too high")
    @Test
    void testTooHigh(){
        String testInput = "999999\r\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        assertEquals(1, testCave.setPlayerChoice(), "High Int Test Failed");
    }

    @DisplayName("input too low")
    @Test
    void testTooLow(){
        String testInput = "0\r\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        assertEquals(1, testCave.setPlayerChoice(), "Low Int Test Failed");
    }

    @AfterEach
    void tearDown() {
        System.setIn(stdin);
    }
}