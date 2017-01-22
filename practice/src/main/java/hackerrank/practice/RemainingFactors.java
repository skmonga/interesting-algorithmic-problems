package hackerrank.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Refer to :
 * https://www.hackerrank.com/contests/morgan-stanley-2016/challenges/remaining-
 * factors
 *
 */
public class RemainingFactors {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		Map<Integer, Integer> primeFactors = getPrimeFactors(N);
		primeFactors = new TreeMap<Integer, Integer>(primeFactors);
		System.out.println(calculateRemainingFactors(N,primeFactors));
	}

	private static int calculateRemainingFactors(int n, Map<Integer, Integer> primeFactors) {
		int count = 0;
		Set<Integer> keySet = primeFactors.keySet();
		return count;
	}

	private static Map<Integer, Integer> getPrimeFactors(int n) {
		Map<Integer, Integer> primeFactors = new HashMap<Integer, Integer>();
		int count = 0;
		while (n % 2 == 0) {
			count++;
			n = n / 2;
		}
		if (count != 0) {
			primeFactors.put(2, count);
		}
		for (int i = 3; i <= Math.sqrt(n); i++) {
			count = 0;
			while (n % i == 0) {
				count++;
				n = n / i;
			}
			if (count != 0) {
				primeFactors.put(i, count);
			}
		}
		
		if(n > 1)
			primeFactors.put(n, 1);
		return primeFactors;
	}
}
