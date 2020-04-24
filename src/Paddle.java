import java.awt.Color;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

/**
 * This class initializes the paddle for game play
 * 
 * @author jacob, muizz, raheel
 *
 */
public class Paddle extends Shape {

	private JPanel panel;
	private static double startx = 300;
	private static double starty = 500;
	private static double width = 80;
	private static double height = 10;
	private double speed = 30;

	/**
	 * This constructor creates the paddle includes position to start, size, color,
	 * panel
	 * 
	 * @param color
	 * @param panel
	 */
	public Paddle(Color color, JPanel panel) {
		super(new RoundRectangle2D.Double(startx, starty, width, height, 15, 15), color, true);
		this.panel = panel;
	}

	/**
	 * This method will move the paddle across the game board
	 * 
	 * @param dx
	 */
	public void move(double dx) {
		if ((getX() + dx >= 0) && (getX() + dx + width <= panel.getWidth())) {
			move(dx, 0);
		}
	}

	/**
	 * This method moves the paddle to the right
	 */
	public void moveRight() {
		move(speed);
	}

	/**
	 * This method moves the paddle to the left
	 */
	public void moveLeft() {
		move(-speed);
	}

}