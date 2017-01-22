package hackerrank.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Refer to : https://www.hackerrank.com/challenges/bfsshortreach
 *
 *this is a simple problem as all edges have same weight (6)
 */
public class ShortestReach {

	static class Graph {
		int V; //no. of vertices
		LinkedList<Integer> adjList[];
		
		public Graph(int v) {
			this.V = v;
			adjList = new LinkedList[v];
			for(int i = 0; i < v; i++)
				adjList[i] = new LinkedList<Integer>();
		}
		
		void addEdge(int u,int v) {
			adjList[u-1].add(v-1);
			adjList[v-1].add(u-1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int nodes,edges;
		String[] arr = null;
		Graph graph = null;
		int edgeWeight = 6;
		for(int i = 1; i <= T; i++) {
			//this is ith test case
			arr = br.readLine().split(" ");
			nodes = Integer.parseInt(arr[0]);
			edges = Integer.parseInt(arr[1]);
			graph = new Graph(nodes);
			for(int j = 1; j <= edges; j++) {
				arr = br.readLine().split(" ");
				graph.addEdge(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
			}
			int startPoint = Integer.parseInt(br.readLine().trim());
			printShortestDistances(startPoint,graph,edgeWeight);
		}
	}

	private static void printShortestDistances(int startPoint, Graph graph,int edgeWeight) {
		int[] dist = new int[graph.V];
		for(int i = 0; i < graph.V; i++)
			dist[i] = -1;
		boolean[] visited = new boolean[graph.V];
		LinkedList<Integer> adj;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(startPoint-1);
		visited[startPoint-1] = true;
		int cur_level = 0;   //this will hold the current level we are iterating from startPoint
		int cur_level_nodes = 1;  //no. of nodes in current level
		int next_level_nodes = 0;  //no. of nodes of next level
		while(!queue.isEmpty()) {
			int curElement = queue.poll();
			dist[curElement] = cur_level * edgeWeight;
			cur_level_nodes -= 1;
			adj = graph.adjList[curElement];
			Iterator<Integer> it = adj.iterator();
			while (it.hasNext()) {
				Integer next = it.next();
				if(!visited[next]) {
					visited[next] = true;
					queue.add(next);
					next_level_nodes += 1;
				}
			}
			if(cur_level_nodes == 0) {
				//no nodes left on this level
				//increment cur_level by 1 and set cur_level_nodes to next_level_nodes
				cur_level += 1;
				cur_level_nodes = next_level_nodes;
				next_level_nodes = 0;
			}
		}
		
		for(int i = 0; i < graph.V; i++) {
			if(dist[i] == 0)
				continue;
			System.out.print(dist[i] + " ");
		}
		System.out.println();
	}
}
