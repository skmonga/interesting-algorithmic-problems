package code.practice;

/**
 * Refer to :
 * http://www.geeksforgeeks.org/minimum-time-to-finish-tasks-without-skipping-
 * two-consecutive/
 * 
 * Given time taken by n tasks. Find the minimum time needed to finish the tasks
 * such that skipping of tasks is allowed, but can not skip two consecutive
 * tasks.
 *
 */
public class SkipTask {

	private static int minTimeWithSkipAllowed(int[] arr) {
		if(arr.length <= 1)
			return 0;
		int min_incl = arr[0],min_excl = 0,temp;
		for(int i = 1; i < arr.length; i++) {
			temp = min_excl;
			min_excl = min_incl;
			min_incl = ((min_incl > temp)?temp:min_incl) + arr[i];
		}
		return (min_incl < min_excl)?min_incl:min_excl;
	}
	
	public static void main(String[] args) {
		int[] arr = {10, 15,4};
		System.out.println(minTimeWithSkipAllowed(arr));
	}

}
