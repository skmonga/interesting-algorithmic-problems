package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class AliceQueries {

	public static class Pair {
		int count;
		
		public Pair() {
			this.count = 0;
		}
		
		public static enum Order implements Comparator<Pair> {
			ByCount() {
				public int compare(Pair p1, Pair p2) {
					if (p1.count > p2.count)
						return -1;
					else if(p1.count < p2.count)
						return 1;
					else
						return 0;
				}
			};
			
			public abstract int compare(Pair p1, Pair p2);
			
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input  = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int Q = Integer.parseInt(input[1]);
		Integer[] arr = new Integer[N];
		input  = br.readLine().split(" ");
		for(int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(input[i]);
		
		Pair[] pairs = new Pair[N];
		for(int i = 0; i < N; i++) {
			pairs[i] = new Pair();
		}
		
		int L,R;
		for(int i = 1; i <= Q; i++) {
			input  = br.readLine().split(" ");
			L = Integer.parseInt(input[0]);
			R = Integer.parseInt(input[1]);
			incrementCount(pairs,L-1,R-1);
		}
		
		//sort the pairs array using count in increasing order
		Arrays.sort(pairs, Pair.Order.ByCount);
		//sort the original array in increasing order
		Arrays.sort(arr,Collections.reverseOrder());
		System.out.println(findMaximumSum(pairs,arr,N));
		
	}

	private static long findMaximumSum(Pair[] pairs, Integer[] arr, int n) {
		long max_sum = 0;
		int idx = 0;
		while(idx < n && pairs[idx].count != 0) {
			max_sum += (pairs[idx].count * arr[idx]);
			idx++;
		}
		return max_sum;
	}

	private static void incrementCount(Pair[] pairs, int l, int r) {
		for(int i = l; i <= r; i++) {
			pairs[i].count += 1;
		}
	}
	
}
