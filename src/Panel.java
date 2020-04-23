import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


import javax.swing.JPanel;

public class Panel extends JPanel {

	private javax.swing.Timer timer;
	private Ball ball;
	private Paddle paddle;
	private BrickMaker bricks;
	private Player currentPlayer; 
	private HighScore highScore;
	private static boolean isPaused;
	public static int level = 1, lives = 3, score;
	private int lastMove, hitCounter;
	private ArrayList<Bricks> brickBucket;
	private SoundPlayer backgroundMusic, brickExplosion, gameOver, lifeOver, winMusic, levelUp;

	public Panel() {

		ball = new Ball(200, 400, Color.red, this);
		paddle = new Paddle(Color.black, this);
		bricks = new BrickMaker(600, 600);
		bricks.makeBricks();
		brickBucket = bricks.getBucket();
		hitCounter = 0;
		isPaused = true;
		currentPlayer = new Player(MainMenu.getUserName());
		highScore = new HighScore(currentPlayer);
		createMusic();
		

		timer = new javax.swing.Timer(5, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				if(isPaused) {
					try {
						Thread.sleep(1000);
					}catch(Exception f) {
						f.printStackTrace();
					}
				}
				else {
					lifeLostChecker();
					paddleInteraction();
					brickInteraction();
					ball.move();
					bricks.deleteInvisibleBricks();
					levelUpChecker();
					brickDestroyer();
					wallDestroyer();
					gameOverChecker();
					repaint();
				}
				
				
			}

		});

		timer.start();
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					paddle.moveLeft();
					lastMove = -1;
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					paddle.moveRight();
					lastMove = 1;
				}
				if (e.getKeyCode() == KeyEvent.VK_P) {
					isPaused = !isPaused;
				}
				repaint();
			}
		});

		setFocusable(true);

	}

	public static void setLives(int l) {
		lives = l;
	}

	public static int getLives() {
		return lives;
	}

	public static void setLevel(int l) {
		level = l;
	}

	public static int getLevel() {
		return level;
	}

	public static void setScore(int s) {
		score = s;
	}

	public static int getScore() {
		return score;
	}
	
	public static boolean getIsPaused() {
		return isPaused;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		if (level == 4 || lives == 0) {
			g2.setFont(new Font("Helvetica", Font.BOLD, 48));
			g2.setColor(Color.black);
			g2.drawString("Game Over!", 50, 250);
			
			if(level == 4) {
				g2.drawString("You Win!!!", 50, 100);
			}
			if(checkHighScore()) {
				g2.drawString("New High Score!!", 50, 400);
			}
			
			

		} else {
			ball.draw(g2);
			paddle.draw(g2);
			bricks.draw(g2);
			g2.setFont(new Font("Helvetica", Font.BOLD, 16));
			g2.setColor(Color.black);
			g2.drawString(MainMenu.getUserName() + " - " + MainMenu.getDifficulty() + " - Level " + level + " - Score "
					+ score + " - Lives " + lives, 0, 600);
			g2.drawString("Press P to Pause/Play", 0, 620);

		}

	}
	
	
	private boolean checkHighScore() {
		highScore.getPlayer().setScore(score);
		highScore.extractHighScore();
		if (highScore.checkHighScore()) {
			return true;
		}
		return false;
	}
	
	private void createMusic() {
		backgroundMusic = new SoundPlayer();
		backgroundMusic.playSound();
		level = 4;
		brickExplosion = new SoundPlayer();
		level = 5;
		gameOver = new SoundPlayer();
		level = 6;
		lifeOver = new SoundPlayer();
		level = 7;
		winMusic = new SoundPlayer();
		level = 8;
		levelUp = new SoundPlayer();
		level = 1;
		
	}
	
	private void paddleInteraction() {
		if (ball.intersection(paddle) || paddle.intersection(ball)) {
			ball.up();
			if (ball.getX() < paddle.getX() + paddle.getWidth() / 8) {
				ball.setDx(ball.getDx() - 0.5);
			}
			if (ball.getX() < (paddle.getX() + paddle.getWidth())
					&& ball.getX() > (paddle.getX() + paddle.getWidth() / 8)) {
				ball.setDx(ball.getDx() + 0.5);
			}

		}
	}
	
	private void brickInteraction() {
		for (Bricks bricks : brickBucket) {
			if (ball.intersection(bricks.getRect()) || bricks.getRect().intersects(ball.getRect())) {
				hitCounter++;
				if (bricks.getColor().equals("Gray") == false) {
					bricks.gotHit();
					score++;
					
				}
				if (ball.getDx() >= 0) {
					if (ball.getPosY() > bricks.getY()) {
						ball.down();
					} else if (ball.getPosY() <= bricks.getY()) {
						ball.up();
					}
					
					if(ball.getDx() > 1) {
					    ball.setDx(-1);
					}
					
					if(ball.getDx() < 0.25) {
						ball.setDx(-1);
					}

					else {
						ball.setDx(ball.getDx()*-0.9);
					}
 
					
				} else if (ball.getDx() < 0) {
					if (ball.getPosY() > bricks.getY()) {
						ball.down();
					} else if (ball.getPosY() <= bricks.getY()) {
						ball.up();
					}
					
					if(ball.getDx() < -1) {
                        ball.setDx(+1);
                    }
					
					if(ball.getDx() > -0.25) {
						ball.setDx(+1);
					}
					
					else {
						ball.setDx(ball.getDx()*-0.9);	
					}
                    
				}

			}
		}
	}
	
	private void lifeLostChecker() {
		if ((int) ball.getY() > (600 + ball.getSize())) {
			ball = new Ball(300, 450, Color.red, Panel.this);
			hitCounter = 0;
			lives--;
			if (lives == 0) {
				backgroundMusic.stop();
				gameOver.playSoundEffect();
			}
			else {
				lifeOver.playSoundEffect();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	private void levelUpChecker() {
		if (bricks.isGameOver()) {
			level++;
			hitCounter = 0;

			if (level < 4) {
				repaint();
				backgroundMusic.stop();
				levelUp.playSoundEffect();
				isPaused = true;
				brickBucket.clear();
				bricks = new BrickMaker(600, 600);
				bricks.makeBricks();
				brickBucket = bricks.getBucket();
				try {
					Thread.sleep(6000);
				} catch(Exception e) {
					e.printStackTrace();
				}
				backgroundMusic = new SoundPlayer();
				backgroundMusic.playSound();
				ball = new Ball(300, 450, Color.red, Panel.this);

			} else {
				backgroundMusic.stop();
				if(level == 4) {
					winMusic.playSoundEffect();
					
				}
				
			}
		}
	}
	
	private void brickDestroyer() {
		if(hitCounter % 30 == 0 && level == 1 && hitCounter != 0 && MainMenu.getDifficulty().toLowerCase().equals("easy")) {
			bricks.hitAll();
			brickExplosion.playSoundEffect();
			hitCounter++;
		}
		else if(hitCounter % 40 == 0 && level == 2 && hitCounter != 0 && MainMenu.getDifficulty().toLowerCase().equals("easy")) {
			bricks.hitAll();
			brickExplosion.playSoundEffect();
			hitCounter++;
		}
		else if(hitCounter % 50 == 0 && level == 3 && hitCounter != 0 && MainMenu.getDifficulty().toLowerCase().equals("easy")) {
			bricks.hitAll();
			brickExplosion.playSoundEffect();
			hitCounter++;
		}
		else if(hitCounter % 45 == 0 && level == 1 && hitCounter != 0 && MainMenu.getDifficulty().toLowerCase().equals("medium")) {
            bricks.hitAll();
            brickExplosion.playSoundEffect();
            hitCounter++;
        }
        else if(hitCounter % 60 == 0 && level == 2 && hitCounter != 0 && MainMenu.getDifficulty().toLowerCase().equals("medium")) {
            bricks.hitAll();
            brickExplosion.playSoundEffect();
            hitCounter++;
        }
        else if(hitCounter % 75 == 0 && level == 3 && hitCounter != 0 && MainMenu.getDifficulty().toLowerCase().equals("medium")) {
            bricks.hitAll();
            brickExplosion.playSoundEffect();
            hitCounter++;
        }
        else if(hitCounter % 60 == 0 && level == 1 && hitCounter != 0 && MainMenu.getDifficulty().toLowerCase().equals("hard")) {
            bricks.hitAll();
            brickExplosion.playSoundEffect();
            hitCounter++;
        }
        else if(hitCounter % 80 == 0 && level == 2 && hitCounter != 0 && MainMenu.getDifficulty().toLowerCase().equals("hard")) {
            bricks.hitAll();
            brickExplosion.playSoundEffect();
            hitCounter++;
        }
        else if(hitCounter % 100 == 0 && level == 3 && hitCounter != 0 && MainMenu.getDifficulty().toLowerCase().equals("hard")) {
            bricks.hitAll();
            brickExplosion.playSoundEffect();
            hitCounter++;
        }
	}
	
	private void wallDestroyer() {
		if(hitCounter % 150 == 0 && level == 1 && hitCounter != 0 && MainMenu.getDifficulty().toLowerCase().equals("medium")) {
			bricks.destroyWall();
			brickExplosion.playSoundEffect();
			hitCounter++;
		}
		else if(hitCounter % 175 == 0 && level == 2 && hitCounter != 0 && MainMenu.getDifficulty().toLowerCase().equals("medium")) {
			bricks.destroyWall();
			brickExplosion.playSoundEffect();
			hitCounter++;
		}
		else if(hitCounter % 200 == 0 && level == 3 && hitCounter != 0 && MainMenu.getDifficulty().toLowerCase().equals("medium")) {
			bricks.destroyWall();
			brickExplosion.playSoundEffect();
			hitCounter++;
		}
		else if(hitCounter % 210 == 0 && level == 1 && hitCounter != 0 && MainMenu.getDifficulty().toLowerCase().equals("hard")) {
			bricks.destroyWall();
			brickExplosion.playSoundEffect();
			hitCounter++;
		}
		else if(hitCounter % 230 == 0 && level == 2 && hitCounter != 0 && MainMenu.getDifficulty().toLowerCase().equals("hard")) {
			bricks.destroyWall();
			brickExplosion.playSoundEffect();
			hitCounter++;
		}
		else if(hitCounter % 250 == 0 && level == 3 && hitCounter != 0 && MainMenu.getDifficulty().toLowerCase().equals("hard")) {
			bricks.destroyWall();
			brickExplosion.playSoundEffect();
			hitCounter++;
		}
		
	}
	private void gameOverChecker() {
		if (level == 4 || lives == 0) {
			repaint();
			timer.stop();
		}
	}
	
	public HighScore getHighScore() {
		return highScore;
	}

}