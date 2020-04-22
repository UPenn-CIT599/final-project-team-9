import javax.swing.JOptionPane;

/**
 * Runner class for the entire program
 * 
 * @author jacob, muizz, raheel
 *
 */
public class Runner {

	/**
	 * This method runs the entire program until user decides to exit
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MainMenu mainMenu;
		Frame frame;

		while (MainMenu.getIsRunning()) {
			mainMenu = new MainMenu();

			while (MainMenu.getIsRunning()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}

			if (MainMenu.getExitWithoutPlay() == false) {
				frame = new Frame();

				while (Panel.getLevel() < 4 && Panel.getLives() > 0) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				frame.dispose();

				Player currentPlayer = new Player(MainMenu.getUserName(), Panel.getScore());
				HighScore highScore = new HighScore(currentPlayer);
				highScore.extractHighScore();
				if (highScore.checkHighScore()) {
					highScore.writeScore();
					if (Panel.getLevel() == 4) {
						JOptionPane.showMessageDialog(null,
								"New High Score!! You win!! Click OK to return to Main Menu. ");
					} else {
						JOptionPane.showMessageDialog(null, "New High Score!! Click OK to return to Main Menu. ");
					}
				} else if (Panel.getLevel() == 4) {
					JOptionPane.showMessageDialog(null, "You win!! Click OK to return to Main Menu. ");
				} else {
					JOptionPane.showMessageDialog(null, "Game Over. Nice try! Click OK to return to Main Menu.");
				}
				
				MainMenu.setIsRunning(true);
				MainMenu.setExitWithoutPlay(false);
				Panel.setLevel(1);
				Panel.setLives(3);
				Panel.setScore(0);

			}
			
		}
		
		System.exit(0);

		// FOLLOW UPS:
		// Critical: game loop is not working
		// follow-up on paddle/brick interactions - fix as needed
		// paddle movement - goes off screen - should touch edges - right is far too
		// left - left goes off screen
		// gray bricks need to be fixed for boxing in potential layout
		// Create JUnit tests (14 more)
		// Create a ReadMe doc

	}

}
