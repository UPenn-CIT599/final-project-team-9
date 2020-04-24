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
				
				MainMenu.setIsRunning(true);
				MainMenu.setExitWithoutPlay(false);
				Panel.setLevel(1);
				Panel.setLives(3);
				Panel.setScore(0);

			}
			
		}
		

	}

}
