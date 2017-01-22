package code.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TWorks2 {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int N = Integer.parseInt(line), largest_prime = 0;
		largest_prime = largestPrime(N);
		if(largest_prime == -1) {
			System.out.println(br.readLine());
			return;
		}
			
		int[] points = new int[N];
		line = br.readLine();
		String[] arr = line.split(" ");
		for (int i = 0; i < N; i++)
			points[i] = Integer.parseInt(arr[i]);

		int[] part_sums = new int[N];
		part_sums[0] = points[0];
		for(int i = 1; i < N; i++)
			part_sums[i] = points[i] + part_sums[i-1];
		
		int[][] sum_startindex_length = new int[N][N+1];
		//first index is the start point and second index is the length of segment
		
		for(int len = 1; len <= N; len++) {
			if(isPrime(len)) {
			for(int i = 0; i <= N-len; i++) {
				sum_startindex_length[i][len] = part_sums[i+len-1] - ((i-1 >= 0)?part_sums[i-1]:0);
			}
			}
		}
		
		
		
	}

	private static int largestPrime(int N) {
		if (N == 2) {
			return N;
		} else {
			for (int i = N; i >= 3; i--) {
				if (isPrime(i)) {
					return i;
				}
			}
		}
		return -1;
	}

	private static boolean isPrime(int n) {
		if(n == 2)
			return true;
		if (n % 2 == 0)
			return false;
		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

}
