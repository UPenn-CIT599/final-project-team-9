import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class Panel extends JPanel{
    
    private javax.swing.Timer timer;
    private Ball ball;
    private Paddle paddle;
    
    public Panel() {
        
        ball = new Ball(200,400,Color.red, this);
        paddle = new Paddle(Color.black, this);
        timer = new javax.swing.Timer(5, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ball.move();
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
    
 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        ball.draw(g2);
        paddle.draw(g2);
        //bricks.draw(g);
    }

}