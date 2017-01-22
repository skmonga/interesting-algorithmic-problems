package code.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AmazonMillyChocolates {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] arr;
        int N = Integer.parseInt(line);
       
        int[] count = new int[N];
        arr = br.readLine().split(" ");
        for(int i = 0; i < N; i++)
        	count[i] = Integer.parseInt(arr[i]);
        
        //lets store start indexes in count only
        
        int[] start_index = new int[N];
        start_index[0] = 1;
        for(int i = 1; i < N; i++) {
        	start_index[i] = 1 + count[i-1];
        	count[i] += count[i-1];
        }
        
        int num_queries = Integer.parseInt(br.readLine());
        for(int i = 1; i <= num_queries; i++) {
        	line = br.readLine();
        	int num = Integer.parseInt(line);
        	System.out.println(1+binarySearch(start_index,num,0,N-1));
        }
	}

	
	private static int binarySearch(int[] start_index, int num,int l,int r) {
		if(l < r) {
			int mid = l + (r-l)/2;
			if(start_index[mid] > num)
				return binarySearch(start_index, num, l, mid-1);
			else {
				if(mid == l) {
					return (start_index[r] > num)?l:r;
				}
				else
				return binarySearch(start_index, num, mid, r);
			}
		} else 
			return l;
	}
}
