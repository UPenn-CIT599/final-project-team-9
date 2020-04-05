/**
 * This class sets and maintains score 
 * @author jacob, muizz, raheel
 */

public class Score {
    
    private int score;
    
    /**
     * This constructor initalizes the score at 0
     */
    public Score() {
        
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
