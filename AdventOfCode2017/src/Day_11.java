import java.util.List;

/*
 * URL: http://adventofcode.com/2017/day/11
 * --- Day 11: Hex Ed ---
 * Crossing the bridge, you've barely reached the other side of the stream when a program comes up to you, clearly in distress. "It's my child process," she says, "he's gotten lost in an infinite grid!"
 * Fortunately for her, you have plenty of experience with infinite grids.
 * Unfortunately for you, it's a hex grid.
 * 
 * The hexagons ("hexes") in this grid are aligned such that adjacent hexes can be found to the north, northeast, southeast, south, southwest, and northwest:
 * 
 *   \ n  /
 * nw +--+ ne
 *   /    \
 * -+      +-
 *   \    /
 * sw +--+ se
 *   / s  \
 *   
 * You have the path the child process took. Starting where he started, you need to determine the fewest number of steps required to reach him. (A "step" means to move from the hex you are in to any adjacent hex.)
 * For example:
 * - ne,ne,ne is 3 steps away.
 * - ne,ne,sw,sw is 0 steps away (back where you started).
 * - ne,ne,s,s is 2 steps away (se,se).
 * - se,sw,se,sw,sw is 3 steps away (s,s,sw).
 * 
 * Your puzzle answer was 796.
 * 
 * --- Part Two ---
 * How many steps away is the furthest he ever got from his starting position?
 * 
 * Your puzzle answer was 1585.
 */
public class Day_11 extends Day_00 {
	private final String INPUT_FILE = "files\\day_11_input.txt";

	// Check: https://www.redblobgames.com/grids/hexagons/#distances
	private enum Direction {
		N("n", 0, 1, 1), NW("nw", -1, 1, 0), NE("ne", 1, 0, 1), S("s", 0, -1, -1), SW("sw", -1, 0, -1), SE("se", 1, -1,
				0);

		private final String operation;
		private final int x_translation;
		private final int y_translation;
		private final int z_translation;

		Direction(String operation, int x_translation, int y_translation, int z_translation) {
			this.operation = operation;
			this.x_translation = x_translation;
			this.y_translation = y_translation;
			this.z_translation = z_translation;
		}

		public String operation() {
			return operation;
		}

		public static Direction fromString(String text) {
			for (Direction b : Direction.values()) {
				if (b.operation().equalsIgnoreCase(text)) {
					return b;
				}
			}
			return null;
		}
	}

	private class Point3D {
		private int x;
		private int y;
		private int z;

		public Point3D(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	public static void main(String[] args) {
		new Day_11();
	}

	@Override
	public void solvePart1() {
		List<String> lines = getLines(INPUT_FILE);

		String[] steps = lines.get(0).split(",");
		int x_pos = 0;
		int y_pos = 0;
		int z_pos = 0;
		for (String step : steps) {
			Direction direction = Direction.fromString(step);
			x_pos += direction.x_translation;
			y_pos += direction.y_translation;
			z_pos += direction.z_translation;
		}

		Point3D origin = new Point3D(0, 0, 0);
		Point3D currentPosition = new Point3D(x_pos, y_pos, z_pos);
		System.out.println("Part 1 steps: " + getCubeDistanceToOrigin(currentPosition, origin));
	}

	@Override
	public void solvePart2() {
		List<String> lines = getLines(INPUT_FILE);

		String[] steps = lines.get(0).split(",");

		int x_pos = 0;
		int y_pos = 0;
		int z_pos = 0;
		Point3D origin = new Point3D(0, 0, 0);
		int maxDistance = Integer.MIN_VALUE;
		for (String step : steps) {
			Direction direction = Direction.fromString(step);
			x_pos += direction.x_translation;
			y_pos += direction.y_translation;
			z_pos += direction.z_translation;

			Point3D currentPosition = new Point3D(x_pos, y_pos, z_pos);
			int currentDistance = getCubeDistanceToOrigin(currentPosition, origin);

			if (currentDistance > maxDistance) {
				maxDistance = currentDistance;
			}
		}

		System.out.println("Part 2 distance: " + maxDistance);
	}

	// Check: https://www.redblobgames.com/grids/hexagons/#distances
	private int getCubeDistanceToOrigin(Point3D a, Point3D b) {
		return (Math.abs(a.x - b.x) + Math.abs(a.y - b.y) + Math.abs(a.z - b.z)) / 2;
	}
}