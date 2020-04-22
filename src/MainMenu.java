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
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

/**
 * This class initializes main menu Prompts user for difficulty setting and user
 * name Displays high scores prior to game play Closes main menu after selection
 * has been made
 * 
 * @author jacob, muizz, raheel
 *
 */

public class MainMenu extends CustomMenuElements implements ActionListener {

	private JTextField nameField;
	private JPanel nameReminder;
	private JTable highscoreTable;
	private JScrollPane highscoreScroll;
	private JLabel titleNameLabel, nameReminderLabel;
	private JFrame window = new JFrame();
	public static String playerName, gameDifficulty;
	private File highscoreFile = new File("high_score_list.txt");
	private static boolean isRunning = true, exitWithoutPlay = false;
	private SoundPlayer introMusic;

	// method for ball display on menu

	/**
	 * Initializes display and welcomes the user to the game, prompts difficulty
	 * selection Calls on the helper method difficulty listener Prompts user for
	 * name Displays high scores from High Score class Closes main menu window
	 */

	public MainMenu() {

		// create the new window

		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.LIGHT_GRAY);
		window.setLayout(null);
		window.setTitle("Main Menu");
		Container container = window.getContentPane();

		// jpanel for title
		JPanel titleNamePanel = CustomPanel(100, 50, 600, 100, Color.MAGENTA);

		// jlabel for title
		titleNameLabel = new JLabel("Brick Breaker");
		titleNameLabel.setForeground(Color.white);
		titleNameLabel.setFont(new Font("Helvetica", Font.BOLD, 75));

		// jpanel for name field
		JPanel nameFieldPanel = CustomPanel(200, 175, 400, 50, Color.LIGHT_GRAY);

		// jtextfield for name
		nameField = new JTextField("Please enter your name");
		nameField.setSize(1000, 50);
		nameField.setFont(new Font("Helvetica", Font.PLAIN, 25)); // clear the field when focused
		nameField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				nameField.setText("");
			}
		});

		// add music
		introMusic = new SoundPlayer(0);
		introMusic.playSound();

		// add ball bouncing around in the background

		// easy button panel
		JPanel easyButtonPanel = CustomPanel(100, 400, 160, 50, Color.GREEN);

		// easy button
		JButton easyButton = CustomButton("easy", "Helvetica", "plain", 25, Color.WHITE);
		easyButton.addActionListener(this);

		// medium button panel
		JPanel medButtonPanel = CustomPanel(300, 400, 160, 50, Color.ORANGE);

		// medium button
		JButton medButton = CustomButton("medium", "Helvetica", "plain", 25, Color.WHITE);
		medButton.addActionListener(this);

		// hard button panel
		JPanel hardButtonPanel = CustomPanel(500, 400, 160, 50, Color.red);

		// hard button
		JButton hardButton = CustomButton("hard", "Helvetica", "plain", 25, Color.WHITE);
		hardButton.addActionListener(this);

		// high score button panel
		JPanel highscorePanel = CustomPanel(200, 225, 400, 150, Color.LIGHT_GRAY);

		// high score JTable
		ArrayList<String> highscoreArray = new ArrayList<String>();
		try {
			Scanner s = new Scanner(highscoreFile);
			int i = 0;
			while (s.hasNextLine()) {
				highscoreArray.add(i, s.nextLine());
				i++;
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String[] columns = { "", "High Scores", "" };
		String[][] data = new String[11][3];
		for (int i = 0; i < highscoreArray.size(); i++) {
			int j = 0;
			for (String word : highscoreArray.get(i).split(" ")) {
				data[i][j] = word;
				j++;
			}
		}
		highscoreTable = new JTable(data, columns) {
			public boolean isCellEditable(int data, int columns) {
				return false;
			}

			public Component prepareRenderer(TableCellRenderer r, int data, int columns) {
				Component c = super.prepareRenderer(r, data, columns);
				c.setBackground(Color.LIGHT_GRAY);
				c.setFont(new Font("Helvetica", Font.BOLD, 14));
				return c;
			}
		};
		highscoreTable.setPreferredScrollableViewportSize(new Dimension(350, 100));
		highscoreTable.setFillsViewportHeight(true);
		highscoreScroll = new JScrollPane(highscoreTable);

		// exit button panel
		JPanel exitPanel = CustomPanel(350, 515, 50, 40, Color.LIGHT_GRAY);

		// exit button
		JButton exitButton = CustomButton("Exit", "Helvetica", "italic", 15, Color.BLACK);
		exitButton.addActionListener(this);

		// name reminder panel
		this.nameReminder = CustomPanel(225, 480, 350, 35, Color.LIGHT_GRAY);
		nameReminder.setVisible(false);

		// name reminder button
		nameReminderLabel = new JLabel("Please Remember to enter your name!");
		nameReminderLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
		nameReminderLabel.setForeground(Color.red);

		// build panel
		titleNamePanel.add(titleNameLabel);
		nameFieldPanel.add(nameField);
		easyButtonPanel.add(easyButton);
		medButtonPanel.add(medButton);
		hardButtonPanel.add(hardButton);
		highscorePanel.add(highscoreScroll);
		nameReminder.add(nameReminderLabel);

		// add elements to panels
		exitPanel.add(exitButton);
		container.add(nameFieldPanel);
		container.add(titleNamePanel);
		container.add(highscorePanel);
		container.add(easyButtonPanel);
		container.add(medButtonPanel);
		container.add(hardButtonPanel);
		container.add(nameReminder);
		container.add(exitPanel);

		// set visible (keep as last line)
		window.setLocationRelativeTo(null);
		window.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		String input = nameField.getText().replaceAll("\\s", ""); // spaces removed from name
		nameField.setText(input);

		if (action.equals("easy")) {
			if (input.equalsIgnoreCase("") || input.equalsIgnoreCase("Pleaseenteryourname")) {

				nameReminder.setVisible(true);
				nameField.setText("Please enter your name");
			} else {
				gameDifficulty = "easy";
				playerName = input;
				isRunning = false;
				introMusic.stop();
				window.dispose();
				// method to launch new game with easy
			}

		} else if (action.equals("medium")) {
			if (input.equalsIgnoreCase("") || input.equalsIgnoreCase("Pleaseenteryourname")) {

				nameReminder.setVisible(true);
				nameField.setText("Please enter your name");
			}

			else {
				gameDifficulty = "medium";
				playerName = input;
				isRunning = false;
				introMusic.stop();
				window.dispose();
				// method to launch new game with easy
			}

		} else if (action.equals("hard")) {
			if (input.equalsIgnoreCase("") || input.equalsIgnoreCase("Pleaseenteryourname")) {

				nameReminder.setVisible(true);
				nameField.setText("Please enter your name");
			}

			else {
				gameDifficulty = "hard";
				playerName = input;
				isRunning = false;
				introMusic.stop();
				window.dispose();
				// method to launch new game with easy
			}
		} else if (action.equals("Exit")) {
			exitWithoutPlay = true;
			isRunning = false;
			introMusic.stop();
			window.dispose();
			
		} else if (action.equals("High Scores")) {
			// what do we want the high score button to do??
		}

	}

	/**
	 * This getter method returns the difficulty selection
	 * 
	 * @return
	 */
	public static String getDifficulty() {
		return gameDifficulty;

	}

	public static boolean getIsRunning() {
		return isRunning;
	}

	public static void setIsRunning(boolean b) {
		isRunning = b;
	}
	
	public static boolean getExitWithoutPlay() {
		return exitWithoutPlay;
	}
	
	public static void setExitWithoutPlay(boolean b) {
		exitWithoutPlay = b;
	}

	/**
	 * This getter method returns the users name
	 * 
	 * @return
	 */
	public static String getUserName() {
		return playerName;

	}

}
