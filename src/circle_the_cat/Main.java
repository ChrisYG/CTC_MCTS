package circle_the_cat;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CircleTheCat game = new CircleTheCat();
	}
	
	public void printMatrix() {
		for (int row = 0; row < 9; row++) {
			if (row % 2 == 1)
				System.out.print(" ");
			for (int col = 0; col < 9; col++) {
				
				System.out.print(mapMatrix[row][col] + " ");
			}
			System.out.println();
		}
	}
}
