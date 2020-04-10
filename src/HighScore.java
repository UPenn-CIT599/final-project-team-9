import java.util.*;
import java.io.*;

/**
 * This class looks at the high score list, takes in a player, and then
 * checks/adds score the list
 * 
 * @author jacob, muizz, raheel
 *
 */

public class HighScore {

	private Player currentPlayer;
	private ArrayList<Player> players;
	private String filename;

	/**
	 * Constructor 1: Takes in information about current player assuming they will
	 * be checked against high score file repository
	 * 
	 * @param userScore
	 */
	public HighScore(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
		players = new ArrayList<Player>();
		filename = "high_score_list.txt";
	}

	/**
	 * Constructor 2: For purposes of checking high scores in file other than the
	 * native directory
	 * 
	 * @param currentPlayer
	 * @param filename
	 */
	public HighScore(Player currentPlayer, String filename) {
		this(currentPlayer);
		this.filename = filename;
	}

	/**
	 * This method checks to see if a high score has been achieved and returns t/f
	 * while adding this player to the list of players with high scores
	 */
	public boolean checkHighScore() {
		/*
		 * for(int i = 0; i < scoreList.size(); i++) { //if score is equal or higher to
		 * high score, return true if(scoreList.get(i) <= userScore) {
		 * this.scorePosition = i+1; return true; } }
		 */

		for (int i = 0; i < players.size(); i++) {
			if (currentPlayer.getScore() >= players.get(i).getScore()) {
				players.add(currentPlayer);
				return true;
			}
		}
		return false;
	}

	/**
	 * This method creates an arraylist of players in the high score file
	 */
	public void extractHighScore() {
		File highScoreFile = new File(filename);
		try {
			Scanner reader = new Scanner(highScoreFile);
			reader.nextLine();
			while (reader.hasNextLine()) {
				String row = reader.nextLine();
				String rowData[] = row.split(" ");

				Player newPlayer = new Player(rowData[1], Integer.parseInt(rowData[2]));
				players.add(newPlayer);

			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method updates the text file of highscores from the arraylist of players
	 * after sorting the scores in descending order and only keeping the first 10 on
	 * the list
	 */
	public void writeScore() {
		File highScoreFile = new File(filename);
		players.sort(null);

		try {
			PrintWriter writer = new PrintWriter(highScoreFile);
			writer.println("Rank Name Score");
			for (int i = 0; i < players.size(); i++) {
				if (i < 10) {
					writer.println(i + 1 + " " + players.get(i).getUsername() + " " + players.get(i).getScore());
				}
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	//to be deleted later - only used for internal demo purposes
	public static void main(String[] args) {
		Player newGuy = new Player("Testing");
		newGuy.setScore(95);
		HighScore gamePlay = new HighScore(newGuy);
		gamePlay.extractHighScore();
		gamePlay.checkHighScore();
		gamePlay.writeScore();
		
		Player newGuy2 = new Player("Testing2");
		newGuy.setScore(52);
		HighScore gamePlay2 = new HighScore(newGuy);
		gamePlay2.extractHighScore();
		gamePlay2.checkHighScore();
		gamePlay2.writeScore();
	}

}
