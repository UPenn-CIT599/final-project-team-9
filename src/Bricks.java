import java.util.*;


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

	public void gotHit() {
		if (strength-- <= 0) {
			strength--;
			visible = false;
		}
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

class BrickMaker {
	private int canvasSizeX, canvasSizeY, maxRow, maxCol, totalBricks, level;
	private String difficulty;
	private ArrayList<Bricks> bucket = new ArrayList<Bricks>();

	BrickMaker(int canvasSizeX, int canvasSizeY, String difficulty, int level) {
		this.canvasSizeX = canvasSizeX;
		this.canvasSizeY = canvasSizeY;
		this.difficulty = difficulty;
		this.level = level;
	}

	void makeBricks() {
		double blueMix, greenMix, orangeMix, redMix, grayMix, invisibleMix, num, xBound, yBound;
		int blueTotal, greenTotal, orangeTotal, redTotal, grayTotal, invisibleTotal;
		Random generator = new Random();

		maxRow = canvasSizeX / 20;
		System.out.println(maxRow);
		maxCol = (int) ((int) canvasSizeY * .60);
		maxCol = (maxCol / 30);
		totalBricks = maxRow * maxCol;

		if (difficulty.equals("easy")) {
			blueMix = .5;
			greenMix = .3;
			orangeMix = .1;
			redMix = .05;
			grayMix = 0;
			invisibleMix = .05;
		}

		else if (difficulty.equals("medium")) {
			blueMix = .25;
			greenMix = .2;
			orangeMix = .25;
			redMix = .1;
			grayMix = .1;
			invisibleMix = .1;
		}

		else {
			blueMix = .1;
			greenMix = .1;
			orangeMix = .4;
			redMix = .225;
			grayMix = .125;
			invisibleMix = .05;
		}
		
		blueTotal = (int) Math.round(blueMix * totalBricks);
		greenTotal = (int) Math.round(greenMix * totalBricks);
		orangeTotal = (int) Math.round(orangeMix * totalBricks);
		redTotal = (int) Math.round(redMix * totalBricks);
		grayTotal = (int) Math.round(grayMix * totalBricks);
		invisibleTotal = (int) Math.round(invisibleMix * totalBricks);

		if (level == 1) {
			double y = .5 * canvasSizeY;
			yBound = (int) Math.round(y);
			xBound = canvasSizeX - 20;
		} else if (level == 2) {
			double y = .6 * canvasSizeY;
			yBound = (int) Math.round(y);
			xBound = canvasSizeX - 20;
		} else {
			double y = .75 * canvasSizeY;
			yBound = (int) Math.round(y);
			xBound = canvasSizeX - 20;
		}

		for (int y = 0; y < yBound; y +=30 ) {
			for (int x = 0; x <= xBound; x += 20) {
				boolean found = false;
				while(found == false) {
					num = generator.nextInt(6);
					if(num == 0 && blueTotal > 0) {
						bucket.add(new BlueBricks(x, y, difficulty));
						blueTotal--;
						found = true; 
					}
					else if(num == 1 && greenTotal > 0) {
						bucket.add(new GreenBricks(x, y, difficulty));
						greenTotal--;
						found = true; 
					}
					else if(num == 2 && orangeTotal > 0) {
						bucket.add(new OrangeBricks(x, y, difficulty));
						orangeTotal--;
						found = true; 
					}
					else if(num == 3 && redTotal > 0) {
						bucket.add(new RedBricks(x, y, difficulty));
						redTotal--;
						found = true; 
					}
					else if(num == 4 && grayTotal > 0) {
						bucket.add(new GrayBricks(x, y, difficulty));
						grayTotal--;
						found = true; 
					}
					else if(num == 4 && invisibleTotal > 0) {
						bucket.add(new InvisibleBricks(x, y, difficulty));
						invisibleTotal--;
						found = true; 
					}
					else if(blueTotal + greenTotal + orangeTotal + redTotal + grayTotal + invisibleTotal == 0) {
						bucket.add(new InvisibleBricks(x, y, difficulty));
						found = true; 
					}
				}
				
			}
		}

	}

	public ArrayList<Bricks> getBucket() {
		return bucket;
	}

	// for testing only
	public static void main(String[] args) {
		BrickMaker brickGame = new BrickMaker(600, 600, "easy", 1);
		brickGame.makeBricks();
		ArrayList<Bricks> brickListing = brickGame.getBucket();

		
		
		for (Bricks bricks : brickListing) {
			System.out.println(bricks.getColor() + " X: " + bricks.getX() + " Y: " + bricks.getY() + " Strength: "
					+ bricks.getStrength() + " Visible: " + bricks.isVisible());
			
		}
		int b = 0 , g = 0, o = 0, r = 0, gr = 0, i = 0;
    	
    	for (Bricks bricks : brickListing) {
			if(bricks.getColor().equals("Blue")) {
				b++;
			}
			if(bricks.getColor().equals("Orange")) {
				o++;
			}
			if(bricks.getColor().equals("Red")) {
				r++;
			}
			if(bricks.getColor().equals("Green")) {
				g++;
			}
			if(bricks.getColor().equals("Gray")) {
				gr++;
			}
			if(bricks.getColor().equals("Invisible")) {
				i++;
			}
		}
    	
    	System.out.println("B: " + b + " G: " + g + " O: " + o + " R: " + r + " Gr: " + gr + " In: " + i + " Total: " + brickGame.totalBricks);

	}
}
