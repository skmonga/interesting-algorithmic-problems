package code.practice;

/**
 * Backtracking | Set 4 (Subset Sum)
 * Refer to : http://www.geeksforgeeks.org/backttracking-set-4-subset-sum/
 * Subset sum problem is to find subset of
 * elements that are selected from a given set whose sum adds up to a given
 * number K. We are considering the set contains non-negative values. It is
 * assumed that the input set is unique (no duplicates are presented).
 *
 */
public class SubsetSum {
	
	//arr contains the original array
	//length is the length of arr
	//start_pos is the index of which element will be starting element for current recursion
	//subset_sum the required sum 
	//temp will contain the elements as we place them in it
	//pos is the position in temp array where we will be placing the element.
	private static void printSubsetWithGivenSum(int[] arr, int length, int start_pos,
			int subset_sum,int[] temp,int pos) {
		
		//reached sum
		if(subset_sum == 0) {
			for(int i = 0; i < pos; i++)
				System.out.print(temp[i]+"  ");
			System.out.println();
			return;
		}
		
		for(int i = start_pos; i < length; i++) {
			if(arr[i] <= subset_sum) {
				temp[pos] = arr[i];
				printSubsetWithGivenSum(arr, length, i+1,
						subset_sum - arr[i], temp, pos+1);
			}
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {15, 22, 14, 26, 32, 9, 16, 8};
		int subset_sum = 53;
		int[] temp = new int[arr.length];
		printSubsetWithGivenSum(arr,arr.length,0,subset_sum,temp,0);
	}

}
