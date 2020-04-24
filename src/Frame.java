import javax.swing.JFrame;

/**
 * This class creates the main JFrame for game play
 * 
 * @author jacob, muizz, raheel
 *
 */
public class Frame extends JFrame {

	private final int frameheight = 650;
	private final int framewidth = 600;
	private Panel panel;

	/**
	 * This constructor creates the main JFrame for game play
	 */
	public Frame() {

		panel = new Panel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(framewidth, frameheight);
		add(panel);
		setTitle(MainMenu.getUserName() + "'s Brick Breaker Game");
		setLocationRelativeTo(null);
		setEnabled(true);
		setVisible(true);
		setResizable(false);

		// Waits for game play to be over
		while (Panel.getLevel() < 4 && Panel.getLives() > 0) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}

		// this thread pauses and allows time for graphics to display "Game Over" on
		// screen
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		// if a high score was achieved this method overwrites the high score file
		if (panel.getHighScore().checkHighScore()) {
			panel.getHighScore().writeScore();
		}

		removeAll();
		setEnabled(false);
		setVisible(false);
		dispose();

	}

}