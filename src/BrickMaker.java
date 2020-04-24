import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class creates the bricks and the respective power-ups
 * 
 * @author jacob, muizz, raheel
 *
 */
public class BrickMaker {
	private int canvasSizeX, canvasSizeY, maxRow, maxCol, totalBricks;
	private ArrayList<Bricks> bucket = new ArrayList<Bricks>();
	private boolean gameOver;

	/**
	 * This constructor sets the canvas for brick creation
	 * 
	 * @param canvasSizeX
	 * @param canvasSizeY
	 */
	public BrickMaker(int canvasSizeX, int canvasSizeY) {
		this.canvasSizeX = canvasSizeX;
		this.canvasSizeY = canvasSizeY;
		this.gameOver = false;
	}

	/**
	 * This method takes the canvas and the user selected difficulty to create the
	 * parameters from which the mix of bricks will be created.
	 */
	public void makeBricks() {
		double blueMix, greenMix, orangeMix, redMix, grayMix, invisibleMix;
		int blueTotal, greenTotal, orangeTotal, redTotal, grayTotal, invisibleTotal;

		maxRow = canvasSizeX / 30;
		maxCol = (int) ((int) canvasSizeY * .60);
		maxCol = (maxCol / 20);
		totalBricks = maxRow * maxCol;

		if (MainMenu.getDifficulty().toLowerCase().equals("easy")) {
			blueMix = .2;
			greenMix = .15;
			orangeMix = .10;
			redMix = .05;
			grayMix = 0;
			invisibleMix = .20;
		}

		else if (MainMenu.getDifficulty().toLowerCase().equals("medium")) {
			blueMix = .125;
			greenMix = .1;
			orangeMix = .125;
			redMix = .075;
			grayMix = .075;
			invisibleMix = .3;
		}

		else {
			blueMix = .1;
			greenMix = .1;
			orangeMix = .3;
			redMix = .175;
			grayMix = .125;
			invisibleMix = .4;
		}

		// the maximum number of bricks that can be created for the respective colors
		blueTotal = (int) Math.round(blueMix * totalBricks);
		greenTotal = (int) Math.round(greenMix * totalBricks);
		orangeTotal = (int) Math.round(orangeMix * totalBricks);
		redTotal = (int) Math.round(redMix * totalBricks);
		grayTotal = (int) Math.round(grayMix * totalBricks);
		invisibleTotal = (int) Math.round(invisibleMix * totalBricks);

		// helper methods to create bricks and remove invisible bricks
		brickBucketMaker(blueTotal, greenTotal, orangeTotal, redTotal, grayTotal, invisibleTotal);
		deleteInvisibleBricks();

	}

	/**
	 * This helper method randomizes the placement of the bricks on the canvas by
	 * creating bricks of randomly generated colors based on the totals passed in
	 * 
	 * @param blueTotal
	 * @param greenTotal
	 * @param orangeTotal
	 * @param redTotal
	 * @param grayTotal
	 * @param invisibleTotal
	 */
	private void brickBucketMaker(int blueTotal, int greenTotal, int orangeTotal, int redTotal, int grayTotal,
			int invisibleTotal) {

		Random generator = new Random();
		double xBound, yBound;
		int num;

		if (Panel.level == 1) {
			double y = .35 * canvasSizeY;
			yBound = (int) Math.round(y);
			xBound = canvasSizeX - 60;
		} else if (Panel.level == 2) {
			double y = .45 * canvasSizeY;
			yBound = (int) Math.round(y);
			xBound = canvasSizeX - 60;
		} else {
			double y = .60 * canvasSizeY;
			yBound = (int) Math.round(y);
			xBound = canvasSizeX - 60;
		}

		for (int y = 20; y < yBound; y += 20) {
			for (int x = 30; x <= xBound; x += 30) {
				boolean found = false;
				while (found == false) {
					num = generator.nextInt(7);
					if (num == 0 && blueTotal > 0) {
						bucket.add(new BlueBricks(x, y));
						blueTotal--;
						found = true;
					} else if (num == 1 && greenTotal > 0) {
						bucket.add(new GreenBricks(x, y));
						greenTotal--;
						found = true;
					} else if (num == 2 && orangeTotal > 0) {
						bucket.add(new OrangeBricks(x, y));
						orangeTotal--;
						found = true;
					} else if (num == 3 && redTotal > 0) {
						bucket.add(new RedBricks(x, y));
						redTotal--;
						found = true;
					} else if (num == 4 && grayTotal > 0) {
						bucket.add(new GrayBricks(x, y));
						grayTotal--;
						found = true;
					} else if ((num == 5 || num == 6) && invisibleTotal > 0) {
						bucket.add(new InvisibleBricks(x, y));
						invisibleTotal--;
						found = true;
					} else if (blueTotal + greenTotal + orangeTotal + redTotal + grayTotal + invisibleTotal == 0) {
						bucket.add(new InvisibleBricks(x, y));
						found = true;
					}
				}

			}
		}

	}

	/**
	 * This method deletes the invisible bricks Note: invisible includes any brick
	 * that reaches strength 0 and is toggled false
	 */
	public void deleteInvisibleBricks() {
		for (int i = 0; i < bucket.size(); i++) {
			if (bucket.get(i).isVisible() == false) {
				bucket.remove(i);
				i--;
			}
		}
	}

	/**
	 * This method checks if the strength of all bricks is 0 then the game is over
	 * 
	 * @return
	 */
	public boolean isGameOver() {
		int counter = 0;

		for (int i = 0; i < bucket.size(); i++) {
			counter += bucket.get(i).getStrength();

		}

		if (counter == 0) {
			gameOver = true;
			return gameOver;
		}

		return gameOver;
	}

	/**
	 * This method is a power-up that gives every non-gray brick a hit;
	 * Additionally, this power-up increments the score for every brick hit
	 */
	public void hitAll() {
		for (Bricks bricks : bucket) {
			if (bricks.getColor().contentEquals("Gray") == false) {
				bricks.gotHit();
				Panel.setScore(Panel.getScore() + 1);
			}
		}
	}

	/**
	 * This method is a power-up for Medium and Hard levels; this method destroys
	 * all gray bricks
	 */
	public void destroyWall() {
		for (Bricks bricks : bucket) {
			if (bricks.getColor().contentEquals("Gray")) {
				bricks.setVisible(false);
			}
		}
	}

	/**
	 * This method draws the bricks onto the panel
	 * 
	 * @param g
	 */
	public void draw(Graphics2D g) {
		for (Bricks bricks : bucket) {
			if (bricks.getColor().contentEquals("Blue") && bricks.isVisible()) {
				g.setColor(Color.BLACK);
				g.drawRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
				g.setColor(new Color(0, 191, 255));
				g.fillRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
			}
			if (bricks.getColor().contentEquals("Orange") && bricks.isVisible()) {
				g.setColor(Color.BLACK);
				g.drawRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
				g.setColor(new Color(255, 165, 0));
				g.fillRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
			}
			if (bricks.getColor().contentEquals("Red") && bricks.isVisible()) {
				g.setColor(Color.BLACK);
				g.drawRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
				g.setColor(new Color(255, 99, 71));
				g.fillRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
			}
			if (bricks.getColor().contentEquals("Gray") && bricks.isVisible()) {
				g.setColor(Color.BLACK);
				g.drawRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());

			}
			if (bricks.getColor().contentEquals("Green") && bricks.isVisible()) {
				g.setColor(Color.BLACK);
				g.drawRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
				g.setColor(new Color(124, 252, 0));
				g.fillRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
			}

		}
	}

	// getters and setters

	/**
	 * 
	 * @param t
	 */
	public void setIsGameOver(boolean t) {
		this.gameOver = t;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Bricks> getBucket() {
		return bucket;
	}

	/**
	 * 
	 * @return
	 */
	public int getTotalBricks() {
		return totalBricks;
	}

}