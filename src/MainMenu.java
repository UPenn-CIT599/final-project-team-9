import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

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
    private JPanel nameReminder, nameFieldPanel;
    private JTextField nameField;
    private JTable highscoreTable;
    private JScrollPane highscoreScroll;
    private JLabel titleNameLabel, nameReminderLabel;
    private JButton easyButton, medButton, hardButton, exitButton;
    private JFrame window = new JFrame();
    private String playerName, gameDifficulty;
    private File highscoreFile = new File("high_score_list.txt");
    
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
        titleNamePanel.setBounds(100, 50, 600, 100);
        titleNamePanel.setBackground(Color.MAGENTA);

        //jlabel for title
        titleNameLabel = new JLabel("Brick Breaker");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(new Font("Helvetica", Font.BOLD, 75));
        
        //jpanel for name field
        nameFieldPanel = new JPanel();
        nameFieldPanel.setBounds(200, 175, 400, 50);
        nameFieldPanel.setBackground(Color.LIGHT_GRAY);
        
        //jtextfield for name
        nameField = new JTextField("Please enter your name");
        nameField.setSize(1000, 50);
        nameField.setFont(new Font("Helvetica", Font.PLAIN,25));
        //clear the field when focused 
        nameField.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                nameField.setText("");
            }
        });
//         nameField.addFocusListener(new FocusListener() {
//            public void focusGained(FocusEvent e) {
//                //nameField.setText("");
//            }
//            public void focusLost(FocusEvent arg0) {
//                nameField.setText("Please enter your name");
//            }
//        });
        
        //add ball bouncing around in the background
        
        
        
        //easy button panel
        easyButtonPanel = new JPanel();
        easyButtonPanel.setBounds(100, 400, 160, 50);
        easyButtonPanel.setBackground(Color.green);
        
        //easy button
        easyButton = new JButton("Easy");
        easyButton.setFont(new Font("Helvetica", Font.PLAIN, 25));
        easyButton.setOpaque(false);
        easyButton.setContentAreaFilled(false);
        easyButton.setBorderPainted(false);
        easyButton.setForeground(Color.white);
        easyButton.addActionListener(this);
        
        
        //medium button panel
        medButtonPanel = new JPanel();
        medButtonPanel.setBounds(300, 400, 160, 50);
        medButtonPanel.setBackground(Color.orange);
        
        //medium button
        medButton = new JButton("Medium");
        medButton.setFont(new Font("Helvetica", Font.PLAIN, 25));
        medButton.setOpaque(false);
        medButton.setContentAreaFilled(false);
        medButton.setBorderPainted(false);
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
        hardButton.setOpaque(false);
        hardButton.setContentAreaFilled(false);
        hardButton.setBorderPainted(false);
        hardButton.setForeground(Color.white);
        hardButton.addActionListener(this);
        
        //high score button panel
        highscorePanel = new JPanel();
        highscorePanel.setBounds(200, 225, 400, 150);
        highscorePanel.setBackground(Color.LIGHT_GRAY);
        
        //high score JTable
        ArrayList<String> highscoreArray = new ArrayList<String>();
        try {
            Scanner s = new Scanner(highscoreFile);
            int i = 0;
            while(s.hasNextLine()) {
                highscoreArray.add(i, s.nextLine());
                i++;
            }
            s.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        
        String[] columns = {"", "High Scores",""};
        String[][] data = new String[11][3];
        for(int i = 0; i < highscoreArray.size(); i++) {
            int j = 0;
            for(String word: highscoreArray.get(i).split(" ")) {
                data[i][j] = word;
                j++;
            }
        }
        highscoreTable = new JTable(data, columns){
            public boolean isCellEditable(int data, int columns) {
                return false;
            }
            public Component prepareRenderer(TableCellRenderer r, int data, int columns) {
                Component c = super.prepareRenderer(r, data, columns);
                c.setBackground(Color.LIGHT_GRAY);
                c.setFont(new Font("Helvetica",Font.BOLD,14));
                return c;
            }
        };
        highscoreTable.setPreferredScrollableViewportSize(new Dimension(350,100));
        highscoreTable.setFillsViewportHeight(true);
        highscoreScroll = new JScrollPane(highscoreTable);
        
        
        /*//high score JTextArea
        highscoreArea = new JTextArea("",0,6);
        highscoreArea.setBackground(Color.LIGHT_GRAY);
        try {
            Scanner s = new Scanner(highscoreFile);
            ArrayList<String> highscoreArray = new ArrayList<String>();
            int i = 0;
            while(s.hasNextLine()) {
                highscoreArray.add(i, s.nextLine());
                i++;
            }
            for(int j=0; j < highscoreArray.size(); j++ ) {
                highscoreArea.append(highscoreArray.get(j) + "\n"); 
            }
            
            s.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }*/
        
        /*//high score button
        highscoreButton = new JButton("High Scores");
        highscoreButton.setFont(new Font("Helvetica", Font.PLAIN, 35));
        highscoreButton.setOpaque(false);
        highscoreButton.setContentAreaFilled(false);
        highscoreButton.setBorderPainted(false);
        highscoreButton.setForeground(Color.white);
        highscoreButton.addActionListener(this);*/
        
        //exit button panel
        exitPanel = new JPanel();
        exitPanel.setBounds(350, 515, 50, 40);
        exitPanel.setBackground(Color.LIGHT_GRAY);
        
        //exit button
        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Helvetica", Font.ITALIC, 15));
        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setForeground(Color.black);
        exitButton.addActionListener(this);
        
        //name reminder panel
        nameReminder = new JPanel();
        nameReminder.setBounds(225, 480, 350, 35);
        nameReminder.setBackground(Color.LIGHT_GRAY);
        nameReminder.setVisible(false);
        
        //exit button
        nameReminderLabel = new JLabel("Please Remember to enter your name!");
        nameReminderLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        nameReminderLabel.setForeground(Color.red);
        
        //build panel
        titleNamePanel.add(titleNameLabel);
        nameFieldPanel.add(nameField);
        easyButtonPanel.add(easyButton);
        medButtonPanel.add(medButton);
        hardButtonPanel.add(hardButton);
        highscorePanel.add(highscoreScroll);
        nameReminder.add(nameReminderLabel);
        exitPanel.add(exitButton);
        container.add(nameFieldPanel);
        container.add(titleNamePanel);
        container.add(highscorePanel);
        container.add(easyButtonPanel);
        container.add(medButtonPanel);
        container.add(hardButtonPanel);
        container.add(nameReminder);
        container.add(exitPanel);
        
        
        
        //set visible (keep as last line)
        window.setVisible(true);
        
    }
    
    

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        String input = nameField.getText().trim(); //spaces removed from name
        nameField.setText(input);
        
        
        if (action.equals("Easy")) {
            if(input.equalsIgnoreCase("") || input.equalsIgnoreCase("Please enter your name")) {
                
                nameReminder.setVisible(true);
                nameField.setText("Please enter your name");
            }
            else{
                this.gameDifficulty = "Easy";
                this.playerName = input;
                window.dispose(); 
                //method to launch new game with easy
            }
           
        }
        else if(action.equals("Medium")) {
            if(input.equalsIgnoreCase("") || input.equalsIgnoreCase("Please enter your name")) {
                
                nameReminder.setVisible(true);
                nameField.setText("Please enter your name");
            }
            
            else{
                this.gameDifficulty = "Medium";
                this.playerName = input;
                window.dispose(); 
                //method to launch new game with easy
            }
            
        }
        else if(action.equals("Hard")) {
            if(input.equalsIgnoreCase("") || input.equalsIgnoreCase("Please enter your name")) {
                
                nameReminder.setVisible(true);
                nameField.setText("Please enter your name");
            }
            
            else{
                this.gameDifficulty = "Hard";
                this.playerName = input;
                window.dispose(); 
                //method to launch new game with easy
            }
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
        return gameDifficulty;
        
    }
    
    /**
     * This getter method returns the users name  
     * @return
     */
    public String getUserName() {
        return playerName;
        
    }
    
    public static void main(String[] args){
        
      new MainMenu();
        
    }



}
