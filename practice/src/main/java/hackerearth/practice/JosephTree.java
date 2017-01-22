package hackerearth.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Refer to: https://www.hackerearth.com/august-clash-16/algorithm/joseph-and-
 * treeaugclash/
 *
 */
public class JosephTree {

	static class TreeNode {
		int index;
		TreeNode parent;
		Integer parentEdgeWeight;
		Map<Integer, Integer> map;

		public TreeNode(int i) {
			this.index = i;
			map = new TreeMap<Integer, Integer>();
		}

		public TreeNode getParent() {
			return parent;
		}

		public void setParent(TreeNode parent) {
			this.parent = parent;
		}

		public Integer getParentEdgeWeight() {
			return parentEdgeWeight;
		}

		public void setParentEdgeWeight(Integer parentEdgeWeight) {
			this.parentEdgeWeight = parentEdgeWeight;
		}
	}

	static class Tree {
		int V; // no. of vertices
		TreeNode[] nodes;

		public Tree(int V) {
			this.V = V;
			nodes = new TreeNode[V];
			for (int i = 0; i < V; i++)
				nodes[i] = new TreeNode(i);
			// index 0 is the root of tree ,assigning an imaginary parent to it
			nodes[0].parent = new TreeNode(-1);
		}

		void addEdge(int src, int dest, int weight) {
			if (nodes[src].parent == null) {
				int temp = src;
				src = dest;
				dest = temp;
			}
			nodes[dest].setParent(nodes[src]); // src is parent of dest
			nodes[dest].setParentEdgeWeight(weight);
			if (nodes[src].map.get(weight) == null)
				nodes[src].map.put(weight, 0);
			nodes[src].map.put(weight, nodes[src].map.get(weight) + 1);
			int dist = weight;
			while (src >= 0 && nodes[src].parent != null && nodes[src].parent.index >= 0) {
				nodes[src].parent.map.put(dist + nodes[src].parentEdgeWeight,
						nodes[src].parent.map.get(dist + nodes[src].parentEdgeWeight) == null ? 1
								: nodes[src].parent.map.get(dist + nodes[src].parentEdgeWeight) + 1);
				dist += nodes[src].parentEdgeWeight;
				src = nodes[src].parent.index;
			}
		}
	}

	static class PreProcess {
		int ind1;
		int ind2;
		int weight;

		public PreProcess(int i1, int i2, int w) {
			this.ind1 = i1;
			this.ind2 = i2;
			this.weight = w;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		Tree tree = new Tree(N);
		String[] arr;
		int a, b, w;
		boolean[] visited = new boolean[N - 1];
		PreProcess[] elements = new PreProcess[N - 1];
		int count_processed = 0;
		for (int i = 0; i < N - 1; i++) {
			arr = br.readLine().split(" ");
			int ind1 = Integer.parseInt(arr[0]) - 1;
			int ind2 = Integer.parseInt(arr[1]) - 1;
			if (ind1 == 0 || ind2 == 0) {
				tree.addEdge(ind1, ind2, Integer.parseInt(arr[2]));
				visited[i] = true;
				count_processed += 1;
			} else {
				elements[i] = new PreProcess(Integer.parseInt(arr[0]) - 1, Integer.parseInt(arr[1]) - 1,
						Integer.parseInt(arr[2]));
			}
		}
		while (count_processed != N - 1) {
			for (int i = 0; i < N - 1; i++) {
				if (!visited[i] && (tree.nodes[elements[i].ind1].parent != null
						|| tree.nodes[elements[i].ind2].parent != null)) {
					tree.addEdge(elements[i].ind1, elements[i].ind2, elements[i].weight);
					visited[i] = true;
					count_processed += 1;
				}
			}
		}

		int Q = Integer.parseInt(br.readLine().trim());
		for (int i = 1; i <= Q; i++) {
			arr = br.readLine().split(" ");
			System.out.println(findKthDistance(tree, Integer.parseInt(arr[0]) - 1, Integer.parseInt(arr[1])));
		}
	}

	private static int findKthDistance(Tree tree, int v, int k) {
		int result = -1;
		int count = 0;
		Iterator<Entry<Integer, Integer>> it = tree.nodes[v].map.entrySet().iterator();
		Entry<Integer, Integer> next = null;
		while (it.hasNext()) {
			next = it.next();
			if (count + next.getValue() >= k)
				return next.getKey();
			count += next.getValue();
		}
		return result;
	}

}
