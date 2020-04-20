/**
 * Runner class for the entire program
 * @author jacob, muizz, raheel
 *
 */
public class Runner {
	
    
    /**
     * This method runs the entire program until user decides to exit 
     * @param args
     */
    public static void main(String[] args) {
    	String username;
    	String difficulty;
    	boolean mainMenuRunning = true;

    	MainMenu mainMenu = new MainMenu();

    	mainMenuRunning = mainMenu.getIsRunning();

    	while(mainMenuRunning) {
    		try {
    			Thread.sleep(3000);
    			mainMenuRunning = mainMenu.getIsRunning();
    		} catch (InterruptedException e1) {
    			e1.printStackTrace();
    		}
    	}

    	difficulty = mainMenu.getDifficulty();
    	username = mainMenu.getUserName();
    	Frame frame = new Frame(username, difficulty);
    	
    	//FOLLOW UPS:
    	//fix paddle movement & ball bounce off paddle
    	//need to fix ball bounce off bricks
    	//level up - DONE
    	//add game ending when you beat level 3 and run out of lives
    	//high score calculator + redirect to main menu
    	//sound needs to stop when moving from main menu to game and level and level to level - DONE
    	//gray bricks need to be fixed for boxing in potential layout
    	

    }

}
