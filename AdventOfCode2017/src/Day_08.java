import java.util.HashMap;
import java.util.List;

/*
 * URL: http://adventofcode.com/2017/day/8
 * --- Day 8: I Heard You Like Registers ---
 * You receive a signal directly from the CPU. Because of your recent assistance with jump instructions, it would like you to compute the result of a series of unusual register instructions.
 * Each instruction consists of several parts: the register to modify, whether to increase or decrease that register's value, the amount by which to increase or decrease it, and a condition. If the condition fails, skip the instruction without modifying the register. The registers all start at 0. The instructions look like this:
 * b inc 5 if a > 1
 * a inc 1 if b < 5
 * c dec -10 if a >= 1
 * c inc -20 if c == 10
 * 
 * These instructions would be processed as follows:
 * - Because a starts at 0, it is not greater than 1, and so b is not modified.
 * - a is increased by 1 (to 1) because b is less than 5 (it is 0).
 * - c is decreased by -10 (to 10) because a is now greater than or equal to 1 (it is 1).
 * - c is increased by -20 (to -10) because c is equal to 10.
 * 
 * After this process, the largest value in any register is 1.
 * You might also encounter <= (less than or equal to) or != (not equal to). However, the CPU doesn't have the bandwidth to tell you what all the registers are named, and leaves that to you to determine.
 * What is the largest value in any register after completing the instructions in your puzzle input?
 * 
 * Your puzzle answer was 5946.
 * 
 * 
 * --- Part Two ---
 * To be safe, the CPU also needs to know the highest value held in any register during this process so that it can decide how much memory to allocate to these operations.
 * For example, in the above instructions, the highest value ever held was 10 (in register c after the third instruction was evaluated).
 * 
 * Your puzzle answer was 6026.
 */
public class Day_08 extends Day_00 {
	private final String INPUT_FILE = "files\\day_08_input.txt";

	private enum Operation {
		INC("inc"), DEC("dec");

		private String operation;

		Operation(String operation) {
			this.operation = operation;
		}

		public String operation() {
			return operation;
		}

		public static Operation fromString(String text) {
			for (Operation b : Operation.values()) {
				if (b.operation().equalsIgnoreCase(text)) {
					return b;
				}
			}
			return null;
		}
	}

	private enum Condition {
		PLUS(">"), PLUS_EQUAL(">="), MINNOR("<"), MINNOR_EQUAL("<="), EQUALS("=="), DIFFERENT("!=");

		private String operation;

		Condition(String operation) {
			this.operation = operation;
		}

		public String operation() {
			return operation;
		}

		public static Condition fromString(String text) {
			for (Condition b : Condition.values()) {
				if (b.operation().equalsIgnoreCase(text)) {
					return b;
				}
			}
			return null;
		}
	}

	public static void main(String[] args) {
		new Day_08();
	}

	@Override
	public void solvePart1() {
		List<String> lines = getLines(INPUT_FILE);

		HashMap<String, Integer> registers = new HashMap<String, Integer>();
		for (String line : lines) {
			String[] elements = line.split("\t| ");

			// Check if the registers already exist and if not, create them
			String registerToAlter = elements[0];
			if (!registers.containsKey(registerToAlter)) {
				registers.put(registerToAlter, 0);
			}

			String registerForCondition = elements[4];
			if (!registers.containsKey(registerForCondition)) {
				registers.put(registerForCondition, 0);
			}

			int valueInRegister = registers.get(registerForCondition);
			Condition condition = Condition.fromString(elements[5]);
			int valueForCondition = Integer.parseInt(elements[6]);

			boolean executeOperation = false;
			switch (condition) {
			case PLUS:
				executeOperation = (valueInRegister > valueForCondition);
				break;
			case PLUS_EQUAL:
				executeOperation = (valueInRegister >= valueForCondition);
				break;
			case MINNOR:
				executeOperation = (valueInRegister < valueForCondition);
				break;
			case MINNOR_EQUAL:
				executeOperation = (valueInRegister <= valueForCondition);
				break;
			case EQUALS:
				executeOperation = (valueInRegister == valueForCondition);
				break;
			case DIFFERENT:
				executeOperation = (valueInRegister != valueForCondition);
				break;
			}

			if (executeOperation) {
				int valueToAlter = registers.get(registerToAlter);
				Operation operation = Operation.fromString(elements[1]);
				int valueForOperation = Integer.parseInt(elements[2]);

				switch (operation) {
				case INC:
					valueToAlter += valueForOperation;
					break;
				case DEC:
					valueToAlter -= valueForOperation;
					break;
				}

				registers.replace(registerToAlter, valueToAlter);
			}
		}

		int max = Integer.MIN_VALUE;
		for (String register : registers.keySet()) {
			int value = registers.get(register);

			if (value > max) {
				max = value;
			}
		}

		System.out.println("Part 1 largest value: " + max);
	}

	@Override
	public void solvePart2() {
		List<String> lines = getLines(INPUT_FILE);

		HashMap<String, Integer> registers = new HashMap<String, Integer>();
		int max = Integer.MIN_VALUE;
		for (String line : lines) {
			String[] elements = line.split("\t| ");

			// Check if the registers already exist and if not, create them
			String registerToAlter = elements[0];
			if (!registers.containsKey(registerToAlter)) {
				registers.put(registerToAlter, 0);
			}

			String registerForCondition = elements[4];
			if (!registers.containsKey(registerForCondition)) {
				registers.put(registerForCondition, 0);
			}

			int valueInRegister = registers.get(registerForCondition);
			Condition condition = Condition.fromString(elements[5]);
			int valueForCondition = Integer.parseInt(elements[6]);

			boolean executeOperation = false;
			switch (condition) {
			case PLUS:
				executeOperation = (valueInRegister > valueForCondition);
				break;
			case PLUS_EQUAL:
				executeOperation = (valueInRegister >= valueForCondition);
				break;
			case MINNOR:
				executeOperation = (valueInRegister < valueForCondition);
				break;
			case MINNOR_EQUAL:
				executeOperation = (valueInRegister <= valueForCondition);
				break;
			case EQUALS:
				executeOperation = (valueInRegister == valueForCondition);
				break;
			case DIFFERENT:
				executeOperation = (valueInRegister != valueForCondition);
				break;
			}

			if (executeOperation) {
				int valueToAlter = registers.get(registerToAlter);
				Operation operation = Operation.fromString(elements[1]);
				int valueForOperation = Integer.parseInt(elements[2]);

				switch (operation) {
				case INC:
					valueToAlter += valueForOperation;
					break;
				case DEC:
					valueToAlter -= valueForOperation;
					break;
				}

				if (valueToAlter > max) {
					max = valueToAlter;
				}

				registers.replace(registerToAlter, valueToAlter);
			}
		}

		System.out.println("Part 2 largest value: " + max);
	}
}