package code.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TWorks1 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int N = Integer.parseInt(line);
		String str[] = br.readLine().split(" ");
		int arr[] = new int[N];
		for(int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(str[i]);
		
		int fill[] = new int[N];
		int num_inc_subseq = 0;
		for(int i = 0; i < N; i++) {
			if(arr[i] == 1)
				num_inc_subseq++;
		}
		
		int max_size = 101;
		int[][] gcd = new int[max_size][max_size];
		buildGCDTable(gcd,max_size);
		
		for(int len = 2; len <= N; len++) {
			//choose start point
			for(int i = 0; i <= N-len; i++) {
				fill[0] = arr[i];
				num_inc_subseq += populateFill(fill,arr,1,i+1,len,N,gcd);
			}
		}
		
	}

	private static void buildGCDTable(int[][] gcd,int size) {
		for(int i = 1; i < size; i++) {
			for(int j = 1; j < size; j++) {
				if( i > j)
					gcd[i][j] = gcd[j][i];
				else
					gcd[i][j] = gcd(i, j);
			}		
		}
	}

	private static int populateFill(int[] fill, int[] arr, int i, int pos_arr,int len,int size, int[][] gcd) {
		if(i == len)
			return checkIfGCDOne(fill,len,gcd);
		if(pos_arr >= size || (len-i) > (size - pos_arr))
		return 0;
		
		if(fill[i-1] >= arr[pos_arr])
			return populateFill(fill, arr, i, pos_arr+1, len, size,gcd);
		
		fill[i] = arr[pos_arr];
		int fill_with_current = populateFill(fill, arr, i+1, pos_arr+1, len, size,gcd);
		int fill_exclude_current = populateFill(fill, arr, i, pos_arr+1, len, size,gcd);
		return fill_with_current + fill_exclude_current;
	}
	
	private static int gcd(int a,int b) {
		if(b == 0)
			return a;
		return gcd(b,a%b);
	}

	private static int checkIfGCDOne(int[] fill, int len, int[][] gcd) {
		int result = fill[0];
		for(int i = 1; i < len; i++){
		    result = gcd[result][fill[i]];
		}
		return (result == 1)?1:0;
	}
}
