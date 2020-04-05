import java.util.*;

public class HighScore {
    
    private int userScore;
    private String userName; 
    private int scorePosition;
    private ArrayList<Integer> scores;
    
    /**
     * 
     * @param userScore
     */
    public HighScore(int userScore, String userName) {
        this.userScore = userScore;
        this.userName = userName;
    }
    
    /**
     * This method checks to see if a high score has been achieved and returns t/f
     */
    public boolean checkHighScore(ArrayList<Integer> scoreList) {
        for(int i = 0; i < scoreList.size(); i++) {
            //if score is equal or higher to high score, return true
            if(scoreList.get(i) <= userScore) {
                this.scorePosition = i+1;
                return true; 
            }
        }
        return false;
    }
    
    /**
     * This method creates an arraylist of scores in the high score file 
     */
    public void extractHighScore() {
        
    }
    
    /**
     * This method updates the text file of highscores from the arraylist 
     */
    public void writeScore() {
        
    }

}
