package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Refer to : https://www.hackerearth.com/viacom-java-hiring-challenge/problems/940ed6e8e22347dab60fce6c6f92c50e/
 *
 */
public class Albums {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int prices[] = new int[N];
		String[] input = br.readLine().split(" ");
		for(int i = 0; i < N; i++)
			prices[i] = Integer.parseInt(input[i]);
		int Q = Integer.parseInt(br.readLine());
		int L,R,K,count_div;
		int[] divisible = new int[N];
		for(int i = 1; i <= Q; i++) {
			input = br.readLine().split(" ");
			L = Integer.parseInt(input[0]);
			R = Integer.parseInt(input[1]);
			K = Integer.parseInt(input[2]);
			if(i == 1) {
			populateDivisible(prices,divisible,N,K);
			}
			if(L == 1) {
				count_div = divisible[R-1];
			} else {
				count_div = divisible[R-1] - divisible[L-2];
			}
			System.out.println(count_div);
		}
	}

	private static void populateDivisible(int[] prices, int[] divisible, int n, int k) {
		divisible[0] = (prices[0] % k == 0)?1:0;
		for(int i = 1; i < n; i++) {
			divisible[i] += divisible[i-1] + ((prices[i] % k == 0)?1:0);
		}
	}

	private static int findNumberAlbums(int[] prices, int l, int r, int k) {
		int num = 0;
		for(int i = l; i <= r; i++) {
			if(prices[i] % k == 0)
				num++;
		}
		return num;
	}
}
