package circle_the_cat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import mctslib.game.State;


public class CircleTheCat extends State<CircleTheCatAction> {
	private int [][] state;
	private int row, col; // initial position for cat
	private int numBlocks; // initial number of blocks
	private int turn; // 0 for human's turn and 1 for cat's turn
	private int winner; // 0 for human, 1 for cat, -1 for non-available
	
	public CircleTheCat() {
		state = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				state[i][j] = 0;
			}
		}
		row = 4;
		col = 4;
		numBlocks = 9;
		turn = 0;
		winner = -1;
		initialize();
	}
	/**
	 * Initialize the map
	 * Randomly place blocks as 1, and cat as 8 on the map
	 */
	public void initialize() {
		Random rand = new Random();
		Set<Integer> checkSet = new HashSet<Integer>();
		for (int count = 0; count < this.numBlocks; count++) {
			int randPoint = rand.nextInt(81) + 1; 
			while (checkSet.contains(randPoint) || randPoint == 41) {
				randPoint =rand.nextInt(81) + 1; // If point's been added or at cat's location
			}
			checkSet.add(randPoint);
			int row = randPoint / 9;
			if (row == 9)
				row = 8;
			int col = randPoint % 9;
			if (col == 0)
				col = 8;
			else
				col -= 1;
			this.state[row][col] = 1;
		}
		this.state[4][4] = 8; // 神经猫的初始站位
	}
	
	public int getWinner() {
		return winner;
	}
	
	@Override
	public String toString() {
		return ""+state;
	}

	@Override
	public State<CircleTheCatAction> getDeepCopy() {
		CircleTheCat copy = new CircleTheCat();
		copy.state = state.clone(); // Deep copying the matrix?
		copy.row = row;
		copy.col = col;
		copy.turn = turn;
		copy.winner = winner;
		return copy;
	}

	@Override
	public void process(CircleTheCatAction action) {
		if (action.getActor() == 0) { // A human's action
			
			
		} else { // A cat's action
			
		}
		
		if (isTerminal()) {
			winner = turn;
		}
		
	}

	@Override
	public boolean isTerminal() {
		return state >= 10;
	}

	@Override
	public List<CircleTheCatAction> getLegalActions() {
		List<CircleTheCatAction> ret = new LinkedList<CircleTheCatAction>();
		ret.add(new CircleTheCatAction());
		ret.add(new CircleTheCatAction());
		return ret;
	}

	@Override
	public Map<Integer, Double> getScore() {
		Map<Integer, Double> ret = new HashMap<Integer, Double>();
		if (isTerminal()) {
			for (int i=0; i<numPlayers; i++) {
				double score = 0.0;
				if (i == winner) {
					score = 1.0;
				}
				ret.put(i, score);
			}
		} else {
			for (int i=0; i<numPlayers; i++) {
				ret.put(i, 0.5);
			}
		}
		
		return ret;
	}

	@Override
	public int getTurn() {
		return turn;
	}
}
