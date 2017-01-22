package hackerrank.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Refer to : https://www.hackerrank.com/contests/walmart-codesprint-algo/challenges/digit-minmax-scores
 *
 */
public class DigitMinMaxScores {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long a = Long.parseLong(br.readLine());
		long b = Long.parseLong(br.readLine());
		System.out.println(minMaxCount(a,b));
	}

	private static long minMaxCount(long a, long b) {
		int count = 0;
		for(Long i = a; i <= b; i++)
			count += countUtil(i.toString());
		return count;
	}

	private static long countUtil(String str) {
		int len = str.length();
		int count = 0;
		for (int i = 1; i < len - 1; i++) {
			if ((str.charAt(i) > str.charAt(i + 1) && str.charAt(i) > str.charAt(i - 1))
					|| (str.charAt(i) < str.charAt(i + 1) && str.charAt(i) < str.charAt(i - 1))) {
				count++;
			}
		}
		return count;
	}
}
