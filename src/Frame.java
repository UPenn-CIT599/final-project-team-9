import javax.swing.JFrame;


public class Frame extends JFrame{
    
    private final int frameheight = 650;
    private final int framewidth = 600;
    private Panel panel;
    
    public Frame() {
        
    	panel = new Panel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(framewidth, frameheight);
        add(panel);
        setTitle(MainMenu.getUserName() + "'s Brick Breaker Game");
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        
        
    }
    
    
    
}