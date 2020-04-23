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
        setEnabled(true);
        setVisible(true);
        setResizable(false);
        
        while (Panel.getLevel() < 4 && Panel.getLives() > 0) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
        
        try {
			Thread.sleep(9000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
        
        if(panel.getHighScore().checkHighScore()) {
        	panel.getHighScore().writeScore();
        }
        
        removeAll();
		setEnabled(false);
		setVisible(false);
		dispose();

        
        
    }
    
    
    
}