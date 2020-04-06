import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.Test;

class tests {

    @Test
    /*
     * This test is to make sure the highscore method is comparing scores properly
     */
    void HighScoreTest() {
        boolean testValue = false;
        HighScore highscore = new HighScore(300, "test");
        ArrayList<Integer> testList = new ArrayList<Integer>();
        testList.add(0, 100);
        testList.add(1, 200);
        testList.add(2, 250);
        testValue = highscore.checkHighScore(testList);
        assertEquals(testValue, true);
    }

}
