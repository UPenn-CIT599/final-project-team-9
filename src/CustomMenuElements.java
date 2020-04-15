import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CustomMenuElements{
    
    public JPanel CustomPanel(int x, int y, int width, int height, Color color) {
        JPanel panel = new JPanel();
        panel.setBounds(x,y,width,height);
        panel.setBackground(color);
        return panel;
    }
    
    public JButton CustomButton(String text, String FontName, String type, int fontSize, Color color) {
        JButton button = new JButton(text);
        if( type.equalsIgnoreCase("plain")) {
            button.setFont(new Font(FontName, Font.PLAIN, fontSize));    
        }
        if(type.equalsIgnoreCase("bold")) {
            button.setFont(new Font(FontName, Font.BOLD, fontSize));
        }
        if (type.equalsIgnoreCase("italic")) {
                button.setFont(new Font(FontName, Font.ITALIC, fontSize));            
        }
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(color);
        return button;
    }
    
}

