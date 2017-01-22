package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Refer to : https://www.hackerearth.com/altimetrik-java-hiring-challenge/problems/
 *
 */
public class MillyPens {

//	private static boolean[]  isPossible = new boolean[10001];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		int N,P,M;
		String[] arr1,arr2;
		boolean[] included;
		for(int i = 1; i <= T; i++) {
			arr1 = br.readLine().split(" ");
			N = Integer.parseInt(arr1[0]);
			P = Integer.parseInt(arr1[1]);
			M = Integer.parseInt(arr1[2]);
			included = new boolean[N];
			arr1 = br.readLine().split(" ");
			arr2 = br.readLine().split(" ");
			if(N > P+M) {
				System.out.println("They can't");
				continue;
			}
			if(possibleToSelect(arr1,arr2,included,N,P,M)) {
				System.out.println("They can");
			} else {
				System.out.println("They can't");
			}
		}
	}

private static boolean possibleToSelect(String[] arr1, String[] arr2, boolean[] included, int n, int p, int m) {
	int i = 0,j = 0;
	int elem,count_unique = 0;
	while(i < p || j < m) {
		if(i < p) {
			elem = Integer.parseInt(arr1[i]);
			if(elem <= n && !included[elem-1]) {
				included[elem-1] = true;
				count_unique++;
			}
			i++;
		}
		if(j < m) {
			elem = Integer.parseInt(arr2[j]);
			if(elem <= n && !included[elem-1]) {
				included[elem-1] = true;
				count_unique++;
			}
			j++;
		}
	}
	return (count_unique == n);
}
}
