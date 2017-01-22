package code.practice;

/**
 * Refer to : http://www.geeksforgeeks.org/rearrange-array-in-alternating-positive-negative-items-with-o1-extra-space-set-2/
 * 
 * Rearrange array in alternating positive & negative items with O(1) extra space.
 * Given an array of positive and negative numbers, arrange them
 * in an alternate fashion such that every positive number is followed by
 * negative and vice-versa. Order of elements in output doesn’t matter. Extra
 * positive or negative elements should be moved to end.
 * 
 * Examples
 * 
 * Input : arr[] = {-2, 3, 4, -1}
 * Output : arr[] = {-2, 3, -1, 4} OR {-1, 3, -2,4} OR ..
 * 
 * Input : arr[] = {-2, 3, 1}
 * Output : arr[] = {-2, 3, 1} OR {-2, 1, 3}
 * 
 * Input : arr[] = {-5, 3, 4, 5, -6, -2, 8, 9, -1, -4}
 * Output : arr[] = {-5, 3,-2, 5, -6, 4, -4, 9, -1, 8} OR..
 *
 */
public class AlternatePositiveNegative {

	private static void swap(int[] arr, int change_pos, int i) {
		int temp = arr[change_pos];
		arr[change_pos] = arr[i];
		arr[i] = temp;
	}
		
	
	private static void rearrangeAlternatePostiveNegative(int[] arr, int length) {
		//these will check whether positive is needed or negative
		boolean pos,neg;
		if(arr[0] > 0) {
			//negative is needed
			neg = true; pos = false;
		} else {
			pos = true; neg = false;
		}
		int i = 1,change_pos = -1;
		while(i < arr.length) {
			if(arr[i] > 0) {
				if(pos) {
					if(change_pos == -1) {
						//change_pos = -1 means no swaps required till this stage
						//so next we need a negative
						neg = true;
						pos = false;
					} else {
						swap(arr,change_pos,i);
						if(i - change_pos > 1) {
							change_pos += 2;
						} else {
							change_pos = -1;
						}
					}
				} else {
					//current element is positive but we need a negative
					if(change_pos == -1)
						change_pos = i;
				}
			} else {
				if(neg) {
					if(change_pos == -1) {
						neg = false;
						pos = true;
					} else {
						swap(arr,change_pos,i);
						if(i - change_pos > 1) {
							change_pos += 2;
						} else {
							change_pos = -1;
						}
					}
				} else {
					//current element is negative but we need a positive
					if(change_pos == -1)
						change_pos = i;
				}
			}
			i++;
		}
		
	}
	

	private static void printArray(int[] arr) {
		for(int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + "  ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		int arr[] = {-2, 3, 1};
		printArray(arr);
		rearrangeAlternatePostiveNegative(arr,arr.length);
		printArray(arr);
	}

	
}
