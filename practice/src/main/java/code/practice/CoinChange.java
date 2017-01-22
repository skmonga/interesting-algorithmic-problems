package code.practice;

/**
 * Refer to :
 * http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
 *
 */
public class CoinChange {

	private static int findWaysToReachSum(int[] coins, int sum) {
		int[][] numWays = new int[coins.length][sum + 1];
		for (int i = 0; i < coins.length; i++) {
			numWays[i][0] = 1;
		}
		// now build for all sum from 1 to sum
		for (int s = 1; s <= sum; s++) {
			for(int i = 0; i < coins.length; i++) {
				//for every element,calculate ways with including this element
				//and without excluding this element
				//while including ,we proceed with rest sum and staying on same index
				//while excluding ,we proceed with same sum and moving one index ahead
				
				//including
				if(s - coins[i] >= 0)
					numWays[i][s] += numWays[i][s-coins[i]];
				
				//excluding
				numWays[i][s] += (i-1 >= 0)?numWays[i-1][s]:0;
			}
		}

		return numWays[coins.length-1][sum];
	}

	public static void main(String[] args) {
		int N = 10;
		int[] coins = { 2, 5, 3 ,6};
		System.out.println(findWaysToReachSum(coins, N));
	}

}
