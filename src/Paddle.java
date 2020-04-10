import org.newdawn.slick.*;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

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
public class Paddle implements KeyListener{
    
    private int width = 150;
    private int height = 40;
    //private int left;
    //private int right;
    private Rectangle paddle;
    private Color color;
    private int x;
    private int y = 10;
    private int paddlespeed;
    

    /**
     * This constructor initializes the paddle for game play
     * Takes into account difficulty selected prior to game start (easy, medium, hard)
     * Variables include size, color, position 
     */
    public Paddle(String difficulty, int startX, int startY, Color paddleColor) {
        
        //need a getter in gameboard for width of board
        x = GameBoard.width/2 - width/2;
        
        if(difficulty == "easy") {
            paddle = new Rectangle(startX,startY, width, height);
            this.color = paddleColor;
            this.paddlespeed = 50;
        }
        else if (difficulty == "medium") {
            int medWidth = (int) (this.width*.95);
            paddle = new Rectangle(startX, startY, medWidth, height);
            this.color = paddleColor;
            this.paddlespeed = 60;
        }
        else if(difficulty == "hard") {
            int hardWidth = (int) (this.width*.9);
            paddle = new Rectangle(startX, startY, hardWidth, height);
            this.color = paddleColor;
            this.paddlespeed = 70;
        }
        
    }
   
    int getX_pos() {
        return this.x;
    }
    
    int getY_pos() {
        return this.y;
    }
    
    public void update() {
        
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

