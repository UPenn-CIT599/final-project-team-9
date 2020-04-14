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
    
	private int posX = 120;
	private int posY = 350;
	private int xDir = -1;
	private int yDir = -2;
	private Timer timer;
	private int delay = 8;
	
    /**
     * This constructor will create the ball for game play
     * Includes size, color, speed, position to start 
     */
    public Ball(int posX, int posY){
    	
    	this.posX = posX;
    	this.posY = posY;
    	timer = new Timer (delay, this);
    	timer.start();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	timer.start();
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
    		//remove this so that when the ball falls below the paddle it disappears
    		if(posY > 400) {
    			yDir = -yDir;
    		}
    	}
    	repaint();
    }
    
    /**
     * This method will set position X and position Y for ball location 
     */
    /*
    public void setPositionBall(double posX, double posY) {
    	this.posX = posX;
    	this.posY = posY;
    	
    	posX = 50.0;
    	posY = 50.0;
    	
    }
    */
   
    /**
     * This method will get position X and position Y for ball location
     **/
    /*
    public int[] getPositionBall() {
        
    }
    */
    
    
    void display() {
    	repaint();
    	
    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.setColor(Color.RED);
    	g.fillOval(posX, posY, 20, 20);
   
    }
    
    public static void main(String[] args) {
		JFrame frame = new JFrame("Test");
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Ball ball = new Ball(100, 100);
		frame.add(ball);
		ball.display();
		
		
	}
	
}
