package datastructures;

/**
 * This is also called Binary Indexed Tree To understand BIT, refer to :
 * http://www.geeksforgeeks.org/binary-indexed-tree-or-fenwick-tree-2/
 * https://www.hackerearth.com/practice/notes/binary-indexed-tree-or-fenwick-
 * tree/
 *
 *For an interesting problem using BIT ,check 
 *http://www.geeksforgeeks.org/count-inversions-array-set-3-using-bit/
 */
public class FenwickTree {

	int[] BITree;

	public FenwickTree(int n) {
		this.BITree = new int[n + 1];
	}

	public void updateBIT(int index, int n, int val) {
		// the index passed here is the index in the actual array
		// index in BITree[] is 1 more than the index in actual array
		index = index + 1;
		while (index <= n) {
			BITree[index] += val;
			index += (index & (-index));
		}
	}

	public int getSum(int index) {
		//call this method when BITree is properly created
		//i.e. each node of BITree[] stores the sum of a proper subarray
		index = index + 1;
		int sum = 0;
		while(index > 0) {
			sum += BITree[index];
			index -= (index & (-index));
		}
		return sum;
	}

	public static FenwickTree constructBIT(int[] arr, int n) {
		FenwickTree fTree = new FenwickTree(n);
		for (int i = 1; i <= n; i++)
			fTree.BITree[i] = 0;

		for (int i = 0; i < n; i++)
			fTree.updateBIT(i, n, arr[i]);

		return fTree;
	}

	public static void main(String[] args) {
		int freq[] = { 2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9 };
		FenwickTree fTree = constructBIT(freq, freq.length);
		//print sum for freq[0..5]
		System.out.println(fTree.getSum(5));
		freq[3] += 6;
		//value is updated in actual array ,update BIT and then check new sum
		fTree.updateBIT(3, freq.length, 6);
		System.out.println(fTree.getSum(5));
	}
}
