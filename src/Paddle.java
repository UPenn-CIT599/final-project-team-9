import java.awt.Color;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

public class Paddle extends Shape{
    
    private JPanel panel;
    private static int startx = 200;
    private static int starty = 400;
    private static int width = 50;
    private static int height = 10;
    private int speed = 10;
    
    
    public Paddle(Color color, JPanel panel) {
            super(new RoundRectangle2D.Double(startx, starty, width, height, 15, 15), color, true);
            this.panel = panel;
    }
    
    public void move (int dx) {
        if((getX() + dx >= 0) && (getX() + dx + width <= panel.getWidth())){
            move(dx,0);
        }
    }
    
    public void moveRight() {
        move(speed);
    }
    
    public void moveLeft() {
        move(-speed);
    }

}