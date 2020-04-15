import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BrickMaker extends JPanel {
	private int canvasSizeX, canvasSizeY, maxRow, maxCol, totalBricks, level;
	private String difficulty;
	private ArrayList<Bricks> bucket = new ArrayList<Bricks>();

	public BrickMaker(int canvasSizeX, int canvasSizeY, String difficulty, int level) {
		this.canvasSizeX = canvasSizeX;
		this.canvasSizeY = canvasSizeY;
		this.difficulty = difficulty;
		this.level = level;
	}

	public void makeBricks() {
		double blueMix, greenMix, orangeMix, redMix, grayMix, invisibleMix; 
		int blueTotal, greenTotal, orangeTotal, redTotal, grayTotal, invisibleTotal;

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
			xBound = canvasSizeX - 20;
		} else if (level == 2) {
			double y = .5 * canvasSizeY;
			yBound = (int) Math.round(y);
			xBound = canvasSizeX - 20;
		} else {
			double y = .65 * canvasSizeY;
			yBound = (int) Math.round(y);
			xBound = canvasSizeX - 20;
		}

		for (int y = 0; y < yBound; y += 30) {
			for (int x = 0; x <= xBound; x += 20) {
				boolean found = false;
				while (found == false) {
					num = generator.nextInt(6);
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
					} else if (num == 4 && invisibleTotal > 0) {
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
	
	private void deleteInvisibleBricks() {
		for(int i = 0; i < bucket.size(); i++) {
			if(bucket.get(i).getColor().equals("Invisible")) {
				bucket.remove(i);
				i--;
			}
		}
	}

	
	public ArrayList<Bricks> getBucket() {
		return bucket;
	}

	public void paint(Graphics g) {
		for (Bricks bricks : bucket) {
			if (bricks.getColor().contentEquals("Blue")) {
				g.setColor(Color.BLACK);
				g.drawRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
				g.setColor(new Color(0,191,255));
				g.fillRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
			}
			if (bricks.getColor().contentEquals("Orange")) {
				g.setColor(Color.BLACK);
				g.drawRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
				g.setColor(new Color(255,165,0));
				g.fillRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
			}
			if (bricks.getColor().contentEquals("Red")) {
				g.setColor(Color.BLACK);
				g.drawRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
				g.setColor(new Color(255,99,71));
				g.fillRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
			}
			if (bricks.getColor().contentEquals("Gray")) {
				g.setColor(Color.BLACK);
				g.drawRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
				
			}
			if (bricks.getColor().contentEquals("Green")) {
				g.setColor(Color.BLACK);
				g.drawRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
				g.setColor(new Color(124,252,0));
				g.fillRect(bricks.getX(), bricks.getY(), bricks.getBrickWidth(), bricks.getBrickHeight());
			}

		}
	}

	// for testing only
	public static void main(String[] args) {
		BrickMaker brickGame = new BrickMaker(600, 600, "hard", 3);
		brickGame.makeBricks();
		ArrayList<Bricks> brickListing = brickGame.getBucket();

		for (Bricks bricks : brickListing) {
			System.out.println(bricks.getColor() + " X: " + bricks.getX() + " Y: " + bricks.getY() + " Strength: "
					+ bricks.getStrength() + " Visible: " + bricks.isVisible());

		}
		int b = 0, g = 0, o = 0, r = 0, gr = 0, i = 0;

		for (Bricks bricks : brickListing) {
			if (bricks.getColor().equals("Blue")) {
				b++;
			}
			if (bricks.getColor().equals("Orange")) {
				o++;
			}
			if (bricks.getColor().equals("Red")) {
				r++;
			}
			if (bricks.getColor().equals("Green")) {
				g++;
			}
			if (bricks.getColor().equals("Gray")) {
				gr++;
			}
			if (bricks.getColor().equals("Invisible")) {
				i++;
			}
		}

		System.out.println("B: " + b + " G: " + g + " O: " + o + " R: " + r + " Gr: " + gr + " In: " + i + " Total: "
				+ brickGame.totalBricks);

		Paddle p = new Paddle(600, 600, "easy");
		JFrame jf = new JFrame();
		jf.setTitle("Bricks Test");
		jf.setSize(600, 600);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(brickGame);
		jf.setVisible(true);

	}
}