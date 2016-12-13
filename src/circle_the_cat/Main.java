package circle_the_cat;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CircleTheCat state = new CircleTheCat();
		Player cat = new MctsPlayer();
		Scanner sc = new Scanner(System.in);
		String line;
		printMatrix(state);
		while (!state.isTerminal()) {
			line = sc.nextLine();
			String[] coordinate = line.split(" ");
			int row = Integer.parseInt(coordinate[0]);
			int col = Integer.parseInt(coordinate[1]);
			state.process(new CircleTheCatAction(row, col));
			printMatrix(state);
			System.out.println(state.getTurn());
			state.process(cat.getAction(state));
			printMatrix(state);
			System.out.println(state.getTurn());
		}
	}
	
	public static void printMatrix(CircleTheCat state) {
		for (int row = 0; row < 9; row++) {
			if (row % 2 == 1)
				System.out.print(" ");
			for (int col = 0; col < 9; col++) {
				
				System.out.print(state.getValue(new MyPoint(row,col)) + " ");
			}
			System.out.println();
		}
	}
}
