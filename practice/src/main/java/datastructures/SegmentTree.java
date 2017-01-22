package datastructures;

/**
 * Refer to :
 * https://www.hackerearth.com/notes/segment-tree-and-lazy-propagation/
 *
 * This is a segment tree for finding sum of elements from left to right
 */
public class SegmentTree {

	int[] segment;
	
	/**
	 * @param node  the root index of current segment
	 * @param start  the start of current segment
	 * @param end  the end of current segment
	 * @param arr  the array for which segment tree is built
	 */
	public void build(int node,int start,int end,int[] arr) {
		
		if(start == end) {
			//leaf node
			segment[node] = arr[start];
		} else {
			int mid = (start +  end)/2;
			build(2*node+1, start, mid, arr);
			build(2*node+2, mid+1, end, arr);
			segment[node] = segment[2*node+1] + segment[2*node+2];
		}
	}
	
	/**
	 * @param node
	 * @param start
	 * @param end
	 * @param idx  the index in array to update
	 * @param val  the value to be added to arr[idx]
	 * @param arr
	 */
	public void update(int node,int start,int end,int idx,int val,int[] arr) {
		if(start == end) {
			//leaf node
			arr[idx] += val;
			segment[node] += val;
		} else {
			int mid = (start + end)/2;
			if(idx >= start && idx <= mid) {
				// If idx is in the left child, recurse on the left child
				update(2*node+1, start, mid, idx, val, arr);
			} else {
				// if idx is in the right child, recurse on the right child
				update(2*node+2, mid+1, end, idx, val, arr);
			}
			// Internal node will have the sum of both of its children
			segment[node] = segment[2*node+1] + segment[2*node+2];
		}
	}
	
	/**
	 * @param node
	 * @param start
	 * @param end
	 * @param l  the start of query range
	 * @param r  the end of query range
	 * @param arr
	 * @return
	 */
	public int query(int node,int start,int end,int l,int r,int[] arr) {
		if(l > end || r < start) {
			 // range represented by a node is completely outside the given range
			return 0;
		}
		
		if(l <= start && r >= end) {
			// range represented by a node is completely inside the given range
			return segment[node];
		}
		int mid = (start + end)/2;
		int l_sum = query(2*node+1, start, mid, l, r, arr);
		int r_sum = query(2*node+2, mid+1, end, l, r, arr);
		return l_sum + r_sum;
	}
	
	public static void main(String[] args) {
		int[] arr = {1,3,5,7,9,11};
		int size = arr.length;
		//the first level contains 1 node
		//second level 2 nodes
		//third level 4 nodes
		//height of segment tree : log(n) to base 2
		//so total nodes in segment tree : 1+2+4+...+pow(2,ceil(logn)) = 2*pow(2,ceil(logn))-1
		SegmentTree segTree  = new SegmentTree();
		int segment_height = (int) Math.ceil(Math.log(size)/Math.log(2));
		int segment_size = (int) (2*Math.pow(2, segment_height)) - 1;
		segTree.segment = new int[segment_size];
	
		segTree.build(0, 0, size-1, arr);
		for(int i = 0; i < segTree.segment.length; i++)
			System.out.println(segTree.segment[i]);
	}
}
