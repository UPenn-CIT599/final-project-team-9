import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

/**
 * This class is a parent class which establishes the parameters of bricks which
 * are rectangular shaped objects
 * 
 * @author jacob, muizz, raheel
 *
 */
public class Bricks {

	private int x, y;
	private int brickHeight, brickWidth;
	private int strength;
	private boolean visible;
	private String color;

	/**
	 * This constructor creates the brick using the provided x and y coordinates and
	 * the brick color
	 * 
	 * @param x
	 * @param y
	 * @param color
	 */
	public Bricks(int x, int y, String color) {
		this.x = x;
		this.y = y;
		brickHeight = 20;
		brickWidth = 30;
		visible = true;
		this.color = color;
	}

	/**
	 * This method provides a hit (e.g. decreases strength of brick) If strength =
	 * 0, then the method toggles the visibility false
	 */
	public void gotHit() {
		strength--;
		if (strength == 0) {
			visible = false;
		}
	}

	/**
	 * This method takes in the brick object parameters and returns a rectangular
	 * object
	 * 
	 * @return
	 */
	public Rectangle2D getRect() {
		return new Rectangle(this.x, this.y, this.getBrickWidth(), this.getBrickHeight());
	}

	// getters and setters

	/**
	 * 
	 * @param strength
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}

	/**
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * 
	 * @param visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * 
	 * @return
	 */
	public int getStrength() {
		return strength;
	}

	/**
	 * 
	 * @return
	 */
	public String getColor() {
		return color;
	}

	/**
	 * 
	 * @return
	 */
	public int getBrickHeight() {
		return brickHeight;
	}

	/**
	 * 
	 * @return
	 */
	public int getBrickWidth() {
		return brickWidth;
	}

}

/**
 * This child class of bricks takes in the x and y coordinates and assigns color
 * and brick strength
 * 
 * @author jacob, muizz, raheel
 *
 */
class BlueBricks extends Bricks {

	BlueBricks(int x, int y) {
		super(x, y, "Blue");

		if (MainMenu.getDifficulty().toLowerCase().equals("easy")) {
			super.setStrength(1);
		} else if (MainMenu.getDifficulty().toLowerCase().equals("medium")) {
			super.setStrength(2);
		} else {
			super.setStrength(3);
		}

	}
}

/**
 * This child class of bricks takes in the x and y coordinates and assigns color
 * and brick strength
 * 
 * @author jacob, muizz, raheel
 * 
 */
class GreenBricks extends Bricks {

	GreenBricks(int x, int y) {
		super(x, y, "Green");

		if (MainMenu.getDifficulty().toLowerCase().equals("easy")) {
			super.setStrength(2);
		} else if (MainMenu.getDifficulty().toLowerCase().equals("medium")) {
			super.setStrength(3);
		} else {
			super.setStrength(4);
		}
	}
}

/**
 * This child class of bricks takes in the x and y coordinates and assigns color
 * and brick strength
 * 
 * @author jacob, muizz, raheel
 *
 */
class OrangeBricks extends Bricks {

	OrangeBricks(int x, int y) {
		super(x, y, "Orange");

		if (MainMenu.getDifficulty().toLowerCase().equals("easy")) {
			super.setStrength(3);
		} else if (MainMenu.getDifficulty().toLowerCase().equals("medium")) {
			super.setStrength(4);
		} else {
			super.setStrength(5);
		}
	}
}

/**
 * This child class of bricks takes in the x and y coordinates and assigns color
 * and brick strength
 * 
 * @author jacob, muizz, raheel
 *
 */
class RedBricks extends Bricks {

	RedBricks(int x, int y) {
		super(x, y, "Red");

		if (MainMenu.getDifficulty().toLowerCase().equals("easy")) {
			super.setStrength(4);
		} else if (MainMenu.getDifficulty().toLowerCase().equals("medium")) {
			super.setStrength(5);
		} else {
			super.setStrength(6);
		}
	}
}

/**
 * This child class of bricks takes in the x and y coordinates and assigns color
 * 
 * @author jacob, muizz, raheel
 *
 */
class GrayBricks extends Bricks {

	GrayBricks(int x, int y) {
		super(x, y, "Gray");

	}
}

/**
 * This child class of bricks takes in the x and y coordinates and assigns color
 * Note: this class provides spacing on the canvas; once created, these objects
 * are deleted before game play begins
 * 
 * @author jacob, muizz, raheel
 *
 */
class InvisibleBricks extends Bricks {

	InvisibleBricks(int x, int y) {
		super(x, y, "Invisible");
		super.setVisible(false);
	}
}
