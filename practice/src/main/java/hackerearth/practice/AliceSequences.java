package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AliceSequences {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for(int i = 1; i <= T; i++) {
			int N = Integer.parseInt(br.readLine().trim());
			System.out.println(numSequences(N));
		}
	}

	private static long numSequences(int n) {
		long result1 = 1,result2 = 1;
		int constant = 1000000007;
		for(int i = 1; i <= n; i++) {
			result1 = (result1 * 3)%constant;
			result2 = (result2 * 2)%constant;
		}
		return (result1 - (3*result2)%constant + 3 + constant)%constant;
	}
}
