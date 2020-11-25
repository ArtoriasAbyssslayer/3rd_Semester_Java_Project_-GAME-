/*
* Γρηγόριος Παυλάκης - ΑΕΜ 9571 (τηλ. 697558826) <grigpavl@ece.auth.gr>
* Χάρης Φίλης - ΑΕΜ 9449 (τηλ. 6972434854) <charisfilis@ece.auth.gr>
*/

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import ds19SnakeGame.Core.*;
import ds19SnakeGame.MiniMaxPlayer.*;

public class Game {
	public int round;
	
	public Game() {
		round = 0;
	}
	
	public Game(int r) {
		round = r;
	}
	
	public int getRound() {
		return round;
	}
	
	public void setRound(int r) {
		this.round = r;
	}
	
	public Map<Integer, Integer> setTurns(ArrayList<Object> players) {
		// hashmap k: die value, v: player id
		Map<Integer, Integer> turn = new HashMap<Integer, Integer>();
		// index for the player list
		int i = 0; 
		
		while(true) {
			// roll the die for the current player
			int die = 1 + (int)(Math.random() * 6);
			/*
			 * since the map has die numbers as keys, we just
			 * need to make sure there's only one player assigned
			 * to each number, the player turns can be inferred by
			 * traversing the die numbers from smallest to largest
			 */
			if (turn.get(die) == null && i < players.size()) {
				turn.put(die, ((Player) players.get(i)).getPlayerId());
				i++;
			} else if (i >= players.size()) {
				break;
			} else {
				continue;
			}
		}
		
		return turn;
	}

	public static void main(String[] args) {
		Game game = new Game(1);
		Board gameBoard = new Board(10, 20, 6, 3, 3);
		
		ArrayList<Object> players = new ArrayList<Object>();
		Player p1 = new Player(1, "Player 1", 0, gameBoard);
		MiniMaxPlayer mp1 = new MiniMaxPlayer(2, "Player 2 (MM)", 0, gameBoard);
		players.add(p1);
		players.add(mp1);
		
		Map<Integer, Integer> turns = game.setTurns(players);
		int[] playerSequence = new int[turns.size()];
		int placeIndex = 0;
		
		for (int i = 0; i <= 6; i++) {
			if (turns.get(i) != null) {
				playerSequence[placeIndex] = turns.get(i);
				placeIndex++;
			}
		}
		
		int[] p1Pos = {1, 0, 0, 0, 0};  //initial position of the random player
		Integer[] mp1Pos = {0, 0, 0, 0, 0, 0};  //just to keep track of the player's absolute position
		mp1.getPath().add(mp1Pos);
		int currentPlayer = 0;
		
		do {
			if (currentPlayer >= players.size()) {
				currentPlayer = 0;
			}
			
			if (playerSequence[currentPlayer] == p1.getPlayerId()) {
				p1Pos = p1.move(p1Pos[0], (int) (1 + Math.random() * 6));
			}
			
			if (playerSequence[currentPlayer] == mp1.getPlayerId()) {
				mp1Pos[0] = mp1.getNextMove(mp1Pos[0]);
			}
			
			currentPlayer++;
		}
		while (p1Pos[0] < gameBoard.getM() * gameBoard.getN() 
				&& mp1Pos[0] < gameBoard.getM() * gameBoard.getN());
		
		mp1.statistics(game.round);
		
		double rank1 = (p1.getScore() * 0.35) + (p1Pos[0] * 0.65);
		double rank2 = (mp1.getScore() * 0.35) + (mp1Pos[0] * 0.65);
		
		System.out.println("Rounds played: " + game.round);
		System.out.println("Player 1 (random-moving) score: " + p1.getScore());
		System.out.println("Player 2 (minimax) score: " + mp1.getScore());
		
		if (rank1 > rank2) {
			System.out.println("Player 1 wins!");
		}
		else {
			System.out.println("Player 2 wins!");
		}
	}

}