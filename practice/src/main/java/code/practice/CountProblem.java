package code.practice;

/**
 * Refer to:
 * http://www.geeksforgeeks.org/count-of-n-digit-numbers-whose-sum-of-digits-
 * equals-to-given-sum/ Given two integers ‘n’ and ‘sum’, find count of all n
 * digit numbers with sum of digits as ‘sum’. Leading 0’s are not counted as
 * digits. 1 <= n <= 100 and 1 <= sum <= 50000
 * 
 * Example:
 * 
 * Input: n = 2, sum = 2 Output: 2 Explanation: Numbers are 11 and 20
 * 
 * Input: n = 2, sum = 5 Output: 5 Explanation: Numbers are 14, 23, 32, 41 and
 * 50
 * 
 */
public class CountProblem {

	private static void countWithSumUtil(int n, int sum, int[][] arr) {
		for(int i = 1; i <= n; i++) {
			for(int j = 0; j <= sum; j++) {
				//for i and j means no.of digits is i and sum is j
				arr[i][j] = 0;           
				if(i > 1) {
					//we iterate for every value between 0 to 9 which this digit can take
					//total sum required is j,current value is k so j-k must be greater than 0
					for(int k = 0; k < 10 && k <= j; k++) {
						arr[i][j] += arr[i-1][j-k];
					}
				} else {
					arr[i][j] = (j >= 0 && j <= 9)?1:0;
				}
			}
		}
	}
	
	private static int countWithSum(int n, int sum) {
		if((sum == 0 || sum == 1) && n > 0)
			return 1;
		int arr[][] = new int[n+1][sum+1];
		countWithSumUtil(n,sum,arr);
		return arr[n][sum] - arr[n-1][sum];
	}

	public static void main(String[] args) {
		int sum = 6;
		int n = 3;
		System.out.println(countWithSum(n,sum));
	}

	
}
