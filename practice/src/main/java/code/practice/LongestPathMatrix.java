package code.practice;


/**
 * @author skm
 * Reference : http://www.geeksforgeeks.org/find-the-longest-path-in-a-matrix-with-given-constraints/
 *
 */
public class LongestPathMatrix {

	private static void findNextIncreasing(int[][] arr, int[][] dp, int n) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				checkIfNextIncreasing(arr[i][j],i,j,arr,n,dp);
			}
		}
	}

	private static void checkIfNextIncreasing(int elem, int row, int col,int[][] arr,int n,int[][] dp) {
		int up = (row-1 >= 0) ?arr[row-1][col]:-1;
		int left = (col-1 >= 0)?arr[row][col-1]:-1;
		int down = (row + 1 < n)?arr[row+1][col]:-1;
		int right = (col+1 < n)?arr[row][col+1]:-1;
		if(up == elem+1) {
			dp[row][col] = n*(row-1) + col;
			return;
		} else if(left == elem+1) {
			dp[row][col] = n*(row) + col - 1;
			return;
		} else if(down == elem+1) {
			dp[row][col] = n*(row+1) + col;
			return;
		} else if(right == elem+1) {
			dp[row][col] = n*(row) + col + 1;
			return;
		}
	}
	
	private static int calculateMaxLength(int[][] dp, int i, int j,int n) {
		if(dp[i][j] == -1) {        //this means there is no consecutive increasing element from here
			return 1;
		}
		int next_ind = dp[i][j];
		int next_row = (next_ind)/n;
		int next_col = (next_ind)%n;
		return 1+calculateMaxLength(dp, next_row, next_col, n);
	}
	
	private static void printPath(int[][] arr, int[][] dp, int start_row, int start_col,int n) {
		while(true) {
			System.out.println(arr[start_row][start_col]);
			int next_ind = dp[start_row][start_col];
			if(next_ind == -1)
				return;
			start_row = (next_ind)/n;
			start_col = (next_ind)%n;
		}
	}

	public static void main(String[] args) {
		int[][] arr = { { 1, 2, 9 }, { 5, 3, 8 }, { 4, 6, 7 } };
		int n = 3;
		int[][] dp = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				dp[i][j] = -1; // initialize all to -1

		findNextIncreasing(arr, dp, n);
        // now dp is populated ,now calculate the max length of path and print the path also
		int start_row = -1,start_col = -1,max_length = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				int max_length_from_here = calculateMaxLength(dp,i,j,n);
				if(max_length_from_here > max_length) {
					max_length = max_length_from_here;
					start_row = i;
					start_col = j;
				}
			}
		}
		
		System.out.println("The length of longest path is: "+max_length);
		printPath(arr,dp,start_row,start_col,n);
		
	}

}
