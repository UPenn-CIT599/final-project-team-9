import java.awt.Color;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

public class Paddle extends Shape{
    
    private JPanel panel;
    private static double startx = 300;
    private static double starty = 500;
    private static double width = 80;
    private static double height = 10;
    private double speed = 30;

    
    public Paddle(Color color, JPanel panel) {
            super(new RoundRectangle2D.Double(startx, starty, width, height,15,15), color, true);
            this.panel = panel;
    }
    
    public void move (double dx) {
        if((getX() + dx >= 0 ) && (getX() + dx + width <= panel.getWidth())){
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