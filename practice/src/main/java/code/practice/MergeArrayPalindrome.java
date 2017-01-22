package code.practice;

/**
 * Refer to::http://www.geeksforgeeks.org/find-minimum-number-of-merge-operations-to-make-an-array-palindrome/
 * Given an array of positive integers. We need to make the
 * given array a ‘Palindrome’. Only allowed operation on array is merge. Merging
 * two adjacent elements means replacing them with their sum. The task is to
 * find minimum number of merge operations required to make given array a
 * ‘Palindrome’.
 * 
 * To make an array a palindromic we can simply apply merging operations n-1
 * times where n is the size of array (Note a single element array is alway
 * palindrome similar to single character string). In that case, size of array
 * will be reduced to 1. But in this problem we are asked to do it in minimum
 * number of operations.
 * 
 * Example:
 * 
 * Input : arr[] = {15, 4, 15} Output : 0 Array is already a palindrome. So we
 * do not need any merge operation.
 * 
 * Input : arr[] = {1, 4, 5, 1} Output : 1 We can make given array palindrome
 * with minimum one merging (merging 4 and 5 to make 9)
 * 
 * Input : arr[] = {11, 14, 15, 99} Output : 3 We need to merge all elements to
 * make a palindrome. Expected time complexity is O(n).
 *
 * 
 * 
 */
public class MergeArrayPalindrome {
	
	private static int minOperationsForPalindrome(int[] arr) {
		int merge_count = 0;
		int i = 0,j = arr.length-1;
		int left_sum,right_sum;
		while(i < j) {
			if(arr[i] == arr[j]) {
				i++;
				j--;
				continue;
			}
			left_sum = arr[i]; right_sum = arr[j];
			while(left_sum != right_sum && i < j) {
				if(left_sum < right_sum) {
					left_sum += arr[++i];
				} else {
					right_sum += arr[--j];
				}
				merge_count++;
			}
			//now we are out of while loop
			if(left_sum != right_sum)  {
				//means i == j
				return merge_count;
			} else {
				//left_sum == right_sum (values of i and j have no effect right now)
				i++;
				j--;
			}
			
		}
		return merge_count;
	}
	
	public static void main(String[] args) {
		int[] arr = {11, 14, 15, 99};
		System.out.println(minOperationsForPalindrome(arr));
	}

}
