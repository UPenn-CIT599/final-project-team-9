import java.util.*;

public class Bricks {

	private int x, y;
	private int brickHeight, brickWidth;
	private int strength;
	private boolean visible;
	
	public Bricks() {
		x = 0;
		y = 0;
		brickHeight = 30;
		brickWidth = 20;
		visible = true;
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

	public void gotHit() {
		if(strength-- <= 0) {
			strength--;
			visible = false;
		}
	}
	
}

class BlueBricks extends Bricks {
	
	BlueBricks(String difficulty) {
		super();
		
		if(difficulty.equals("easy")) {
			super.setStrength(1);
		}
		else if(difficulty.equals("medium")) {
			super.setStrength(2);
		}
		else {
			super.setStrength(3);
		}
		
	}
}

class GreenBricks extends Bricks {
	
	GreenBricks(String difficulty) {
		super();
		
		if(difficulty.equals("easy")) {
			super.setStrength(2);
		}
		else if(difficulty.equals("medium")) {
			super.setStrength(3);
		}
		else {
			super.setStrength(4);
		}
	}
}

class OrangeBricks extends Bricks {
	
	OrangeBricks(String difficulty) {
		super();
		
		if(difficulty.equals("easy")) {
			super.setStrength(3);
		}
		else if(difficulty.equals("medium")) {
			super.setStrength(4);
		}
		else {
			super.setStrength(5);
		}
	}
}

class RedBricks extends Bricks {
	
	RedBricks(String difficulty) {
		super();
		
		if(difficulty.equals("easy")) {
			super.setStrength(4);
		}
		else if(difficulty.equals("medium")) {
			super.setStrength(5);
		}
		else {
			super.setStrength(6);
		}
	}
}

class GrayBricks extends Bricks {
	
	GrayBricks(String difficulty) {
		super();
		
	}
}

class InvisibleBricks extends Bricks{
	
	InvisibleBricks(String difficulty){
		super();
	}
}

class BrickMaker {
	private int canvasSizeX, canvasSizeY; 
	private String difficulty;
	private HashMap<String, ArrayList<Bricks>> bucket = new HashMap<String, ArrayList<Bricks>>();
	
	BrickMaker(int canvasSizeX, int canvasSizeY, String difficulty){
		this.canvasSizeX = canvasSizeX;
		this.canvasSizeY = canvasSizeY;
		this.difficulty = difficulty;
	}
	
	void makeBricks() {
		int maxRow, maxCol, totalBricks;
		double blueMix, greenMix, orangeMix, redMix, grayMix, invisibleMix;
		
		maxRow = canvasSizeX / 20;
		maxCol = canvasSizeY / 30;
		totalBricks = maxRow * maxCol;
		
		if(difficulty.equals("easy")) {
			blueMix = .5;
			greenMix = .3;
			orangeMix = .1;
			redMix = .05;
			grayMix = 0; 
			invisibleMix = .05;
		}
		
		else if(difficulty.equals("medium")) {
			blueMix = .25;
			greenMix = .2;
			orangeMix = .25;
			redMix = .1;
			grayMix = .1; 
			invisibleMix = .10;
		}
		
		else {
			blueMix = .1;
			greenMix = .1;
			orangeMix = .4;
			redMix = .225;
			grayMix = .125; 
			invisibleMix = .05;
		}
		
		ArrayList<Bricks> brick = new ArrayList<Bricks>();
		for(int i = 1; i <= Math.round(blueMix * totalBricks); i++) {
			BlueBricks b = new BlueBricks(difficulty);
			brick.add(b);
		}
		bucket.put("Blue", brick);
		brick.clear();
		
		for(int i = 1; i <= Math.round(greenMix * totalBricks); i++) {
			GreenBricks b = new GreenBricks(difficulty);
			brick.add(b);
		}
		bucket.put("Green", brick);
		brick.clear();
		
		for(int i = 1; i <= Math.round(orangeMix * totalBricks); i++) {
			OrangeBricks b = new OrangeBricks(difficulty);
			brick.add(b);
		}
		bucket.put("Orange", brick);
		brick.clear();
		
		for(int i = 1; i <= Math.round(redMix * totalBricks); i++) {
			RedBricks b = new RedBricks(difficulty);
			brick.add(b);
		}
		bucket.put("Red", brick);
		brick.clear();
		
		for(int i = 1; i <= Math.round(grayMix * totalBricks); i++) {
			GrayBricks b = new GrayBricks(difficulty);
			brick.add(b);
		}
		bucket.put("Gray", brick);
		brick.clear();
		
		for(int i = 1; i <= Math.round(invisibleMix * totalBricks); i++) {
			InvisibleBricks b = new InvisibleBricks(difficulty);
			brick.add(b);
		}
		bucket.put("Invisible", brick);
		brick.clear();
		
	}

	public HashMap<String, ArrayList<Bricks>> getBucket() {
		return bucket;
	}
}