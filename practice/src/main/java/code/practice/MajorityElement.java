package code.practice;

/**
 * This is little different from actual majority element but idea is the same.
 * Refer to :http://www.geeksforgeeks.org/majority-element/
 *
 * Given an array of 2n elements of which n elements are same and the remaining
 * n elements are all different. Write a C program to find out the value which
 * is present n times in the array. There is no restriction on the elements in
 * the array. They are random (In particular they not sequential).
 */
public class MajorityElement {

	private static int repeatingElementUtil(int[] arr, int len) {
		int rep_index = 0,count = 1,prev_rep_index = -1;
		for(int i = 1; i < len; i++) {
			if(arr[i] == arr[rep_index]) {
				//this element repeats so this must be the repeating element.
				return arr[i];
			} else {
				if(prev_rep_index != -1 && arr[i] == arr[prev_rep_index])
					return arr[i];    //this is for the boundary condition like {1,2,1,3} or {2,1,3,1}
				prev_rep_index = rep_index;
				rep_index = i;
				count = 1;
			}
		}
		return -1;
	}
	
	private static int printRepeatingElement(int[] arr, int len) {
		 return repeatingElementUtil(arr,len);
	}

	public static void main(String[] args) {
		int[] arr = {1,2,3,1,1,4,1,5};
		int len = arr.length;
		System.out.println(printRepeatingElement(arr,len));
	}
	
	
}
