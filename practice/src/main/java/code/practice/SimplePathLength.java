package code.practice;

import java.util.LinkedList;

/**
 * Refer to:
 * http://www.geeksforgeeks.org/find-if-there-is-a-path-of-more-than-k-length-
 * from-a-source/
 *
 */
public class SimplePathLength {

	static class AdjListNode {
		int dest;
		int weight;

		public AdjListNode(int d, int w) {
			dest = d;
			weight = w;
		}

		public int getDest() {
			return dest;
		}

		public void setDest(int dest) {
			this.dest = dest;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}
	}

	static class Graph {
		int V;
		LinkedList<AdjListNode> adj[];

		public Graph(int v) {
			this.V = v;
			adj = new LinkedList[v];
			for (int i = 0; i < v; i++)
				adj[i] = new LinkedList<AdjListNode>();
		}

		public void addEdge(int src, int dest, int weight) {
			// this is undirected graph
			adj[src].add(new AdjListNode(dest, weight));
			adj[dest].add(new AdjListNode(src, weight));
		}
	}

	private static boolean possiblePathGreaterDistanceUtil(Graph g, int src, int dist, boolean[] visited) {
		if (dist < 0)
			return true;
		// mark this vertex as visited
		visited[src] = true;
		LinkedList<AdjListNode> list = g.adj[src];
		for (AdjListNode adjNode : list) {
			if (!visited[adjNode.dest]
					&& possiblePathGreaterDistanceUtil(g, adjNode.dest, dist - adjNode.weight, visited))
				return true;
		}
		visited[src] = false;
		return false;
	}

	private static boolean possiblePathGreaterDistance(Graph g, int src, int dist) {
		boolean[] visited = new boolean[g.V];
		return possiblePathGreaterDistanceUtil(g, src, dist, visited);
	}

	public static void main(String[] args) {
		int v = 9;
		Graph g = new Graph(v);

		g.addEdge(0, 1, 4);
		g.addEdge(0, 7, 8);
		g.addEdge(1, 2, 8);
		g.addEdge(1, 7, 11);
		g.addEdge(2, 3, 7);
		g.addEdge(2, 8, 2);
		g.addEdge(2, 5, 4);
		g.addEdge(3, 4, 9);
		g.addEdge(3, 5, 14);
		g.addEdge(4, 5, 10);
		g.addEdge(5, 6, 2);
		g.addEdge(6, 7, 1);
		g.addEdge(6, 8, 6);
		g.addEdge(7, 8, 7);

		int src = 0;
		int dist = 62;
		System.out.println(possiblePathGreaterDistance(g, src, dist));

		dist = 60;
		System.out.println(possiblePathGreaterDistance(g, src, dist));
	}

}
