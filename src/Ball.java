import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;



/**
 * This class initializes ball for game play
 * Sets/gets position  
 * @author jacob. muizz, raheel
 */

public class Ball extends JPanel implements ActionListener{
    
	private int posX;
	private int posY;
	private int xDir = -1;
	private int yDir = -2;
	private Timer timer;
	private int delay;
	
    /**
     * This constructor will create the ball for game play
     * Includes speed and position to start 
     */
    public Ball(int posX, int posY, int delay){
    	
    	this.posX = posX;
    	this.posY = posY;
    	this.delay = delay;
    	timer = new Timer (delay, this);
    	timer.start();
        
    }
    
    public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	@Override
    public void actionPerformed(ActionEvent e) {
   
    	if(true) {
    		//this will change the ball's direction once it intersects with paddle
    		//need to update inputs to detect paddle; currently just arbitrary inputs
    		if(new Rectangle(posX, posY, 20, 20).intersects(new Rectangle(250, 150, 100, 8))) {
    			yDir = -yDir;
    		}
    		posX += xDir;
    		posY += yDir;
    		if(posX < 0) {
    			xDir = -xDir;
    		}
    		if(posY < 0) {
    			yDir = -yDir;
    		}
    		if(posX > 400) {
    			xDir = -xDir;
    		}
    		
    	}
    	repaint();
    }
    
    /**
     * This method will set position X and position Y for ball location 
     */
    public void setPositionBall(int posX, int posY) {
    	this.posX = posX;
    	this.posY = posY;
    	
    	
    }
    /**
     * This method displays the ball
     */
    void display() {
    	repaint();
    	
    }
    
    /**
     * This method creates the ball
     */
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.setColor(Color.RED);
    	g.fillOval(posX, posY, 20, 20);
   
    }
    
    public static void main(String[] args) {
		JFrame frame = new JFrame("Ball");
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Ball ball = new Ball(200, 400, 10);
		frame.add(ball);
		ball.display();
		
		
		
	}
	
}
