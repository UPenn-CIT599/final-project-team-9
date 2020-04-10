import org.newdawn.slick.*;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.lwjgl.input.*;

/**
 * This class initializes paddle for game play
 * Sets/gets position  
 * Adjusts paddle position based on user input
 * @author jacob, muizz, raheel
 *
 */
public class Paddle implements ElementInterface{
    
    //private fields 
    private int width = 150;
    private int height = 20;
    //private int left;
    //private int right;
    private Rectangle paddle;
    private Color color;
    private float x;
    private float startingX;
    private int paddlespeed;
    
    //public fields
    public float yPaddle = height - height / 10 ;
    

    /**
     * This constructor initializes the paddle for game play
     * Takes into account difficulty selected prior to game start (easy, medium, hard)
     * Variables include size, color, position 
     */
    public Paddle(String difficulty, Color paddleColor) {
       
        
        //need a getter in gameboard for width of board
        startingX = GameBoard.width/2;
        
        if(difficulty == "easy") {
            paddle = new RoundedRectangle(startingX,yPaddle, width, height, 10);
            this.color = paddleColor;
            this.paddlespeed = 50;
        }
        else if (difficulty == "medium") {
            int medWidth = (int) (this.width*.95);
            paddle = new RoundedRectangle(startingX, yPaddle, medWidth, height,10);
            this.color = paddleColor;
            this.paddlespeed = 60;
        }
        else if(difficulty == "hard") {
            int hardWidth = (int) (this.width*.9);
            paddle = new RoundedRectangle(startingX, yPaddle, hardWidth, height,10);
            this.color = paddleColor;
            this.paddlespeed = 70;
        }
        
    }
    
    @Override
    public void update() {
        
    }
    
    @Override
    public void display() {
        ShapeRenderer.fill(paddle);
    }     
    
    float getX_pos() {
        return this.x;
    }
    
    float getY_pos() {
        return this.yPaddle;
    }
       
    
    /**
     * this method is the key listener for left and right 
     */
    @Override
    public void keyPressed(KeyEvent arg0) {
        int keyCode = arg0.getKeyCode();
        switch ( keyCode ) {
            case KeyEvent.VK_RIGHT:
                this.x += paddlespeed;
                break;
            case KeyEvent.VK_LEFT:
                this.x -= paddlespeed;
                break;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    
}

