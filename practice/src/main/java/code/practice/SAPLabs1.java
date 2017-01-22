package code.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Refer to :
 * https://www.hackerearth.com/sap-labs-java-hiring-challenge/problems/
 *
 */
public class SAPLabs1 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		int N;
		int[] num;
		String[] arr;
		for (int i = 1; i <= T; i++) {
			N = Integer.parseInt(br.readLine().trim());
			arr = br.readLine().split(" ");
			num = new int[N];
			for (int j = 0; j < N; j++)
				num[j] = Integer.parseInt(arr[j]);
			System.out.println(specialArrayCount(num, N));
		}
	}

	private static int specialArrayCount(int[] num, int n) {
		int count = n; // every single element is special
		int run = 0;
		for (int i = 0; i < n - 1; i++) {
			run = 0;
			while (num[i] == num[i + 1]) {
				run++;
				i++;
			}
			count = count + (run * (run + 1)) / 2;
		}
		return count;
	}
}
