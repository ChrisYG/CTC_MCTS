package circle_the_cat;

import mctslib.game.Action;

public class CircleTheCatAction extends Action {
	int number;
	public CircleTheCatAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void set(int n) {
		if (n != 1 && n != 2) {
			throw new IllegalArgumentException();
		}
		number = n;
	}
	
	public int get() {
		return number;
	}


	
	@Override
	public String toString() {
		return ""+number;
	}
}
