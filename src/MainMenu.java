import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class initializes main menu
 * Prompts user for difficulty setting and user name  
 * Displays high scores prior to game play
 * Closes main menu after selection has been made 
 * @author jacob, muizz, raheel 
 *
 */

public class MainMenu implements ActionListener{
    
    private JPanel titleNamePanel, easyButtonPanel, medButtonPanel, hardButtonPanel, highscorePanel, exitPanel;
    private JLabel titleNameLabel;
    private JButton easyButton, medButton, hardButton, highscoreButton, exitButton;
    private int x = 400, y = 600;
    private int velx = 1, vely = 1;
    private JFrame window = new JFrame();
    //method for ball display on menu 
    
    /**
     * Initializes display and welcomes the user to the game, prompts difficulty selection 
     * Calls on the helper method difficulty listener
     * Prompts user for name 
     * Displays high scores from High Score class 
     * Closes main menu window 
     */
    
    public MainMenu() {
        
        //create the new window 
        
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.LIGHT_GRAY);
        window.setLayout(null);
        window.setTitle("Menu Test");
        Container container = window.getContentPane();
        
        //jpanel for title 
        //flashing background idea 
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 100);
        titleNamePanel.setBackground(Color.MAGENTA);
        //titleNamePanel.setFont(Font.SANS_SERIF);
        
        //add ball bouncing around in the background
        
        
        
        //jlabel
        titleNameLabel = new JLabel("Brick Breaker");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(new Font("Helvetica", Font.BOLD, 75));
        
        
        //easy button panel
        easyButtonPanel = new JPanel();
        easyButtonPanel.setBounds(100, 400, 160, 50);
        easyButtonPanel.setBackground(Color.green);
        
        //easy button
        easyButton = new JButton("Easy");
        easyButton.setFont(new Font("Helvetica", Font.PLAIN, 25));
        easyButton.setBackground(Color.GREEN);
        easyButton.setForeground(Color.white);
        easyButton.addActionListener(this);
        
        
        //medium button panel
        medButtonPanel = new JPanel();
        medButtonPanel.setBounds(300, 400, 160, 50);
        medButtonPanel.setBackground(Color.orange);
        
        //medium button
        medButton = new JButton("Medium");
        medButton.setFont(new Font("Helvetica", Font.PLAIN, 25));
        medButton.setBackground(Color.orange);
        medButton.setForeground(Color.white);
        medButton.addActionListener(this);
        
        //hard button panel
        hardButtonPanel = new JPanel();
        hardButtonPanel.setBounds(500, 400, 160, 50);
        hardButtonPanel.setBackground(Color.red);
        
        //hard button
        String SQR = "Hard";
        hardButton = new JButton(SQR);
        hardButton.setFont(new Font("Helvetica", Font.PLAIN, 25));
        hardButton.setBackground(Color.red);
        hardButton.setForeground(Color.white);
        hardButton.addActionListener(this);
        
        //high score button panel
        highscorePanel = new JPanel();
        highscorePanel.setBounds(275, 250, 250, 70);
        highscorePanel.setBackground(Color.DARK_GRAY);
        
        //high score button
        highscoreButton = new JButton("High Scores");
        highscoreButton.setFont(new Font("Helvetica", Font.PLAIN, 40));
        highscoreButton.setBackground(Color.DARK_GRAY);
        highscoreButton.setForeground(Color.white);
        highscoreButton.addActionListener(this);
        
        //exit button panel
        exitPanel = new JPanel();
        exitPanel.setBounds(375, 500, 50, 35);
        exitPanel.setBackground(Color.LIGHT_GRAY);
        
        //medium button
        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Helvetica", Font.ITALIC, 15));
        exitButton.setBackground(Color.LIGHT_GRAY);
        exitButton.setForeground(Color.black);
        exitButton.addActionListener(this);
        
        //build panel
        titleNamePanel.add(titleNameLabel);
        easyButtonPanel.add(easyButton);
        medButtonPanel.add(medButton);
        hardButtonPanel.add(hardButton);
        highscorePanel.add(highscoreButton);
        exitPanel.add(exitButton);
        container.add(titleNamePanel);
        container.add(highscorePanel);
        container.add(easyButtonPanel);
        container.add(medButtonPanel);
        container.add(hardButtonPanel);
        container.add(exitPanel);
        
        
        
        //set visible (keep as last line)
        window.setVisible(true);
        
    }
    


    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Easy")) {
           window.dispose(); 
           //method to launch new game with easy
        }
        else if(action.equals("Medium")) {
            window.dispose();
            //method to launch new game with medium
        }
        else if(action.equals("Hard")) {
            window.dispose();
            //method to launch new game with hard
        }
        else if(action.equals("Exit")){
            window.dispose();
        }
        else if(action.equals("High Scores")) {
            //what do we want the high score button to do??
        }
    
    }
    
    /**
     * This getter method returns the difficulty selection 
     * @return
     */
    public String getDifficulty() {
        return null;
        
    }
    
    /**
     * This getter method returns the users name  
     * @return
     */
    public String getUserName() {
        return null;
        
    }
    
    public static void main(String[] args){
        
      new MainMenu();
        
    }



}
