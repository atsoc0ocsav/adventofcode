package utils;

import java.util.Random;

/*
 * From: https://stackoverflow.com/a/2484831 Based on:
 * https://www.topcoder.com/community/data-science/data-science-tutorials/
 * primality-testing-non-deterministic-algorithms/
 * 
 * Miller-Rabin primality test, iteration signifies the accuracy of the test
 */
public class PrimeTools {
	/*
	 * From: https://stackoverflow.com/a/2473188 Based on Bertrand's postulate
	 * (https://en.wikipedia.org/wiki/Bertrand%27s_postulate): Bertrand's postulate
	 * (actually a theorem) states that if n > 3 is an integer, then there always
	 * exists at least one prime number p with n < p < 2n - 2. A weaker but more
	 * elegant formulation is: for every n > 1 there is always at least one prime p
	 * such that n < p < 2n.
	 */
	public static int getNextPrime(int n) {
		for (int i = n; i < 2 * n; ++i) {
			if (Miller(i, 50)) { // 18-20 iterations are enough for most of the applications.
				return i;
			}
		}

		return -1;
	}

	/*
	 * Miller-Rabin primality test, iteration signifies the accuracy of the test
	 */
	private static boolean Miller(long p, int iteration) {
		if (p < 2) {
			return false;
		}
		if (p != 2 && p % 2 == 0) {
			return false;
		}

		long s = p - 1;
		while (s % 2 == 0) {
			s /= (long)2;
		}

		Random random = new Random();
		for (int i = 0; i < iteration; i++) {
			long a = (long) (random.nextInt(((int)p - 1) + 1)), temp = s;
			long mod = modulo(a, temp, p);
			while (temp != p - 1 && mod != 1 && mod != p - 1) {
				mod = mulmod(mod, mod, p);
				temp *= 2;
			}
			if (mod != p - 1 && temp % 2 == 0) {
				return false;
			}
		}
		return true;
	}

	/*
	 * This function calculates (ab)%c
	 */
	private static int modulo(long a, long b, long c) {
		long x = 1, y = a; // long long is taken to avoid overflow of intermediate results
		while (b > 0) {
			if (b % 2 == 1) {
				x = (x * y) % c;
			}
			y = (y * y) % c; // squaring the base
			b /= 2;
		}
		return (int) x % (int) c;
	}

	/*
	 * This function calculates (a*b)%c taking into account that a*b might overflow
	 */
	private static long mulmod(long a, long b, long c) {
		long x = 0, y = a % c;
		while (b > 0) {
			if (b % 2 == 1) {
				x = (x + y) % c;
			}
			y = (y * 2) % c;
			b /= 2;
		}
		return x % c;
	}
}
