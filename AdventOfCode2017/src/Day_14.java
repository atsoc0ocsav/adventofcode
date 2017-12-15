import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utils.Printer;

/*
 * URL: http://adventofcode.com/2017/day/14
 * --- Day 14: Disk Defragmentation ---
 * Suddenly, a scheduled job activates the system's disk defragmenter. Were the situation different, you might sit and watch it for a while, but today, you just don't have that kind of time. It's soaking up valuable system resources that are needed elsewhere, and so the only option is to help it finish its task as soon as possible.
 * The disk in question consists of a 128x128 grid; each square of the grid is either free or used. On this disk, the state of the grid is tracked by the bits in a sequence of knot hashes.
 * A total of 128 knot hashes are calculated, each corresponding to a single row in the grid; each hash contains 128 bits which correspond to individual grid squares. Each bit of a hash indicates whether that square is free (0) or used (1).
 * The hash inputs are a key string (your puzzle input), a dash, and a number from 0 to 127 corresponding to the row. For example, if your key string were flqrgnkx, then the first row would be given by the bits of the knot hash of flqrgnkx-0, the second row from the bits of the knot hash of flqrgnkx-1, and so on until the last row, flqrgnkx-127.
 * The output of a knot hash is traditionally represented by 32 hexadecimal digits; each of these digits correspond to 4 bits, for a total of 4 * 32 = 128 bits. To convert to bits, turn each hexadecimal digit to its equivalent binary value, high-bit first: 0 becomes 0000, 1 becomes 0001, e becomes 1110, f becomes 1111, and so on; a hash that begins with a0c2017... in hexadecimal would begin with 10100000110000100000000101110000... in binary.
 * 
 * Continuing this process, the first 8 rows and columns for key flqrgnkx appear as follows, using # to denote used squares, and . to denote free ones:
 * ##.#.#..-->
 * .#.#.#.#   
 * ....#.#.   
 * #.#.##.#   
 * .##.#...   
 * ##..#..#   
 * .#...#..   
 * ##.#.##.-->
 * |      |   
 * V      V   
 * 
 * In this example, 8108 squares are used across the entire 128x128 grid.
 * 
 * Given your actual key string, how many squares are used?
 * 
 * Your puzzle answer was 8148.
 * 
 * 
 * --- Part Two ---
 * Now, all the defragmenter needs to know is the number of regions. A region is a group of used squares that are all adjacent, not including diagonals. Every used square is in exactly one region: lone used squares form their own isolated regions, while several adjacent squares all count as a single region.
 * 
 * In the example above, the following nine regions are visible, each marked with a distinct digit:
 * 11.2.3..-->
 * .1.2.3.4   
 * ....5.6.   
 * 7.8.55.9   
 * .88.5...   
 * 88..5..8   
 * .8...8..   
 * 88.8.88.-->
 * |      |   
 * V      V   
 * 
 * Of particular interest is the region marked 8; while it does not appear contiguous in this small view, all of the squares marked 8 are connected when considering the whole 128x128 grid. In total, in this example, 1242 regions are present.
 * 
 * How many regions are present given your key string?
 * 
 * Your puzzle answer was 8148.
 */
public class Day_14 extends Day_00 {
	private final String INPUT_FILE = "files\\day_14_input.txt";

	public static void main(String[] args) {
		new Day_14();
	}

	@Override
	public void solvePart1() {
		final int RAWS = 128;
		final int COLS = 128;

		List<String> lines = getLines(INPUT_FILE);
		String input = lines.get(0);

		int usedSquares = 0;
		for (int i = 0; i < RAWS; i++) {
			String hash = calculateHash(input + "-" + i);

			for (int j = 0; j < COLS; j++) {
				if (hash.charAt(j) == '1') {
					usedSquares++;
				}
			}
		}

		System.out.println("Part 1 used squares: " + usedSquares);
	}

	@Override
	public void solvePart2() {
		final int RAWS = 8;
		final int COLS = 8;

		List<String> lines = getLines(INPUT_FILE);
		String input = lines.get(1);

		String[][] group = new String[RAWS][COLS];
		for (int i = 0; i < RAWS; i++) {
			String hash = calculateHash(input + "-" + i);

			for (int j = 0; j < COLS; j++) {
				if (hash.charAt(j) == '1') {
					group[i][j] = "#";
				} else {
					group[i][j] = ".";
				}
			}

			Printer.printArray(group[i], true);
		}

		System.out.println();

		int groupCount = 1;
		boolean countGroup = false;
		for (int y = 0; y < RAWS; y++) {
			for (int x = 0; x < COLS; x++) {
				if (group[y][x].equals("#")) {
					// check cell on top
					if (y > 0 && !group[y - 1][x].equals("#") && !group[y - 1][x].equals(".")) {
						group[y][x] = group[y - 1][x];
						System.out.println("entrei");
					}

					// check cell on left
					if (x > 0 && !group[y][x - 1].equals("#") && !group[y][x - 1].equals(".")) {
						if (group[y][x].equals("#")) {
							group[y][x] = group[y][x - 1];
						} else {
							group[y][x - 1] = group[y][x];
						}
					}

					// check on right
					if (x < RAWS - 1 && !group[y][x + 1].equals("#") && !group[y][x + 1].equals(".")) {
						if (group[y][x].equals("#")) {
							group[y][x] = group[y][x + 1];
						} else {
							group[y][x + 1] = group[y][x];
						}
					}

					if (group[y][x].equals("#")) {
						group[y][x] = Integer.toString(groupCount);
						countGroup = true;
					}

					// check cell bellow
					if (y < RAWS - 1 && group[y + 1][x].equals("#")) {
						group[y + 1][x] = Integer.toString(groupCount);
					}

					// check cell on right
					if (x < COLS - 1 && group[y][x + 1].equals("#")) {
						group[y][x + 1] = Integer.toString(groupCount);
					}
				} else if (group[y][x].equals(".") && countGroup) {
					countGroup = false;
					groupCount++;
				}
				
				// check cell on left
				if (x > 0 && !group[y][x - 1].equals("#") && !group[y][x - 1].equals(".")) {
					group[y][x - 1] = group[y][x];
					System.out.println("Eedddsd");
				}

			}

			System.out.println(Printer.getArrayAsString(group[y], true, '\t') + "\tCount=" + groupCount);
		}

		System.out.println("Part 2 regions: " + groupCount);

	}

	private String calculateHash(String input) {
		final int NUMBER_LIST_LENGTH = 256;
		final String SUFFIX = "17, 31, 73, 47, 23";

		// Initialize the list of numbers
		int[] sparseHash = new int[NUMBER_LIST_LENGTH];
		for (int i = 0; i < sparseHash.length; i++) {
			sparseHash[i] = i;
		}

		// Get the suffix length
		String[] suffixStr = SUFFIX.replaceAll(" |\t", "").split(",");

		// Convert the ASCII input to int values
		int[] lengths = new int[input.length() + suffixStr.length];
		for (int i = 0; i < input.length(); i++) {
			lengths[i] = (int) input.charAt(i);
		}

		// Add the suffix
		for (int i = 0; i < suffixStr.length; i++) {
			lengths[i + input.length()] = Integer.parseInt(suffixStr[i]);
		}

		// Perform 64 cycles of hashing
		int currentPosition = 0;
		int skipSize = 0;
		for (int i = 0; i < 64; i++) {
			for (int length : lengths) {
				if (length <= sparseHash.length) {
					int[] subarray = getSubArray(sparseHash, currentPosition, length);
					subarray = reverse(subarray);
					mergeArrays(currentPosition, sparseHash, subarray);
					currentPosition = (currentPosition + skipSize + length) % NUMBER_LIST_LENGTH;
					skipSize++;
				} else {
					System.err.println("Argument out of range");
				}
			}
		}

		// Convert the sparse hash to the dense hash
		int[] denseHash = new int[16];
		for (int i = 0; i < NUMBER_LIST_LENGTH; i += 16) {
			int[] subarray = getSubArray(sparseHash, i, 16);

			int xor = subarray[0];
			for (int j = 1; j < subarray.length; j++) {
				xor ^= subarray[j];
			}
			denseHash[i / 16] = xor;
		}

		// Get the hexadecimal values
		String[] binaryString = new String[16];
		for (int i = 0; i < binaryString.length; i++) {
			binaryString[i] = Integer.toBinaryString(denseHash[i]);

			while (binaryString[i].length() < 8) {
				binaryString[i] = "0" + binaryString[i];
			}
		}

		// Convert to a Knot hash string
		String knotHash = "";
		for (String value : binaryString) {
			knotHash += value;
		}

		return knotHash;
	}

	private void mergeArrays(int position, int[] array, int[] subarray) {
		for (int i = 0; i < subarray.length; i++) {
			array[(position + i) % array.length] = subarray[i];
		}
	}

	private int[] getSubArray(int[] array, int position, int quantity) {
		List<Integer> numbers = new ArrayList<Integer>();

		for (int i = 0; i < quantity; i++) {
			numbers.add(array[(position + i) % array.length]);
		}

		// From: https://stackoverflow.com/a/23945015
		return numbers.stream().mapToInt(i -> i).toArray();
	}

	// From: https://stackoverflow.com/a/2137766
	public static int[] reverse(int[] arr) {
		List<Integer> list = new ArrayList<Integer>();
		for (int a : arr) {
			list.add(a);
		}

		Collections.reverse(list);

		// From: https://stackoverflow.com/a/23945015
		return list.stream().mapToInt(i -> i.intValue()).toArray();
	}
}