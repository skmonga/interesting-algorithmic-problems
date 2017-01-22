package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VanyaAndArray {

		 public static void main(String [] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		 String[] arr = br.readLine().split(" ");
		  int N = Integer.parseInt(arr[0]);
		  int K = Integer.parseInt(arr[1]);
		  int [] A = new int[N];
		  arr = br.readLine().split(" ");
		  for(int i = 0; i < N; i++)
			  A[i] = Integer.parseInt(arr[i]);
		  
		  int[] smaller = new int[N];
		  smaller[N-1] = 0;
		  for(int i = 0; i < N-1; i++) {
			  int count_small = 0;
			  for(int j = i+1; j < N; j++) {
				  if(A[i] < A[j])
					  count_small += 1;
			  }
			  smaller[i] = count_small;
		  }
		  
		  int num = 0;
		  for(int i = 0; i < N-1; i++) {
			  for(int j = i+1; j < N; j++) {
				  if(smaller[i] + smaller[j] >= K)
					  num++;
			  }
		  }
		  System.out.println(num);
		   
		 }
		  
}  

