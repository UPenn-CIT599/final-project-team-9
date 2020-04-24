/**
 * This class maintains data about players
 * @author jacob, muizz, raheel
 *
 */
public class Player implements Comparable {
	private String username;
	private int score;
	
	/**
	 * Constructor 1: for existing players that have scores
	 * @param username
	 * @param score
	 */
	public Player(String username, int score) {
		this.username = username;
		this.score = score;
	}
	
	/**
	 * Constructor 2: for new players/returning players playing a new game
	 * @param username
	 */
	public Player(String username) {
		this.username = username;
		score = 0;
	}
	
	/**
	 * This getter returns the username
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * This setter sets the username
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * This getter returns the score
	 * @return
	 */
	public int getScore() {
		return score;
	}

	/**
	 * This setter sets the score
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Creates a sort method for players to be ranked by scores
	 */
	@Override
	public int compareTo(Object otherPlayer) {
		Player currentPlayer = (Player) otherPlayer;
		
		if(currentPlayer.getScore() > score) {
			return 1;
		}
		else if (currentPlayer.getScore() == score) {
			return 0;
		}
		else {
			return -1;
		}
	}

}
