package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Refer to :
 * https://www.hackerearth.com/code-monk-segment-tree-and-lazy-propagation/
 * algorithm/spartans-leonidas-vs-xerxes-monk/
 *
 */
public class LeonidasVsXerxes {

	static class Segment {
		int length;   //length of longest increasing chain for current segment
		int from_start;   //length of longest increasing chain starting from start of current segment
		int to_end;   //length of longest increasing chain ending at the end of current segment
		
		public Segment() {
		}
		
		public Segment(int l,int f_s,int t_e) {
			length = l;
			from_start = f_s;
			to_end = t_e;
		}
	}
	
	static class SegmentTree {
		Segment[] segments;
		
		public SegmentTree(int size) {
			segments = new Segment[size];
		}
		
		int max(int a,int b) {
			return (a > b)?a:b;
		}
		
		void build(int node,int start,int end,int[] arr) {
			if(start == end) {
				//leaf node
				segments[node] = new Segment(1, 1, 1);
			} else {
				int mid = (start + end)/2;
				build(2*node+1, start, mid, arr);
				build(2*node+2, mid+1, end, arr);
				Segment cur_segment = new Segment();
				int l_start = segments[2*node+1].from_start;
				int l_end = segments[2*node+1].to_end;
				int r_start = segments[2*node+2].from_start;
				int r_end = segments[2*node+2].to_end;
				int max_len = max(segments[2*node+1].length,segments[2*node+2].length);
				
				if(arr[mid] < arr[mid+1]) {
					if(l_start == mid-start+1) {
						cur_segment.from_start = l_start + r_start;
					} else {
						cur_segment.from_start = l_start;
					}
					
					if(r_end == end-mid) {
						cur_segment.to_end = r_end + l_end;
					} else {
						cur_segment.to_end = r_end;
					}
					
					if(l_end + r_start > max_len) {
						cur_segment.length = l_end + r_start;
					} else {
						cur_segment.length = max_len;
					}
					
				} else {
					cur_segment.from_start = l_start;
					cur_segment.to_end = r_end;
					cur_segment.length = max_len;
				}
				segments[node] = cur_segment;
			}
		}
		
		void update(int node, int start, int end, int idx, int val, int[] arr) {

			if(start == end) {
				//leaf node
				arr[idx] += val;
			} else {
				int mid = (start + end)/2;
				if(idx >= start && idx <= mid) {
				update(2*node+1, start, mid, idx, val, arr);
				} else {
				update(2*node+2, mid+1, end, idx, val, arr);
				}
				Segment cur_segment = new Segment();
				int l_start = segments[2*node+1].from_start;
				int l_end = segments[2*node+1].to_end;
				int r_start = segments[2*node+2].from_start;
				int r_end = segments[2*node+2].to_end;
				int max_len = max(segments[2*node+1].length,segments[2*node+2].length);
				
				if(arr[mid] < arr[mid+1]) {
					if(l_start == mid-start+1) {
						cur_segment.from_start = l_start + r_start;
					} else {
						cur_segment.from_start = l_start;
					}
					
					if(r_end == end-mid) {
						cur_segment.to_end = r_end + l_end;
					} else {
						cur_segment.to_end = r_end;
					}
					
					if(l_end + r_start > max_len) {
						cur_segment.length = l_end + r_start;
					} else {
						cur_segment.length = max_len;
					}
					
				} else {
					cur_segment.from_start = l_start;
					cur_segment.to_end = r_end;
					cur_segment.length = max_len;
				}
				segments[node] = cur_segment;
			}
		}
		
		Segment query(int node,int start,int end,int l,int r, int[] arr) {
			if(start > end || l > end || r < start)
				return null;
			
			
			if(l <= start && r >= end) {
				return segments[node];
			}
			int mid = (start + end)/2;
			Segment l_Segment = query(2*node+1, start, mid, l, r, arr);
			Segment r_Segment = query(2*node+2, mid+1, end, l, r, arr);
			if(l_Segment == null) {
				return r_Segment;
			} else if(r_Segment == null) {
				return l_Segment;
			} else {
				Segment ret_Segment = new Segment();
				ret_Segment.length = max(l_Segment.length,r_Segment.length);
				if(arr[mid] < arr[mid+1]) {
				
					if(l_Segment.from_start == mid-l+1) {
						ret_Segment.from_start = l_Segment.from_start + r_Segment.from_start;
					} else {
						ret_Segment.from_start = l_Segment.from_start;
					}
					
					if(r_Segment.to_end == r-mid) {
						ret_Segment.to_end = r_Segment.to_end + l_Segment.to_end;
					} else {
						ret_Segment.to_end = r_Segment.to_end;
					}
					
					if(l_Segment.to_end + r_Segment.from_start > ret_Segment.length)
						ret_Segment.length = l_Segment.to_end + r_Segment.from_start;
				} else {
					ret_Segment.from_start = l_Segment.from_start;
					ret_Segment.to_end = r_Segment.to_end;
				}
				return ret_Segment;
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		String[] arr;
		int[] elem;
		int N,Q,type,l,r;
		SegmentTree tree = new SegmentTree(1000000);
		for(int i = 1; i <= T; i++) {
			arr = br.readLine().split(" ");
			N = Integer.parseInt(arr[0]);
			Q = Integer.parseInt(arr[1]);
			elem = new int[N];
			arr =  br.readLine().split(" ");
			for(int j = 0; j < N; j++)
				elem[j] = Integer.parseInt(arr[j]);
			
			tree.build(0, 0, N-1, elem);
			
			for(int k = 0; k < Q; k++) {
				arr = br.readLine().split(" ");
				type = Integer.parseInt(arr[0]);
				l = Integer.parseInt(arr[1]);
				r = Integer.parseInt(arr[2]);
				if(type == 0) {
					System.out.println(tree.query(0, 0, N-1, l-1, r-1, elem).length);
				} else {
					tree.update(0, 0, N-1, l-1, r, elem);
				}
			}
		}
	}
	
}
