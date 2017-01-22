package code.practice;

/**
 * In a sorted array every number is present twice, only one number is present
 * one time. You have to find the number occurring once. Expected complexity :
 * O(log N) 
 * Ex : 101, 101, 200, 200, 301, 450, 450 
 * Output : 301
 *
 */
public class ElementOccuringOnce {
	
	private static int findSingleOccuringElementUtil(int[] arr, int lo, int hi, int n) {
		if (lo > hi)
			return -1;
		else if (lo == hi)
			return lo;
		int mid = lo + (hi - lo)/2;
		if(arr[mid] == arr[mid-1]) {
			//left part has mid-lo+1 elements,check if they are odd or even
			if((mid - lo) % 2 == 0) {
				return findSingleOccuringElementUtil(arr, lo,mid-2, n);
			} else {
				//left half has even elements ,so find in right half
				return findSingleOccuringElementUtil(arr, mid+1, hi, n);
			}
		} else if(arr[mid] == arr[mid+1]) {
			if((mid - lo) % 2 == 0) {
				return findSingleOccuringElementUtil(arr, mid+2, hi, n);
			} else {
				return findSingleOccuringElementUtil(arr, lo, mid-1, n);
			}
		} else {
			//arr[mid] is not equal to any of elements on its left or right.
			//so this element occurs once.
			return mid;
		}
	}

	
	private static void findSingleOccuringElement(int[] arr, int n) {
		int index = findSingleOccuringElementUtil(arr,0,n-1,n);
		if(n % 2 == 0)
			System.out.println("Incorrect input provided");
		if(index != -1)
			System.out.println("The desired element is : "+arr[index]);
	}

	public static void main(String[] args) {
		int arr[] = {1,101, 101, 200, 200, 301, 301,403,403,450, 450,451,451};
		int n = arr.length;
		
		findSingleOccuringElement(arr,n);
	}
}
