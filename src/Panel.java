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

public class Panel extends JPanel{
    
    private javax.swing.Timer timer;
    private Ball ball;
    private Paddle paddle;
    private BrickMaker bricks;
    private int lives = 3;
    private String difficulty, username;
    private int score;
    private int level = 1;
    private int lastMove;
    private ArrayList<Bricks> brickBucket;
    private SoundPlayer sound;
    
    
    public Panel(String difficulty, String username) {
        
        this.difficulty = difficulty;
        this.username = username;
    	
    	
        ball = new Ball(200,400,Color.red, this);
        paddle = new Paddle(Color.black, this);
        bricks = new BrickMaker(600, 600, difficulty, level);
        bricks.makeBricks();
        brickBucket = bricks.getBucket();
        
        sound = new SoundPlayer(level);
        sound.Sound();
        
        timer = new javax.swing.Timer(5, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	if((int)ball.getY()>(600+ball.getSize())) {
            		ball = new Ball(200,450,Color.red, Panel.this);
            		try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
            		lives--; //add if formula to check if lives is less than 0; will end game
            		
            	}
            	if(ball.intersection(paddle) || paddle.intersection(ball)) {
            		ball.up();
            		if ((ball.getY() + (ball.getHeight())) < paddle.getY()) {
            			if(lastMove > 0) {
            				ball.right();
            			}
            			else {
            				ball.left();
            			}
            		}
            		/*
            		if(ball.getY() + (ball.getHeight() / 2) > paddle.getY() - (paddle.getHeight() / 2) + 1) {
            			System.out.println("Hi Raheel");
            			if(ball.getDx() > 0) {
            				ball.right();
            				//left to right
            				System.out.println("Hi Muizz");

            			}
            			else if(ball.getDx() < 0) {
            				ball.left();
            				//right to left
            				System.out.println("Hi Jacob");

            			}
            		}
            		
            		*/
            		
            		/*
            		//getter/setter for dx
                    //adjust the x speed by +/-0.5 if hit on the edges
                    if(ball.getX() < paddle.getX() + paddle.getWidth()/4) {
                        ball.setdx(ball.getdx() - 0.5);
                    }
                    if(ball.getX() < (paddle.getX() + paddle.getWidth()) && ball.getX() > (paddle.getX() + paddle.getWidth() /4)) {
                        ball.setdx(ball.getdx() + 0.5);
                    }
            		*/
            	} 
            	

               for (Bricks bricks : brickBucket) {
				if(ball.intersection(bricks.getRect())) {
					bricks.gotHit();
					score++;
					ball.up();
				}
			}
            	ball.move();
            	bricks.deleteInvisibleBricks();
            	if(bricks.isGameOver()) {
            		level++;
            		bricks = new BrickMaker(600, 600, difficulty, level);
            		bricks.makeBricks();
            		brickBucket.clear();
            		brickBucket = bricks.getBucket();
            		bricks.setIsGameOver(false);
            		sound.stop();
            		sound = new SoundPlayer(level);
            		sound.Sound();
            		//check level of game and move on
            		//if level 3, end game
            		
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
    
    
    public int getLives() {
    	return lives;
    }
    
    public void setLevel(int level) {
    	this.level = level;
    }
    
 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        ball.draw(g2);
        paddle.draw(g2);
        bricks.draw(g2);
        g2.setFont(new Font("Helvetica",Font.BOLD,20));
        g2.setColor(Color.black);
        g2.drawString(username + " - Level " + level + " - Score " + score + " - Lives " + lives, 0, 600);
        
        
    }

}