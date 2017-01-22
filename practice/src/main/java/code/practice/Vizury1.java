package code.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Vizury1 {
	
	static void populateNewArray(int[] new_array,int [] arr,int s_index,int e_index) {
		for(int i = 0; i < new_array.length; i++) {
			if(i % 2 == 0) {
				new_array[i] = arr[s_index++];
			} else {
				new_array[i] = arr[e_index--];
			}
		}
	}
	
	static int calculateMaxDifference(int[] arr,int size) {
		int max_diff = 0,i;
		for(i = 1; i < size-1; i +=2) {
			max_diff += Math.abs(arr[i] - arr[i-1]) + Math.abs(arr[i] - arr[i+1]);
		}
		if(i == size-1) {
			max_diff += Math.abs(arr[i] - arr[i-1]);
		}
		return max_diff;
	}
	
	static int findMaximumInterestDifference(int [] arr,int N,int M) {
		Arrays.sort(arr);
		//after sorting create a new array of size M and place the elements in there
		int[] new_array = new int[M];
		populateNewArray(new_array,arr,0,N-1);
		int max_diff = calculateMaxDifference(new_array,M);
		int other_max = Integer.MIN_VALUE;
		if(M % 2 == 1) {
			//we can pick one more max and one less min
			//reverse the original sorted array
			int i = 0,j = N-1,temp;
			while(i < j) {
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
			populateNewArray(new_array,arr,0,N-1);
			other_max = calculateMaxDifference(new_array,M);
		}
		
		return (max_diff > other_max)?max_diff:other_max;
	}
	
    public static void main(String args[] ) throws Exception {
       
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int T = Integer.parseInt(line);
        //T is the number of test cases
        int N,M;
        String[] size;
        int[] interest;
        while((line=br.readLine()) != null) {
        	size = line.split(" ");
        	N = Integer.parseInt(size[0]);
        	M = Integer.parseInt(size[1]);
        	line = br.readLine();
        	size = line.split(" ");
        	interest = new int[N];
        	for(int i = 0; i < interest.length; i++) {
        		interest[i] = Integer.parseInt(size[i]);
        	}
        	System.out.println(findMaximumInterestDifference(interest,N,M));
        }
      
   }


}
