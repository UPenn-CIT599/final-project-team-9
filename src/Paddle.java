import org.newdawn.slick.*;

import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

/**
 * This class initializes paddle for game play
 * Sets/gets position  
 * Adjusts paddle position based on user input
 * @author jacob, muizz, raheel
 *
 */
public class Paddle extends JPanel implements ActionListener, KeyListener{
    
    private Timer tm = new Timer(2, this);
    private double x = 0, velx = 0, y = 0, vely = 0;
    private double paddleheight = 15, paddlewidth = 90;
    private String difficulty;
    private double speedmultiplier = 1;
    private double paddlesizemultiplier = 1;
    private int canvassizeX = 600, canvassizeY =600;
    
    //constructor with timer and key listener 
    //will need the canvas size 
    public Paddle(int canvasSizeX, int canvasSizeY, String difficulty) {
        
        tm.start();
        
        this.x = canvasSizeX /2;
        this.y = canvasSizeY * .8;
        this.canvassizeX = canvasSizeX;
        this.canvassizeY = canvasSizeY;
        this.difficulty = difficulty;
        
        //change paddle based on difficulty
        if(difficulty == "medium") {
            this.speedmultiplier = 1.15;
            this.paddlesizemultiplier = .90;
        }
        if(difficulty == "hard") {
            this.speedmultiplier = 1.25;
            this.paddlesizemultiplier = .80;
        }
        
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        g.fillRoundRect((int) x, (int) y, 
          (int) paddlewidth * (int) this.paddlesizemultiplier, (int) paddleheight, 15, 15);
        
    }
    
    //action listener method for a change 
    public void actionPerformed(ActionEvent e) {
        
        if (x < 0) { 
            velx = 0; 
            x = 1; 
        } 
        if( x > this.canvassizeX - (this.paddlewidth * this.paddlesizemultiplier)){ 
            velx = 0; 
            x = this.canvassizeX - (this.paddlesizemultiplier* this.paddlewidth)-1; 
        }
        if (y < 0) { 
            vely = 0; 
            y = 1; 
        } 
        if(y > this.canvassizeY - this.paddleheight) { 
            vely = 0; 
            y = this.canvassizeY - this.paddleheight-1; 
        }
         
        
        x = x + velx;
        y = y + vely;
        repaint();
        
    }
    
    //key listener for key pressed 
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if( code == KeyEvent.VK_LEFT) {
            velx = -1 * this.speedmultiplier;
            vely = 0;
        }
        if(code == KeyEvent.VK_RIGHT) {
            velx = 1 * this.speedmultiplier;
            vely = 0;
        }
        /*if(code == KeyEvent.VK_UP) {
            velx = 0;
            vely = -1 * this.speedmultiplier;
        }
        if(code == KeyEvent.VK_DOWN) {
            velx = 0;
            vely = 1 * this.speedmultiplier;
        }*/
    }
    
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {
        velx = 0;
        vely = 0;
    }
    
    public static void main(String[] args) {
        Paddle p = new Paddle(600, 600, "easy");
        
        JFrame jf = new JFrame();
        jf.setTitle("Paddle Test");
        jf.setSize(600, 600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(p);
        jf.setVisible(true);
        
    }
    
}

