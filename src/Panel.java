import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Panel extends JPanel {

	private javax.swing.Timer timer;
	private Ball ball;
	private Paddle paddle;
	private BrickMaker bricks;
	public static int level = 1, lives = 3, score;
	private int lastMove;
	private ArrayList<Bricks> brickBucket;
	private SoundPlayer backgoundMusic, brickExplosion, gameOver, lifeOver, winMusic, levelUp;

	public Panel() {

		ball = new Ball(200, 400, Color.red, this);
		paddle = new Paddle(Color.black, this);
		bricks = new BrickMaker(600, 600);
		bricks.makeBricks();
		brickBucket = bricks.getBucket();

		createMusic();
		

		timer = new javax.swing.Timer(5, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if ((int) ball.getY() > (600 + ball.getSize())) {
					ball = new Ball(300, 450, Color.red, Panel.this);
					lives--;
					if (lives == 0) {
						backgoundMusic.stop();
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

				for (Bricks bricks : brickBucket) {
					if (ball.intersection(bricks.getRect()) || bricks.getRect().intersects(ball.getRect())) {
						if (bricks.getColor().equals("Gray") == false) {
							bricks.gotHit();
							score++;
						}
						if (ball.getX() > 0) {
							if (ball.getPosY() > bricks.getY()) {
								ball.down();
							} else if (ball.getPosY() < bricks.getY()) {
								ball.up();
							}
							if (ball.getDx() > -1) {
								ball.setDx(ball.getDx() - 0.5);
							}

						} else if (ball.getX() < 0) {
							if (ball.getPosY() > bricks.getY()) {
								ball.down();
							} else if (ball.getPosY() < bricks.getY()) {
								ball.up();
							}
							if (ball.getDx() < 1) {
								ball.setDx(ball.getDx() + 0.5);
							}

						}

					}
				}
				ball.move();
				bricks.deleteInvisibleBricks();
				if (bricks.isGameOver()) {
					level++;

					if (level < 4) {
						levelUp.playSoundEffect();
						try {
							Thread.sleep(4000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						brickBucket.clear();
						bricks = new BrickMaker(600, 600);
						bricks.makeBricks();
						brickBucket = bricks.getBucket();
						backgoundMusic.stop();
						backgoundMusic = new SoundPlayer();
						backgoundMusic.playSound();
						ball = new Ball(300, 450, Color.red, Panel.this);

					} else {
						backgoundMusic.stop();
						if(level == 4) {
							winMusic.playSoundEffect();
							
						}
						
					}

					

				}
				if(score % 30 == 0 && level == 1 && score != 0) {
					bricks.hitAll();
					brickExplosion.playSoundEffect();
					score++;
				}
				else if(score % 40 == 0 && level == 2 && score != 0) {
					bricks.hitAll();
					brickExplosion.playSoundEffect();
					score++;
				}
				else if(score % 50 == 0 && level == 3 && score != 0) {
					bricks.hitAll();
					brickExplosion.playSoundEffect();
					score++;
				}
				if (level == 4 || lives == 0) {
					repaint();
					timer.stop();
				}

				repaint();
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
			g2.setFont(new Font("Helvetica", Font.BOLD, 20));
			g2.setColor(Color.black);
			g2.drawString(MainMenu.getUserName() + " - " + MainMenu.getDifficulty() + " - Level " + level + " - Score "
					+ score + " - Lives " + lives, 0, 600);

		}

	}
	
	private boolean checkHighScore() {
		Player currentPlayer = new Player(MainMenu.getUserName(), score);
		HighScore highScore = new HighScore(currentPlayer);
		highScore.extractHighScore();
		if (highScore.checkHighScore()) {
			highScore.writeScore();
			return true;
		}
		return false;
	}
	
	private void createMusic() {
		backgoundMusic = new SoundPlayer();
		backgoundMusic.playSound();
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

}