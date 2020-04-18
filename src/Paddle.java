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
    


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        g.fillRoundRect((int) x, (int) y, 
          (int) paddlewidth * (int) this.paddlesizemultiplier, (int) paddleheight, 15, 15);
  
        
    }

    
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
    
    public static void main(String[] args) {
        Paddle p = new Paddle(600, 600, "easy");
        
        JFrame jf = new JFrame();
        jf.setTitle("Paddle Test");
        jf.setSize(600, 600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(p);
        
        jf.setVisible(true);
        

    public void moveLeft() {
        move(-speed);
    }

}