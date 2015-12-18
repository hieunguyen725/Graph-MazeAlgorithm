/*
 * Hieu Trung Nguyen
 */

/*
 * Main simulation
 */
public class Main {
	public static void main(String[] args) {
		Maze maze = new Maze(5, 5, true);
		maze.display();
		maze = new Maze(5, 5, false);
		maze.display();
		// Test 10 x 15 maze
		maze = new Maze(10, 15, false);
		maze.display();
		// test 20 x 20 maze
		maze = new Maze(15, 15, false);
		maze.display();

		/*
		 * The only test/debug method I used for the main
		 * components (generate and solve) of the assignment
		 * is display(), which is already from the Maze class.
		 */
	}
}
