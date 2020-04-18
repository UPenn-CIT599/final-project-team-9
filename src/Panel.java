import java.awt.Color;
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
    private String difficulty;
    private int score;
    private int level;
    
    public Panel(int level, String difficulty) {
        this.level = level;
        this.difficulty = difficulty;
    	
    	
        ball = new Ball(200,400,Color.red, this);
        paddle = new Paddle(Color.black, this);
        bricks = new BrickMaker(600, 600, difficulty, level);
        bricks.makeBricks();
        ArrayList<Bricks> brickBucket = bricks.getBucket();
        
        SoundPlayer sound;
        
        if(level == 1) {
        	sound = new SoundPlayer("Giant.wav");
        }
        else if(level == 2) {
        	sound = new SoundPlayer("Kul_Riddim.wav");
        }
        else {
        	sound = new SoundPlayer("Night Owl.wav");
        }
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
               if(ball.intersection(paddle)) {
            	   ball.up();
            	 
               }
               
               for (Bricks bricks : brickBucket) {
				if(ball.intersection(bricks.getRect())) {
					bricks.gotHit();
					ball.up();
				}
			}
            	ball.move();
            	bricks.deleteInvisibleBricks();
            	if(bricks.isGameOver()) {
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
               }
               if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                   paddle.moveRight();
               }
               repaint();
           }
        });
        
       
        
        setFocusable(true);
        
    }
    
    
    public int getLives() {
    	return lives;
    }
    
 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        ball.draw(g2);
        paddle.draw(g2);
        bricks.draw(g2);
    }

}