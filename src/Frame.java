import javax.swing.JFrame;

import testing.Frame;
import testing.Panel;


public class Frame extends JFrame{
    
    private final int frameheight = 600;
    private final int framewidth = 600;

    private Panel panel = new Panel();
    
    public Frame() {
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(frameheight, framewidth);
        add(panel);
        setTitle("Test");
        setVisible(true);
        setResizable(false);
        
    }
    
    public static void main(String[] args) {
        Frame frame = new Frame();
    }
    
    
}