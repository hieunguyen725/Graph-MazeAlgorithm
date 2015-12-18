/*
 * Hieu Trung Nguyen
 */

import java.util.*;

/*
 * This is a maze class that will generate a random perfect maze using
 * binary tree algorithm and solve the maze using recursive backtracking algorithm
 */
public class Maze {
	private int row;
	private int column;
	private boolean debug;
	private String[][] grid;

	/*
	 * Construct a new maze given the width, depth and debug mode
	 */
	public Maze(int width, int depth, boolean debug) {
		this.column = width;
		this.row = depth;
		this.debug = debug;
		// using a grid of strings as a representation of graph G(n, m)
		grid = new String[row * 2 + 1][column * 2 + 1];
		// generate maze using binary tree algorithm
		generateMaze(row, column);
		// solve maze using recursive backtracking algorithm
		solveMaze(row * 2 - 1, 1, true);
	}

	/*
	 * Display the maze to the console
	 */
	public void display() {
		for (int i = 0; i < (row * 2 + 1); i++) {
			for (int j = 0; j < (column * 2 + 1); j++) {
				if (!debug && grid[i][j].equals("V ")) {
					grid[i][j] = "  ";
				}
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	/*
	 * Generate maze using binary tree algorithm
	 * Thanks to the Buckblog for the tutorial of this maze algorithm
	 */
	private void generateMaze(int row, int column) {	
		// Generate closed cells representing graph G(n, m)
		for (int i = 0; i < (row * 2 + 1); i++) {
			for (int j = 0; j < (column * 2 + 1); j++) {
				if ((i + 1) % 2 == 1 || (j + 1) % 2 == 1) {
					grid[i][j] = "X "; 
				} else {
					grid[i][j] = "  ";
				}
			}
		}
		// Open up walls
		breakWalls();
	}

	/*
	 * Helper method for the binary tree maze generation algorithm
	 * Open up paths in the maze
	 */
	private void breakWalls() {
		Random rand = new Random();
		for (int i = 0; i < (row * 2 + 1); i++) {
			for (int j = 0; j < (column * 2 + 1); j++) {
				if ((i + 1) % 2 == 0 && (j + 1) % 2 == 0) {
					boolean breakNorth = rand.nextBoolean();
					if (i - 1 == 0 && breakNorth) {
						breakNorth = false;
					} else if ((j + 1) == (column * 2) && !breakNorth) {
						breakNorth = true;
					}
					if (breakNorth) {
						grid[i - 1][j] = "  ";
					} else {
						grid[i][j + 1] = "  ";
					}
					if (debug) {
						grid[i][j] = "V ";
						display();
					}
				}
			}
		}
		grid[row * 2][1] = "  ";
		if (debug) {
			display();
		}
		debug = false;
	}

	/*
	 * Solve maze using recursive backtracking algorithm
	 * Thanks to cs.bu.edu for the tutorial of this algorithm
	 */
	private boolean solveMaze(int x, int y, boolean pathOpen) {
		// base cases
		if ((x < 0) || (y < 0) || (x > row * 2) || (y > column * 2)) { // out of bound
			return false;
		} else if ((x == 1) && (y == column * 2 - 1)) { // at exit
			grid[x][y] = "+ ";
			return true;
		} else if (!pathOpen) { // no path
			return false;
		}
		grid[x][y] = "+ "; // mark cell to the solution path
		// recursive cases
		if (solveMaze(x - 2, y, !grid[x - 1][y].equals("X "))) { // head North
			return true;
		} else if (solveMaze(x, y + 2, !grid[x][y + 1].equals("X "))) { // head East
			return true;
		} else if (solveMaze(x + 2, y, !grid[x + 1][y].equals("X "))) { // head South
			return true;
		} else if (solveMaze(x, y - 2, !grid[x][y - 1].equals("X "))) { // head West
			return true;
		}
		grid[x][y] = "  "; // unmarked cell to the solution path
		return false;
	}
}

// http://weblog.jamisbuck.org/2011/2/1/maze-generation-binary-tree-algorithm
// https://www.cs.bu.edu/teaching/alg/maze/

