package hackerrank.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Refer to : https://www.hackerrank.com/challenges/queries-with-fixed-length
 *
 */
public class QueriesFixedLength {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int q = Integer.parseInt(input[1]);
		int[] arr = new int[n];
		input = br.readLine().split(" ");
		for(int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(input[i]);
		for(int i = 0; i < q; i++) {
			int d = Integer.parseInt(br.readLine());
			System.out.println(findMinimumForFixedLength(arr,n,d));
		}
	}

	/*private static int findMinimumForFixedLength(final int[] arr, int n, int d) {
		PriorityQueue<Integer> pq1 = new PriorityQueue<Integer>(d, new Comparator<Integer>() {
			public int compare(Integer i1, Integer i2) {
				return (arr[i1] > arr[i2])?-1:1;
			}
		});
		PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>(n-d+1, new Comparator<Integer>() {
			public int compare(Integer i1, Integer i2) {
				return (arr[i1] < arr[i2])?-1:1;
			}
		});
		
		for(int i = 0; i < d; i++)
			pq1.add(i);
		
		int idx = 0;
		while (idx <= n - d) {
			pq2.add(pq1.peek());
			pq1.remove(idx);
			if (idx + d < n) {
				pq1.add(idx + d);
			}
			idx++;
		}
		return arr[pq2.poll()];
	}*/
	
	private static int findMinimumForFixedLength(final int[] arr, int n, int d) {
		Deque<Integer> q = new LinkedList<Integer>();
		int min = Integer.MAX_VALUE;
		int idx = 0;
		for(int i = 0; i < d; i++) {
			while(!q.isEmpty() && arr[i] >= arr[q.getLast()]) {
				q.removeLast();
			}
			q.addLast(i);
		}
		while(idx <= n-d) {
			int maxElem = q.peekFirst();
			if(arr[maxElem] < min) {
				min = arr[maxElem];
			}
			if(maxElem == idx) {
				q.pop();
			}
			while(!q.isEmpty() && idx+d < n && arr[idx+d] >= arr[q.getLast()]) {
				q.removeLast();
			}
			q.add(idx+d);
			idx++;
		}
		return min;
	}
	
}
