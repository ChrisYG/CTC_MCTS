package circle_the_cat;

import java.util.ArrayList;
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
	private MyPoint current; 
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
		current = new MyPoint(4,4);
		numBlocks = 9;
		turn = 0;
		winner = -1;
		initialize();
	}
	/**
	 * Initialize the map
	 * Randomly place blocks as 1, and cat as 8 on the map
	 */
	private void initialize() {
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
	
	private void setValue(int row, int col, int value) {
		/**
		if (row < 0 || row > 8)
			return;
		if (col < 0 || col > 8) 
			return;
		if (value != 0 && value != 1 && value != 8)
			return;**/
		this.state[row][col] = value;
	}

	// Return 1 for illegal arguments
	public int getValue(MyPoint point) {
		if (point.x < 0 || point.x > 8)
			return 1;
		if (point.y < 0 || point.y > 8)
			return 1;
		return this.state[point.x][point.y];
	}
	
	public int[][] getState() {
		return state;
	}
	
	private void switchTurn() {
		if (turn == 0)
			turn = 1;
		else
			turn = 0;
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
		for (int i = 0; i < state.length; i++) {
			copy.state[i] = state[i].clone();
		}
		copy.current.x = current.x;
		copy.current.y = current.y;
		copy.turn = turn;
		copy.winner = winner;
		return copy;
	}

	@Override
	public void process(CircleTheCatAction action) {
		if (turn == 0) { // A human's action
			setValue(action.getRow(), action.getCol(), 1);
		} else { // A cat's action
			setValue(current.x, current.y, 0);
			//System.out.println(action);
			setValue(action.getRow(), action.getCol(), 8);
			current.setValue(action.getRow(), action.getCol());
		}
		if (isTerminal()) {
			winner = turn;
		}
		switchTurn();
		
	}
	/**
	 * 返回point的下一步可走节点
	 * 由于每个点的后继可走节点最多为6个，同时对于当前节点所在行数的奇偶有关，必须
	 * 分情况讨论
	 * */
	private ArrayList<MyPoint> getNextStep(MyPoint point) {
		ArrayList<MyPoint> nextStepList = new ArrayList<MyPoint>();
		List<MyPoint> nextstep = this.getNext6Step(point);
		
		for (int i = 0; i < nextstep.size(); i++) {
			if (getValue(nextstep.get(i)) == 0)
				nextStepList.add(nextstep.get(i));
		}
		return nextStepList;
	}
	
	/**
	 * 一个封装方法，获取参数point的后继六个点，无需判断point所在行数的奇偶性
	 * 返回一个长度为6的MyPoint数组，6个节点的排列顺序如下
	 *              1     2
	 *           6   point   3
	 *              5      4
	 * 数字代表这个点的在数组中的索引号+1
	 * @return
	 * 
	 * */
	private List<MyPoint> getNext6Step(MyPoint point) {
		List<MyPoint> list = new ArrayList<MyPoint>();
		int x = point.x; 
		int y = point.y;
		if (point.x % 2 == 0) {
			// 奇数行

			if (x >= 1 && y >= 1) {
				MyPoint point1 = new MyPoint(point.x-1, point.y-1);
				list.add(point1);
			}
			if (x >= 1) {
				MyPoint point2 = new MyPoint(point.x-1, point.y);
				list.add(point2);
			}
			
			MyPoint point3 = new MyPoint(point.x, point.y+1);
			list.add(point3);
			
			MyPoint point4 = new MyPoint(point.x+1, point.y);
			list.add(point4);
			
			if (y >= 1) {
				MyPoint point5 = new MyPoint(point.x+1, point.y-1);
				list.add(point5);
				MyPoint point6 = new MyPoint(point.x, point.y-1);
				list.add(point6);
			}
		}
		else {
			// 偶数行
			if (x >= 1) {
				MyPoint point1 = new MyPoint(point.x-1, point.y);
				list.add(point1);
				MyPoint point2 = new MyPoint(point.x-1, point.y+1);
				list.add(point2);
			}
			

			
			MyPoint point3 = new MyPoint(point.x, point.y+1);
			list.add(point3);
			
			MyPoint point4 = new MyPoint(point.x+1, point.y+1);
			list.add(point4);
			
			MyPoint point5 = new MyPoint(point.x+1, point.y);
			list.add(point5);
			
			if (y >= 1){ 
				MyPoint point6 = new MyPoint(point.x, point.y-1);
				list.add(point6);
			}

		}
		return list;
	}

	@Override
	public boolean isTerminal() {
		// Cat at edge or cat surrounded by blocks
		if (current.x == 0 || current.y == 0 || current.x == 8 || current.y == 8) return true;
		if (getNextStep(current).isEmpty()) return true;
		else return false;
	}

	@Override
	public List<CircleTheCatAction> getLegalActions() {
		List<CircleTheCatAction> actions = new ArrayList<CircleTheCatAction>();
		if (turn == 0) {
			// Add all available points
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (state[i][j] == 0) {
						actions.add(new CircleTheCatAction(i, j));
					}
				}
			}
		} else {
			//System.out.println("x: " + current.x + " y: " + current.y);
			List<MyPoint> pts = getNextStep(current);
			
			for (int i = 0; i < pts.size(); i++) {
				System.out.println(pts.size());
				//11System.out.println("( " + pts.get(i).x + " ," + pts.get(i).y + ")");
				actions.add(new CircleTheCatAction(pts.get(i).x, pts.get(i).y));
			}
		}
		return actions;
		
	}

	@Override
	public Map<Integer, Double> getScore() {
		Map<Integer, Double> ret = new HashMap<Integer, Double>();
		if (isTerminal()) {
			for (int i=0; i< 2; i++) {
				double score = 0.0;
				if (i == winner) {
					score = 1.0;
				}
				ret.put(i, score);
			}
		} else {
			for (int i=0; i< 2; i++) {
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
