package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MonkAndKOperations {

	static int[] num = new int[100001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int N, K;
		String[] arr = null;
		for (int i = 1; i <= T; i++) {
			arr = br.readLine().split(" ");
			N = Integer.parseInt(arr[0]);
			K = Integer.parseInt(arr[1]);
			arr = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				num[j] = Integer.parseInt(arr[j]);
			}
			if(K != 0) {
			System.out.println(maxPowerArray(N, K));
			} else {
				System.out.println(num[0] * num[N-1]);
			}
		}
	}

	private static long maxPowerArray(int n, int k) {
		long maxProduct = 0;
		long curProduct = 0;
		for (int i = 0; i <= k; i++) {
			int rearIndex = n - 1 - k + i;
		    curProduct = num[i] * num[rearIndex];
			if (curProduct > maxProduct)
				maxProduct = curProduct;
		}
		return maxProduct;
	}
}
