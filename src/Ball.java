

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



/**
 * This class initializes ball for game play
 * Sets/gets position  
 * @author jacob. muizz, raheel
 */

public class Ball extends Shape{
    
    private int posX;
    private int posY;
    private double dx = -1;
    private double dy = -1;
    private final static int size = 10;
    private JPanel panel;
    private int speed = 1;
    
    /**
     * This constructor will create the ball for game play
     * Includes speed and position to start 
     */
    
    //draw the ball
    public Ball(int posX, int posY, Color color, JPanel panel){
        super(new Ellipse2D.Double(posX, posY, size, size), color, true);
        this.posX = posX;
        this.posY = posY;
        this.panel = panel;
        
                        
    }
    
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
    
    public int getSize() {
    	return size;
    }
    
    public int getSpeed() {
    	return speed;
    }
    
    public Rectangle2D getRect() {
		return new Rectangle(this.posX, this.posY, this.size, this.size);
	}
    
    public void setSpeed(int speed) {
    	this.speed = speed;
    }
    
    public double getDx() {
    	return dx;
    }
    
    public void setDx(double dx) {
        this.dx = dx;
    }
    
    public double getDy() {
    	return dy;
    }
   
    /**
     * This method will set position X and position Y for ball location 
     */
    public void setPositionBall(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
               
    }
    
    //move the ball
    public void move() {
        if(getX() + dx < 0) {
            dx = 1;
        }
        if(getX() + getWidth() + dx > panel.getWidth()) {
            dx = -1;
        }
        if(getY() + dy < 0) {
            dy = 1;
        }
        /*
        if(getY() + getHeight() + dy > panel.getHeight()) {
            dy = -1;
        }
        */
        super.move(dx, dy);
    }
  
    
    public void right() {
    	dx = 1 * speed;
    }
    
    public void left() {
    	dx = -1 * speed;
    }
    
    public void up() {
    	dy = -1 * speed;
    }
    
    public void down() {
    	dy = 1 * speed;
    }


}