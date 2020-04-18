import java.awt.Rectangle;


public class Bricks {

	private int x, y;
	private int brickHeight, brickWidth;
	private int strength;
	private boolean visible;
	private String color;

	public Bricks(int x, int y, String color) {
		this.x = x;
		this.y = y;
		brickHeight = 30;
		brickWidth = 20;
		visible = true;
		this.color = color;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getStrength() {
		return strength;
	}

	public String getColor() {
		return color;
	}

	public int getBrickHeight() {
		return brickHeight;
	}

	public int getBrickWidth() {
		return brickWidth;
	}

	public void gotHit() {
		strength--;
		if (strength == 0) {
			visible = false;
		}
	}
	
	public Rectangle getRect() {
		return new Rectangle(this.x, this.y, this.getBrickWidth(), this.getBrickHeight());
	}

}

class BlueBricks extends Bricks {

	BlueBricks(int x, int y, String difficulty) {
		super(x, y, "Blue");

		if (difficulty.equals("easy")) {
			super.setStrength(1);
		} else if (difficulty.equals("medium")) {
			super.setStrength(2);
		} else {
			super.setStrength(3);
		}

	}
}

class GreenBricks extends Bricks {

	GreenBricks(int x, int y, String difficulty) {
		super(x, y, "Green");

		if (difficulty.equals("easy")) {
			super.setStrength(2);
		} else if (difficulty.equals("medium")) {
			super.setStrength(3);
		} else {
			super.setStrength(4);
		}
	}
}

class OrangeBricks extends Bricks {

	OrangeBricks(int x, int y, String difficulty) {
		super(x, y, "Orange");

		if (difficulty.equals("easy")) {
			super.setStrength(3);
		} else if (difficulty.equals("medium")) {
			super.setStrength(4);
		} else {
			super.setStrength(5);
		}
	}
}

class RedBricks extends Bricks {

	RedBricks(int x, int y, String difficulty) {
		super(x, y, "Red");

		if (difficulty.equals("easy")) {
			super.setStrength(4);
		} else if (difficulty.equals("medium")) {
			super.setStrength(5);
		} else {
			super.setStrength(6);
		}
	}
}

class GrayBricks extends Bricks {

	GrayBricks(int x, int y, String difficulty) {
		super(x, y, "Gray");

	}
}

class InvisibleBricks extends Bricks {

	InvisibleBricks(int x, int y, String difficulty) {
		super(x, y, "Invisible");
		super.setVisible(false);
	}
}


