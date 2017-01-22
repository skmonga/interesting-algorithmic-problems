package code.practice;

/**
 * Given an array of integers where each element represents the max number of
 * steps that can be made forward from that element. Write a function to return
 * the minimum number of jumps to reach the end of the array (starting from the
 * first element). If an element is 0, then cannot move through that element.
 * 
 * Example:
 * 
 * Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9} Output: 3 (1-> 3 -> 8 ->9)
 *
 */
public class JumpsToEnd {
	
	private static int minStepsToEnd(int[] arr) {
		int n = arr.length;
		int dp[] = new int[n];
		dp[n-1] = 0;
		for(int i = n - 2; i >= 0; i--) {
			if(arr[i] == 0) {
				dp[i] = Integer.MAX_VALUE;
			} else {
				int min = Integer.MAX_VALUE;
				for(int j = i+1; j <= i+arr[i] && j < n; j++) {
					if(arr[j] == Integer.MAX_VALUE)
						continue;
					if(dp[j] + 1 < min)
						min = dp[j] + 1;
				}
				dp[i] = min;
			}
		}
		return dp[0];
	}
	
	public static void main(String[] args) {
		int arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
		System.out.println(minStepsToEnd(arr));
	}

	

}
