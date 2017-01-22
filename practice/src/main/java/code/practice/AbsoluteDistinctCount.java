package code.practice;

/**
 * Refer to :http://www.geeksforgeeks.org/absolute-distinct-count-array-sorted-absolute-values
 * Given a sorted array of
 * integers, return the number of distinct absolute values among the elements of
 * the array. The input can contain duplicates values.
 * 
 * Input: [-3, -2, 0, 3, 4, 5] Output: 5 There are 5 distinct absolute values
 * among the elements of this array, i.e. 0, 2, 3, 4 and 5)
 * 
 * Input: [-1, -1, -1, -1, 0, 1, 1, 1, 1] Output: 2
 * 
 * Input: [-1, -1, -1, -1, 0] Output: 2
 * 
 * Input: [0, 0, 0] Output: 1 
 * The solution should do only one scan of the input
 * array and should not use any extra space. i.e. expected time complexity is
 * O(n) and auxiliary space is O(1).
 *
 */
public class AbsoluteDistinctCount {

	private static int countDistinctAbsValues(int[] arr, int length) {
		int count_dis = 0;
		int left = 0,right = length - 1;
		if(left > right)
			return -1;
		if(left == right)
			return 1;
		while(left < right) {
			while(left < right && arr[left] == arr[left+1])
				left++;
//			count_dis += 1;
			if(left == right)
				return 1+count_dis;
			while(left < right && arr[right] == arr[right-1])
				right--;
//			count_dis += 1;
			if(left == right)
				return 1+count_dis;
			//now duplicates on both sides are considered 
			//now check for absolute values on leftmost and rightmost elements
			if(left < right) {
				count_dis += 1;
				if(Math.abs(arr[left]) == Math.abs(arr[right])) {
					left++;
					right--;
				} else if(Math.abs(arr[left]) > Math.abs(arr[right])) {
					left++;
				} else {
					right--;
				}
			}
		}
		
		//now we are out of while loop
		//two cases can be there 
		//1 - left > right ,so no element need to be considered
		//2 - left == child ,this element remains so add 1 for it.
		return count_dis + ((left == right)?1:0);
	}
	
	public static void main(String[] args) {
		int arr[] = {-3, -2, 0, 3, 4, 5};
		System.out.println(countDistinctAbsValues(arr,arr.length));
	}

	
}
