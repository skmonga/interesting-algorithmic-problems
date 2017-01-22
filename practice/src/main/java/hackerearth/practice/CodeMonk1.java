package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Refer to : https://www.hackerearth.com/code-monk-segment-tree-and-lazy-propagation/algorithm/2-vs-3/
 *
 */
public class CodeMonk1 {

	static class SegmentTree {
		int[] segment;
		
		public SegmentTree(int size) {
			segment = new int[size];
		}
		
		void build(char[] arr,int node,int start,int end) {
			if(start == end) {
				//leaf node
				segment[node] = arr[start] - '0';
			} else {
				int mid = (start +  end)/2;
				build(arr,2*node+1, start, mid);
				build(arr,2*node+2, mid+1, end);
				segment[node] = ((segment[2*node+1]*(((end-mid)%2 == 0)?1:2)) + segment[2*node+2])%3;
			}
		}
		
		void update(char[] arr,int node,int start,int end,int idx) {
			if(start == end) {
				//leaf node,reached where we want to reach
				arr[idx] = '1';
				segment[node] = 1;
			} else {
				int mid = (start + end)/2;
				if(idx >= start && idx <= mid) {
					// If idx is in the left child, recurse on the left child
					update(arr,2*node+1, start, mid, idx);
				} else {
					// if idx is in the right child, recurse on the right child
					update(arr,2*node+2, mid+1, end, idx);
				}
				// Internal node will have the sum of both of its children
				segment[node] = ((segment[2*node+1]*(((end-mid)%2 == 0)?1:2)) + segment[2*node+2])%3;
			}
		}
		
		int query(char[] arr, int node, int start, int end, int l, int r) {
			if (start > end || l > end || r < start)
				return 0;

			if (l <= start && r >= end)
				return segment[node];

			int mid = (start + end) / 2;
			int l_val, r_val, shift_factor = 1;
			if (l > mid) {
				l_val = 0;
				r_val = query(arr, 2 * node + 2, mid + 1, end, l, r);
			} else {
				l_val = query(arr, 2 * node + 1, start, mid, l, r);
				if(r <= mid) {
					r_val = 0;
					shift_factor = 2;
				} else {
					r_val = query(arr, 2 * node + 2, mid + 1, end, l, r);
					shift_factor = r - mid;
				}
			}
			return ((l_val * ((shift_factor % 2 == 0)?1 :2)) + r_val) % 3;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		String content = br.readLine();
		char[] charArray = content.toCharArray();
		SegmentTree tree = new SegmentTree(1000000);
		tree.build(charArray, 0, 0, N-1);
		int Q = Integer.parseInt(br.readLine().trim());
		String[] arr;
		for(int i = 1; i <= Q; i++) {
			arr = br.readLine().split(" ");
			if(Integer.parseInt(arr[0]) == 0) {
				int l = Integer.parseInt(arr[1]);
				int r = Integer.parseInt(arr[2]);
				System.out.println(tree.query(charArray, 0, 0, N-1, l, r));
			} else {
				int idx = Integer.parseInt(arr[1]);
				if(charArray[idx] == '0')
				tree.update(charArray, 0, 0, N-1, Integer.parseInt(arr[1]));
			}
		}
	}
}
