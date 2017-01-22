package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WalmartLabs {

	static class TreeNode {
		int index;
		TreeNode parent;
		int edgeWeight;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int index) {
			this.index = index;
		}
	}
	
	static class Tree {
		TreeNode root;
		List<Integer> leaves;
		
		public Tree() {
			
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		int e = Integer.parseInt(br.readLine().trim());
		TreeNode[] nodes = new TreeNode[n];
		Tree tree = new Tree();
		int start,end,count;
		String[] arr;
		for(int i = 1; i <= e; i++) {
			arr = br.readLine().split(" ");
			start = Integer.parseInt(arr[0]);
			end = Integer.parseInt(arr[1]);
			count = Integer.parseInt(arr[2]);
			addEdge(nodes, start, end, count);
		}
		
		findParentAndMarkLeaves(nodes,tree,n);
		int left_weight = calculateWeight(tree.root.left) - tree.root.left.edgeWeight;
		int right_weight = calculateWeight(tree.root.right) - tree.root.right.edgeWeight;
		int min_time = Integer.MAX_VALUE;
		int min_count = 0;
		int leaves_count = tree.leaves.size();
		int[] times = new int[leaves_count];
		for(int i = 0; i < leaves_count; i++) {
			int min_cur = calculateMinTime(nodes,tree.root,tree.leaves.get(i),left_weight,right_weight);
			times[i] = min_cur;
			if(times[i] < min_time) {
				min_count = 1;
				min_time = times[i];
			} else if(times[i] == min_time) {
				min_count += 1;
			}
		}
		
		System.out.println(min_time);
		TreeNode leaf;
		int count_taken = 0;
		for(int i = 0; i < leaves_count; i++) {
			if(times[i] == min_time) {
				count_taken += 1;
				leaf = nodes[tree.leaves.get(i)];
				printPath(leaf,tree.root);
				if(count_taken != min_count)
					System.out.println();
				else
					break;
			}
				
		}
		
	}

	private static void printPath(TreeNode leaf,TreeNode root) {
		int idx;
		while(leaf != root) {
			idx = leaf.index+1;
			System.out.print(idx + " -> ");
			leaf = leaf.parent;
		}
		idx = leaf.index+1;
		System.out.print(idx);
	}

	private static int calculateWeight(TreeNode node) {
		if(node == null)
			return 0;
		return node.edgeWeight + calculateWeight(node.left) + calculateWeight(node.right);
	}

	private static int calculateMinTime(TreeNode[] nodes, TreeNode root,int index,int l_w,int r_w) {

		//first calculate time to reach the child of root
		TreeNode cur = nodes[index];
		int cost = 0,self_count,other_count;
		while(cur.parent != root) {
			if(cur.parent.left == cur) {
				self_count = cur.edgeWeight;
				other_count = (cur.parent.right == null)?0:cur.parent.right.edgeWeight;
			} else {
				self_count = cur.edgeWeight;
				other_count = (cur.parent.left == null)?0:cur.parent.left.edgeWeight;
			}
			if(self_count >= other_count)
				cost += (self_count + other_count);
			else
				cost += (2*self_count + 1);
			cur = cur.parent;
		}
		
		TreeNode otherChild = null;
		//now we are at root's child
		if(root.left == cur) {
			otherChild = root.right;
			other_count = r_w + ((otherChild == null)?0:otherChild.edgeWeight);
			self_count = cost + cur.edgeWeight;
		} else {
			otherChild = root.left;
			other_count = l_w + ((otherChild == null)?0:otherChild.edgeWeight);
			self_count = cost + cur.edgeWeight;
		}
		
		if(self_count >= other_count)
			return (self_count + other_count);
		else
			return (2*self_count + 1);
	}

	private static void findParentAndMarkLeaves(TreeNode[] nodes, Tree tree, int n) {
		tree.leaves = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			if(nodes[i].left == null && nodes[i].right == null) {
				tree.leaves.add(i);
			} else if(nodes[i].parent == null) {
				tree.root = nodes[i];
			}
		}
	}

		private static void addEdge(TreeNode[] nodes,int start,int end,int count) {
			if(nodes[start-1] == null)
				nodes[start-1] = new TreeNode(start-1);
			if(nodes[end-1] == null)
				nodes[end-1] = new TreeNode(end-1);
			nodes[start-1].parent = nodes[end-1];
			nodes[start-1].edgeWeight = count;
			if(nodes[end-1].left == null)
				nodes[end-1].left = nodes[start-1];
			else
				nodes[end-1].right = nodes[start-1];
		}
	
	
}
