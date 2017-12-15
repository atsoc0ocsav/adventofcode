import java.util.Arrays;
import java.util.List;

/*
 * URL: http://adventofcode.com/2017/day/4
 * --- Day 4: High-Entropy Passphrases ---
 * A new system policy has been put in place that requires all accounts to use a passphrase instead of simply a password. A passphrase consists of a series of words (lowercase letters) separated by spaces.
 * To ensure security, a valid passphrase must contain no duplicate words.
 * 
 * For example:
 * - aa bb cc dd ee is valid.
 * - aa bb cc dd aa is not valid - the word aa appears more than once.
 * - aa bb cc dd aaa is valid - aa and aaa count as different words.
 * 
 * The system's full passphrase list is available as your puzzle input. How many passphrases are valid?
 * 
 * Your puzzle answer was 477.
 * 
 * 
 * --- Part Two ---
 * For added security, yet another system policy has been put in place. Now, a valid passphrase must contain no two words that are anagrams of each other - that is, a passphrase is invalid if any word's letters can be rearranged to form any other word in the passphrase.
 * 
 * For example:
 * - abcde fghij is a valid passphrase.
 * - abcde xyz ecdab is not valid - the letters from the third word can be rearranged to form the first word.
 * - a ab abc abd abf abj is a valid passphrase, because all letters need to be used when forming another word.
 * - iiii oiii ooii oooi oooo is valid.
 * - oiii ioii iioi iiio is not valid - any of these words can be rearranged to form any other word.
 * 
 * Under this new system policy, how many passphrases are valid?
 * 
 * Your puzzle answer was 167.
 */
public class Day_04 extends Day_00 {
	private final String INPUT_FILE = "files\\day_04_input.txt";

	public static void main(String[] args) {
		new Day_04();
	}

	@Override
	public void solvePart1() {
		List<String> lines = getLines(INPUT_FILE);
		int valid = 0;

		for (String line : lines) {
			if (!line.isEmpty()) {
				String[] words = line.split("\t| ");

				boolean isInvalid = false;
				for (int i = 0; i < words.length - 1; i++) {
					for (int j = i + 1; j < words.length; j++) {
						if (!isInvalid && words[i].equals(words[j])) {
							isInvalid = true;
						}
					}
				}

				if (!isInvalid) {
					valid++;
				}
			}
		}

		System.out.println("Part 1 valid: " + valid);
	}

	@Override
	public void solvePart2() {
		List<String> lines = getLines(INPUT_FILE);
		int valid = 0;
		for (String line : lines) {
			if (!line.isEmpty()) {
				String[] words = line.split("\t| ");

				boolean isInvalid = false;
				for (int i = 0; i < words.length - 1; i++) {
					for (int j = i + 1; j < words.length; j++) {
						if (!isInvalid && (words[i].equals(words[j])
								|| (words[i].length() == words[j].length() && isAnagram(words[i], words[j])))) {
							isInvalid = true;
						}
					}
				}

				if (!isInvalid) {
					valid++;
				}
			}
		}

		System.out.println("Part 2 valid: " + valid);
	}

	// From https://stackoverflow.com/a/15045791
	// Solution is O(NlogN), but not big worries about optimization right now
	public boolean isAnagram(String firstWord, String secondWord) {
		char[] word1 = firstWord.replaceAll("[\\s]", "").toCharArray();
		char[] word2 = secondWord.replaceAll("[\\s]", "").toCharArray();
		Arrays.sort(word1);
		Arrays.sort(word2);
		return Arrays.equals(word1, word2);
	}
}
