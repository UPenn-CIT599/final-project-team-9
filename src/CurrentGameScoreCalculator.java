/**
 * This class sets and maintains the current game score 
 * @author jacob, muizz, raheel
 */

public class CurrentGameScoreCalculator {
    
    private int score;
    
    /**
     * This constructor initializes the score at 0
     */
    public CurrentGameScoreCalculator() {
        
        this.score = 0;
    }
    
    /**
     * This method updates the score +1 when a brick has been hit once
     */
    public void pointScored(){
        this.score++;
    }
    
    /**
     * This method gets the current score 
     */
    public int getCurrentScore() {
        return score; 
    }
}
