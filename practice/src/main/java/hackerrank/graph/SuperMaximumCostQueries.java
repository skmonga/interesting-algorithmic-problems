package hackerrank.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class SuperMaximumCostQueries {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr = br.readLine().split(" ");
		int N = Integer.parseInt(arr[0]);
		int Q = Integer.parseInt(arr[1]);
		int[] weights = new int[N];
		weights[0] = 0;
		for(int i = 1; i < N; i++) {
			arr = br.readLine().split(" ");
			weights[i] = Integer.parseInt(arr[2]);
		}
		Arrays.sort(weights);
		int[] numpaths = new int[N];
		fillNumPaths(numpaths,N);
		int left,right;
		for(int i = 1; i <= Q; i++) {
			arr = br.readLine().split(" ");
			left = Integer.parseInt(arr[0]);
			right = Integer.parseInt(arr[1]);
			int lessThanLeft = binarySearch(weights,0,N-1,left);
			int moreThanRight = binarySearch(weights,0,N-1,right);
			int result = numpaths[moreThanRight];
			if(left != right) {
				result -= (lessThanLeft-1 >= 0)?numpaths[lessThanLeft-1]:0;
			}
			
			System.out.println(result);
		}
	}

	private static int binarySearch(int[] weights, int l, int r, int key) {
		int m;
		while(r -l > 1) {
			m = l + (r - l)/2;
			if(weights[m] <= key)
				l = m;
			else
				r = m;
		}
		if(weights[l] > key)
			return l-1;
		else if(weights[r] < key)
			return r;
		else {
			if(weights[r] > key)
				return l;
			return r;
		}
	}
	
	private static void fillNumPaths(int[] numpaths, int n) {
		numpaths[0] = 0;
		int val = 1;
		for(int i = 1; i < n; i++) {
			numpaths[i] = numpaths[i-1] + val;
			val += 1;
		}
	}
}
