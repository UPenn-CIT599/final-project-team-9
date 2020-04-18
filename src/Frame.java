import javax.swing.JFrame;


public class Frame extends JFrame{
    
    private final int frameheight = 600;
    private final int framewidth = 600;

    private Panel panel = new Panel(3, "hard");
    
    public Frame() {
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(frameheight, framewidth);
        add(panel);
        setTitle("Test");
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        
        
    }
    
    public static void main(String[] args) {
    	
    	Frame frame = new Frame();
        
    }
    
    
}