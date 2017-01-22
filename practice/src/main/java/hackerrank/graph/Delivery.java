package hackerrank.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Refer to : https://www.hackerrank.com/contests/walmart-codesprint-algo/challenges/delivery
 *
 */
public class Delivery {


	static class Node {
		int key;
		int depth;
		Node parent;
		
		public Node(int k,int d,Node p) {
			this.key = k;
			this.depth = d;
			this.parent = p;
		}
	}
	
	static class BinaryTree {
		Node[] nodes;
		
		public BinaryTree(int n) {
			this.nodes = new Node[n];
		}
		
		int findDistance(Node n1,Node n2) {
			int d1 = n1.depth, d2 = n2.depth;
			int diff = d1 - d2;
			if(diff < 0) {
				Node temp = n1;
	            n1 = n2;
	            n2 = temp;
	            diff = -diff;
			}
			int distance = diff;
			
			while (diff-- != 0)
	            n1 = n1.parent;
			
			//now n1 and n2 are at same height
			while(n1 != null && n2 != null) {
				if (n1 == n2)
	                return distance;
	            n1 = n1.parent;
	            n2 = n2.parent;
	            distance += 2;
			}
			
			return distance;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr = br.readLine().split(" ");
		int n = Integer.parseInt(arr[0]);
		int m = Integer.parseInt(arr[1]);
		int q = Integer.parseInt(arr[2]);
		
		BinaryTree tree = new BinaryTree(n+1);
		createCityTree(tree,n,1,0,null);
		
		Map<Integer, List<Integer>> foodStores = new HashMap<>();
		for(int i = 1; i <= m; i++) {
			arr = br.readLine().split(" ");
			int size = Integer.parseInt(arr[0]);
			List<Integer> stores = new ArrayList<>(size);
			for(int j = 1; j <= size; j++)
				stores.add(Integer.parseInt(arr[j]));
			foodStores.put(i, stores);
		}
		
		int distance = 0;
		int src = 1,dest,foodType;
		for(int i = 1; i <= q; i++) {
			arr = br.readLine().split(" ");
			foodType = Integer.parseInt(arr[0]);
			dest = Integer.parseInt(arr[1]);
			distance += minimumDistance(src,dest,foodType,foodStores,tree);
			src = dest;
		}
		System.out.println(distance);
	}

	private static int minimumDistance(int src, int dest, int foodType, Map<Integer, List<Integer>> foodStores,
			BinaryTree tree) {
		int min_dist = Integer.MAX_VALUE;
		Iterator<Integer> it = foodStores.get(foodType).iterator();
		while(it.hasNext()) {
			int store = it.next();
			int cost = tree.findDistance(tree.nodes[src], tree.nodes[store]) +
					tree.findDistance(tree.nodes[store],tree.nodes[dest]);
			if(cost < min_dist)
				min_dist = cost;
		}
		return min_dist;
	}

	private static void createCityTree(BinaryTree tree,int n,int index,int depth,Node parent) {
		if(index > n)
			return;
		tree.nodes[index] = new Node(index, depth, parent);
		createCityTree(tree, n, 2*index, depth + 1, tree.nodes[index]);
		createCityTree(tree, n, 2*index + 1, depth + 1, tree.nodes[index]);
	}
}
