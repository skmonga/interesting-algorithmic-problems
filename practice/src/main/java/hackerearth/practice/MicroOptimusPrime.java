package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MicroOptimusPrime {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		long n;
		for(int i = 1; i <= T; i++) {
			n = Long.parseLong(br.readLine());
			System.out.println(findSmallestOptimalPrime(n));
		}
	}

	private static long findSmallestOptimalPrime(long n) {
		long smallest_op_prime = 1;
		long constant = 1000000007;
		for(int i = 1; i <= n; i++) {
			smallest_op_prime = (smallest_op_prime * 2)%constant;
		}
		return smallest_op_prime;
	}
	
}
