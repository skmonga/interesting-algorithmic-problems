package hackerrank.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Refer to : https://www.hackerrank.com/contests/womens-codesprint/challenges/mars-and-the-binary-search-tree
 * Check the editorial for intelligent solution
 *
 */
public class VeronicaMarsBST {

	static int constant = 1000000007;
	
	static class Pair {
		int small;
		int large;
		
		public Pair(int s,int l) {
			this.small = s;
			this.large = l;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + large;
			result = prime * result + small;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (large != other.large)
				return false;
			if (small != other.small)
				return false;
			return true;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] indexes = new int[N];
		String[] arr = br.readLine().split(" ");
		
		/*
		 * to do:
		 * create range map : Map<Pair,Integer>
		 * initially available is -inf,inf with index as 1
		 * then on element insertion, pick the proper range for element
		 * and break the given range into two small ranges
		 * insert the elements as they come into a TreeSet so that
		 * we can know the range in which the element lies for ex
		 * if element to be inserted is x, then its range is (x1,x2)
		 * where x1 is the largest element smaller than x in BST and
		 * x2 is the smallest element larger than x in BST.
		 */
		
		Map<Pair, Integer> rangeToIndex = new HashMap<>();
		rangeToIndex.put(new Pair(Integer.MIN_VALUE, Integer.MAX_VALUE), 1);
		
		TreeSet<Integer> included = new TreeSet<Integer>();
		for(int i = 0; i < N; i++) {
			int elem = Integer.parseInt(arr[i]);
			Pair cur_range = findRangeForCurrentElement(elem,included,rangeToIndex);
			indexes[i] = rangeToIndex.get(cur_range);
			createNewRanges(elem,cur_range,rangeToIndex);
			included.add(elem);
		}
		
		for(int i = 0; i < N-1; i++)
			System.out.print(indexes[i] + " ");
		System.out.print(indexes[N-1]);
		
	}

	private static void createNewRanges(int elem, Pair cur_range, Map<Pair, Integer> rangeToIndex) {
		//now make 2 new range using cur_range 
		//and remove the cur_range
		int index = rangeToIndex.get(cur_range);
		rangeToIndex.put(new Pair(cur_range.small, elem), (2*index)%constant);
		rangeToIndex.put(new Pair(elem, cur_range.large), ((2*index)%constant+1)%constant);
		rangeToIndex.remove(cur_range);
	}

	private static Pair findRangeForCurrentElement(int elem, TreeSet<Integer> included, Map<Pair, Integer> rangeToIndex) {
		Integer small = included.lower(elem);
		if(small == null)
			small = Integer.MIN_VALUE;
		Integer large = included.higher(elem);
		if(large == null)
			large = Integer.MAX_VALUE;
		
		return new Pair(small,large);
	}
	
}
