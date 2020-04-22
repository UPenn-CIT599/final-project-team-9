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
    
    @Test
    void BricksTest() {
    	BrickMaker brickGame = new BrickMaker(600, 600);
    	brickGame.makeBricks();
    	
    	ArrayList<Bricks> brickListing = brickGame.getBucket();
    	int b = 0 , g = 0, o = 0, r = 0, gr = 0;
    	
    	for (Bricks bricks : brickListing) {
			if(bricks.getColor().equals("Blue")) {
				b++;
			}
			if(bricks.getColor().equals("Orange")) {
				o++;
			}
			if(bricks.getColor().equals("Red")) {
				r++;
			}
			if(bricks.getColor().equals("Green")) {
				g++;
			}
			if(bricks.getColor().equals("Gray")) {
				gr++;
			}
		}
    	
    	assertEquals(b, 150);
    	assertEquals(g, 108);
    	assertEquals(o, 36);
    	assertEquals(r, 18);
    	assertEquals(gr, 0);
    }

}
