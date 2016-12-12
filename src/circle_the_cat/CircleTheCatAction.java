package circle_the_cat;

import mctslib.game.Action;

public class CircleTheCatAction extends Action {
	private int row, col; // point to be moved to
	// int actor; // 0 for human's turn, 1 for cat's turn
	public CircleTheCatAction(int row, int col) {
		// TODO Auto-generated constructor stub
		this.set(row, col);
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if (o instanceof CircleTheCatAction) {
			CircleTheCatAction c = (CircleTheCatAction) o;
			return row == c.row && col == c.col; // && actor == c.actor;
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void set(int row, int col) {
		if (row < 0 || row > 8 || col < 0 || col > 8) {
			throw new IllegalArgumentException();
		}
		//this.actor = actor;
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}

	@Override
	public String toString() {
		return "";
	}
}
