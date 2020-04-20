import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;



public class BrickMaker {
	private int canvasSizeX, canvasSizeY, maxRow, maxCol, totalBricks, level;
	private String difficulty;
	private ArrayList<Bricks> bucket = new ArrayList<Bricks>();
	private boolean gameOver;

	public BrickMaker(int canvasSizeX, int canvasSizeY, String difficulty, int level) {
		this.canvasSizeX = canvasSizeX;
		this.canvasSizeY = canvasSizeY;
		this.difficulty = difficulty;
		this.level = level;
		this.gameOver = false;
	}

	public void makeBricks() {
		double blueMix, greenMix, orangeMix, redMix, grayMix, invisibleMix; 
		int blueTotal, greenTotal, orangeTotal, redTotal, grayTotal, invisibleTotal;

		maxRow = canvasSizeX / 30;
		maxCol = (int) ((int) canvasSizeY * .60);
		maxCol = (maxCol / 20);
		totalBricks = maxRow * maxCol;
		
		
		if (difficulty.equals("easy")) {
			blueMix = .2;
			greenMix = .15;
			orangeMix = .10;
			redMix = .05;
			grayMix = 0;
			invisibleMix = .20;
		}

		else if (difficulty.equals("medium")) {
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

		blueTotal = (int) Math.round(blueMix * totalBricks);
		greenTotal = (int) Math.round(greenMix * totalBricks);
		orangeTotal = (int) Math.round(orangeMix * totalBricks);
		redTotal = (int) Math.round(redMix * totalBricks);
		grayTotal = (int) Math.round(grayMix * totalBricks);
		invisibleTotal = (int) Math.round(invisibleMix * totalBricks);

		brickBucketMaker(blueTotal, greenTotal, orangeTotal, redTotal, grayTotal, invisibleTotal);
		deleteInvisibleBricks();
		

	}

	private void brickBucketMaker(int blueTotal, int greenTotal, int orangeTotal, int redTotal, int grayTotal, int invisibleTotal) {
		
		Random generator = new Random();
		double xBound, yBound;
		int num;
		
		if (level == 1) {
			double y = .35 * canvasSizeY;
			yBound = (int) Math.round(y);
			xBound = canvasSizeX - 30;
		} else if (level == 2) {
			double y = .45 * canvasSizeY;
			yBound = (int) Math.round(y);
			xBound = canvasSizeX - 30;
		} else {
			double y = .60 * canvasSizeY;
			yBound = (int) Math.round(y);
			xBound = canvasSizeX - 30;
		}

		for (int y = 20; y < yBound; y += 20) {
			for (int x = 0; x <= xBound; x += 30) {
				boolean found = false;
				while (found == false) {
					num = generator.nextInt(7);
					if (num == 0 && blueTotal > 0) {
						bucket.add(new BlueBricks(x, y, difficulty));
						blueTotal--;
						found = true;
					} else if (num == 1 && greenTotal > 0) {
						bucket.add(new GreenBricks(x, y, difficulty));
						greenTotal--;
						found = true;
					} else if (num == 2 && orangeTotal > 0) {
						bucket.add(new OrangeBricks(x, y, difficulty));
						orangeTotal--;
						found = true;
					} else if (num == 3 && redTotal > 0) {
						bucket.add(new RedBricks(x, y, difficulty));
						redTotal--;
						found = true;
					} else if (num == 4 && grayTotal > 0) {
						bucket.add(new GrayBricks(x, y, difficulty));
						grayTotal--;
						found = true;
					} else if ((num == 5 || num == 6) && invisibleTotal > 0) {
						bucket.add(new InvisibleBricks(x, y, difficulty));
						invisibleTotal--;
						found = true;
					} else if (blueTotal + greenTotal + orangeTotal + redTotal + grayTotal + invisibleTotal == 0) {
						bucket.add(new InvisibleBricks(x, y, difficulty));
						found = true;
					}
				}

			}
		}

	}
	
	public void deleteInvisibleBricks() {
		for(int i = 0; i < bucket.size(); i++) {
			if(bucket.get(i).isVisible() == false) {
				bucket.remove(i);
				i--;
			}
		}
	}
	
	public boolean isGameOver() {
		int counter = 0;
		
		for(int i = 0; i < bucket.size(); i++) {
			counter += bucket.get(i).getStrength();	
			
		}
		
		if(counter == 0 ) {
			gameOver = true;
			return gameOver;
		}
		
		return gameOver;
	}
	
	public void setIsGameOver(boolean t) {
		this.gameOver = t;
	}

	
	public ArrayList<Bricks> getBucket() {
		return bucket;
	}

	public void draw(Graphics2D g) {
		for (Bricks bricks : bucket) {
			if (bricks.getColor().contentEquals("Blue") && bricks.isVisible()) {
				g.setColor(Color.BLACK);
				g.drawRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
				g.setColor(new Color(0,191,255));
				g.fillRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
			}
			if (bricks.getColor().contentEquals("Orange") && bricks.isVisible()) {
				g.setColor(Color.BLACK);
				g.drawRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
				g.setColor(new Color(255,165,0));
				g.fillRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
			}
			if (bricks.getColor().contentEquals("Red") && bricks.isVisible()) {
				g.setColor(Color.BLACK);
				g.drawRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
				g.setColor(new Color(255,99,71));
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
				g.setColor(new Color(124,252,0));
				g.fillRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
			}
			
		}
	}

}