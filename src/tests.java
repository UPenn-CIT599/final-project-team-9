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
        Player test = new Player ("test", 300);
        HighScore highscore = new HighScore(test, "high_score_test_file.txt");
        highscore.extractHighScore();
        testValue = highscore.checkHighScore();
        assertEquals(testValue, true);
    }
    

}
