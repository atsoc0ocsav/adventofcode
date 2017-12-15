package utils;

import java.util.List;

public class Printer {

	public static void printList(List<?> objs, boolean includeSeparator, char... sep) {
		char separator = ' ';
		if (sep.length > 0) {
			separator = sep[0];
		}
		
		for (int i = 0; i < objs.size(); i++) {
			System.out.print(objs.get(i));

			if (includeSeparator && i < objs.size() - 1) {
				System.out.print(separator);
			}
		}
		System.out.println();
	}

	public static void printArray(String[] strings, boolean includeSeparator, char... sep) {
		char separator = ' ';
		if (sep.length > 0) {
			separator = sep[0];
		}
		
		for (int i = 0; i < strings.length; i++) {
			System.out.print(strings[i]);

			if (includeSeparator && i < strings.length - 1) {
				System.out.print(separator);
			}
		}
		System.out.println();
	}

	public static void printArray(int[] numbers, boolean includeSeparator, char... sep) {
		char separator = ' ';
		if (sep.length > 0) {
			separator = sep[0];
		}
		
		for (int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i]);

			if (includeSeparator && i < numbers.length - 1) {
				System.out.print(separator);
			}
		}
		System.out.println();
	}

	public static String getArrayAsString(String[] strings, boolean includeSeparator, char... sep) {
		String str = "";
		char separator = ' ';

		if (sep.length > 0) {
			separator = sep[0];
		}

		for (int i = 0; i < strings.length; i++) {
			str += strings[i];

			if (includeSeparator && i < strings.length - 1) {
				str += separator;
			}
		}

		return str;
	}

	public static String getArrayAsString(int[] numbers, boolean includeSeparator, char... sep) {
		String str = "";
		char separator = ' ';

		if (sep.length > 0) {
			separator = sep[0];
		}

		for (int i = 0; i < numbers.length; i++) {
			str += numbers[i];

			if (includeSeparator && i < numbers.length - 1) {
				str += separator;
			}
		}

		return str;
	}
}
