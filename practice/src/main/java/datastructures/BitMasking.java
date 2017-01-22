package datastructures;

/**
 * This is problem related to BitMasking. To understand bitmasking, refer to :
 * https://www.quora.com/What-is-bitmasking-What-kind-of-problems-can-be-solved-
 * using-it 
 * http://codeforces.com/blog/entry/18169
 *
 * This problem finds the number of subsets in a set S of integers which have
 * sum k
 */
public class BitMasking {

	private static int numberOfSubsetsWithSum(int[] arr, int k) {
		//treat every element in the set as a bit
		//first element is rightmost bit
		//second element is second from right bit
		//if there are n elements in set , then a subset can be 
		//represented as a bit vector having n bits.
		//each set bit in this bit vector indicates that element corresponding to that 
		//bit is included in the subset
		
		int count = 0;   //count of subsets with sum k
		int mask = 0;
		int n = arr.length;
		int subset_sum;
		while(mask < (1 << n)) {
			subset_sum = 0;
			for(int i = 0; i < n; i++) {
				if((mask & (1 << i)) != 0)
					subset_sum += arr[i];
			}
			if(subset_sum == k) {
				count++;
				printSubset(arr,mask);
				System.out.println();
			}
			mask++;
		}
		
		return count;
	}
	
	private static void printSubset(int[] arr, int mask) {
		for(int i = 0; i < arr.length; i++) {
			if((mask & (1 << i)) != 0)
				System.out.print(arr[i] + " ");
		}
	}

	public static void main(String[] args) {
		int[] arr = {1,3,2,6,7,9,2,4,10};
		int k = 10;
		System.out.println(numberOfSubsetsWithSum(arr,k));
	}

}
