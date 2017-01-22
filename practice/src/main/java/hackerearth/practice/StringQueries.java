package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class StringQueries {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr = br.readLine().split(" ");
		int N = Integer.parseInt(arr[0]);
		int Q = Integer.parseInt(arr[1]);
		String str = br.readLine();
		int[][] counts = new int[26][N];
		preprocessString(str,counts,N);
		int L = 0,R = 0;
		for(int i = 1; i <= Q; i++) {
			arr = br.readLine().split(" ");
			L = Integer.parseInt(arr[0]);
			R = Integer.parseInt(arr[1]);
			System.out.println(numDeletions(counts,L-1,R-1));
		}
	}

	private static int numDeletions(int[][] counts, int l, int r) {
		int num_del = 0;
		int[] range_counts = new int[26];
		for(int i = 0; i < 26; i++) {
			range_counts[i] = counts[i][r] - ((l > 0)?counts[i][l-1]:0);
		}
		Arrays.sort(range_counts);
		int j = 0;
		while(range_counts[j] == 0)
			j++;
		int min_occur = range_counts[j];
		for(int k = j+1; k < 26; k++)
			num_del += range_counts[k] - min_occur;
		return num_del;
	}

	private static void preprocessString(String str, int[][] counts,int N) {
		for(int i = 0; i < 26; i++)
			counts[i][0] = 0;
		
		counts[str.charAt(0)-'a'][0] = 1;
		for(int i = 0; i < 26; i++) {
			for(int j = 1; j < N; j++) {
				counts[i][j] = counts[i][j-1] + ((str.charAt(j) - 'a' == i)?1:0);
			}
		}
	}
}
