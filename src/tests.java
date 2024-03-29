import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.junit.jupiter.api.Test;

class tests {

	@Test
	/**
	 * This test is to make sure the highscore method is comparing scores properly
	 */
	void highScoreTest() {
		boolean testValue = false;
		Player test = new Player("test", 300);
		HighScore highscore = new HighScore(test, "high_score_test_file.txt");
		highscore.extractHighScore();
		testValue = highscore.checkHighScore();
		assertEquals(testValue, true);
	}

	@Test
	/**
	 * This test tests the maximum number of bricks possible which is dependent on
	 * the size of the frame
	 */
	void totalBrickTest() {
		BrickMaker bricks = new BrickMaker(500, 500);
		MainMenu.setDifficulty("easy");
		bricks.makeBricks();
		int totalBricks = bricks.getTotalBricks();
		assertEquals(totalBricks, 240);
	}

	@Test
	/**
	 * This test will make sure once bricks are added to the bucket and strength
	 * reaches zero, they are eliminated from the bucket
	 */
	void deletionTest() {
		BrickMaker bricks = new BrickMaker(250, 250);
		MainMenu.setDifficulty("medium");
		bricks.getBucket().add(new RedBricks(20, 0));
		bricks.getBucket().add(new BlueBricks(40, 0));
		bricks.getBucket().add(new GreenBricks(60, 0));
		bricks.getBucket().add(new OrangeBricks(80, 0));
		bricks.getBucket().add(new GrayBricks(100, 0));

		int blueCounter = 0;

		for (Bricks brick : bricks.getBucket()) {
			if (brick.getColor().equals("Blue")) {
				blueCounter++;
			}
		}

		assertEquals(blueCounter, 1);

		bricks.getBucket().get(1).gotHit();
		bricks.getBucket().get(1).gotHit();
		bricks.deleteInvisibleBricks();

		blueCounter = 0;

		for (Bricks brick : bricks.getBucket()) {
			if (brick.getColor().equals("Blue")) {
				blueCounter++;
			}
		}
		assertEquals(blueCounter, 0);
	}

	@Test
	/**
	 * This test will confirm power up for brickDestroyer is working which gives one
	 * hit to all bricks except for Gray walls
	 */
	void brickDestroyerTest() {
		BrickMaker bricks = new BrickMaker(100, 100);
		MainMenu.setDifficulty("easy");
		bricks.getBucket().add(new GreenBricks(20, 0));
		bricks.getBucket().add(new GreenBricks(60, 60));

		boolean gameOver = bricks.isGameOver();
		assertEquals(gameOver, false);

		bricks.hitAll();

		gameOver = bricks.isGameOver();
		assertEquals(gameOver, false);

		bricks.hitAll();

		gameOver = bricks.isGameOver();
		assertEquals(gameOver, true);

	}

	@Test
	/**
	 * This test will confirm power up for wall destroyer which eliminates all walls
	 * after certain number of continuous hits
	 */
	void wallDestroyerTest() {
		BrickMaker bricks = new BrickMaker(350, 350);
		MainMenu.setDifficulty("hard");
		bricks.makeBricks();
		bricks.deleteInvisibleBricks();
		int grayCounter = 0;
		boolean grayIsPresent = false;

		for (Bricks brick : bricks.getBucket()) {
			if (brick.getColor().equals("Gray")) {
				grayCounter++;
			}
		}

		if (grayCounter > 0) {
			grayIsPresent = true;
		} else {
			grayIsPresent = false;
		}

		assertEquals(grayIsPresent, true);

		bricks.destroyWall();
		bricks.deleteInvisibleBricks();
		grayCounter = 0;

		for (Bricks brick : bricks.getBucket()) {
			if (brick.getColor().equals("Gray")) {
				grayCounter++;
			}
		}

		if (grayCounter > 0) {
			grayIsPresent = true;
		} else {
			grayIsPresent = false;
		}

		assertEquals(grayIsPresent, false);

	}

	@Test
	/**
	 * This test confirms if sound plays and stops according to the commands passed
	 * in.
	 */
	void SoundTester() {
		Panel.setLevel(0);
		SoundPlayer soundMaker = new SoundPlayer();

		soundMaker.playSound();
		assertEquals(soundMaker.getClip().isOpen(), true);

		soundMaker.stop();
		assertEquals(soundMaker.getClip().isOpen(), false);

		soundMaker.playSoundEffect();
		assertEquals(soundMaker.getClip().isOpen(), true);

		soundMaker.stop();
		assertEquals(soundMaker.getClip().isOpen(), false);

	}

	@Test
	/*
	 * This test confirms that the paddle is moving properly
	 */
	void paddleMoveTest() {
		JPanel panel = new JPanel();
		Paddle paddle = new Paddle(Color.RED, panel);
		paddle.move(100);
		int testlocation = (int) paddle.getX();
		assertEquals(testlocation, 300);

	}

	@Test
	/*
	 * This test confirms the frame is being created and has a title
	 */
	void frameTestTitle() {
		JFrame frame = new JFrame();
		frame.setTitle("Test Title");
		String title = frame.getTitle();
		String lastword = title.substring(title.lastIndexOf(" ") + 1);
		assertEquals(lastword, "Title");
	}

	@Test
	/*
	 * This test confirms the paddle is being created with color
	 */
	void paddleColor() {
		JPanel panel = new JPanel();
		Paddle paddle = new Paddle(Color.red, panel);
		Color paddlecolor = paddle.getColor();
		assertEquals(paddlecolor, Color.red);
	}

	@Test
	/*
	 * This test is to make sure the Custom Menu element parent class is creating
	 * JButtons correctly
	 */
	void customMenuTest() {
		CustomMenuElements test = new CustomMenuElements();
		JButton testButton = test.CustomButton("Test Button", "Helvetica", "plain", 25, Color.red);
		assertEquals(testButton.getForeground(), Color.red);
	}

	@Test
	/**
	 * This test validates that the ball has color
	 */
	void ballColor() {
		JPanel testPanel = new JPanel();
		Ball ball = new Ball(100, 200, Color.green, testPanel);
		Color ballColor = ball.getColor();
		assertEquals(ballColor, Color.green);
	}

	@Test
	/**
	 * This test validates the x and y position of the ball
	 */
	void ballPosition() {
		JPanel testPanel = new JPanel();
		Ball ball = new Ball(100, 200, Color.green, testPanel);
		ball.setPositionBall(250, 250);
		int ballPosX = ball.getPosX();
		assertEquals(ballPosX, 250);

		int ballPosY = ball.getPosY();
		assertEquals(ballPosY, 250);

	}

	@Test
	/**
	 * This test validates that when two shapes are in the same position the
	 * intersection of the shapes is recognized
	 */
	void intersectionChecker() {
		Shape ball = new Shape(new Ellipse2D.Double(300, 500, 15, 15), Color.green, true);
		Shape paddle = new Shape(new RoundRectangle2D.Double(300, 500, 80, 10, 15, 15), Color.orange, true);
		boolean intersects = ball.intersection(paddle);
		assertEquals(intersects, true);

	}

	@Test
	/**
	 * This test checks if the game is paused or in play
	 */
	void pauseTest() {
		Panel testPanel = new Panel();
		boolean pause = Panel.getIsPaused();
		assertEquals(pause, true);

	}

	@Test
	/**
	 * This test checks if the sort mechanism in the players class works
	 * appropriately
	 */
	void playerTest() {
		Player player1 = new Player("John", 225);
		Player player2 = new Player("Ben", 150);
		Player player3 = new Player("Ali", 178);
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(player1);
		playerList.add(player2);
		playerList.add(player3);

		assertEquals(playerList.get(1).getUsername(), "Ben");

		playerList.sort(null);
		assertEquals(playerList.get(1).getUsername(), "Ali");

	}

}
