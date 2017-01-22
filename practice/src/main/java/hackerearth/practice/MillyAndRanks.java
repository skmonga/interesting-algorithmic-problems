package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Refer to :
 * https://www.hackerearth.com/rang-de-java-hiring-challenge/problems/
 * c909d40c1a8e41c6acb0de97c61f84b8/
 * 
 * @author skm
 *
 */
public class MillyAndRanks {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] ranks = new int[100001];
		int N = 0;
		String[] arr = null;
		for (int i = 1; i <= T; i++) {
			N = Integer.parseInt(br.readLine());
			arr = br.readLine().split(" ");
			for (int j = 0; j < N; j++)
				ranks[j] = Integer.parseInt(arr[j]);
			int thinkTime = findMinimumTime(ranks, N);
			System.out.println(thinkTime);
		}
	}

	private static int findMinimumTime(int[] ranks, int n) {
		Arrays.sort(ranks, 0, n);
		int thinkTime = 0;
		int minRank = ranks[0];
		for (int i = 0; i < n; i++) {
			if (ranks[i] != minRank) {
				if (ranks[i] < minRank) {
					thinkTime += (minRank - ranks[i]);
					ranks[i] = minRank;
				}
			}
			minRank = ranks[i] + 1;
		}
		return thinkTime;
	}
}
