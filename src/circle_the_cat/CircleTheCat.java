package circle_the_cat;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mctslib.game.State;


public class CircleTheCat extends State<CircleTheCatAction> {
	private int numPlayers;
	private int state;
	private int turn;
	private int winner;
	
	public CircleTheCat(int np) {
		numPlayers = np;
		state = 0;
		turn = 0;
		winner = -1;
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
		CircleTheCat ret = new CircleTheCat(numPlayers);
		ret.state = state;
		ret.turn = turn;
		ret.winner = winner;
		return ret;
	}

	@Override
	public void process(CircleTheCatAction action) {
		state += action.get();
		
		if (isTerminal()) {
			winner = turn;
		}
		
		turn += 1;
		if (turn >= numPlayers) {
			turn = 0;
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
