package code.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Refer to :
 * https://www.hackerearth.com/sap-labs-java-hiring-challenge/problems/
 *
 */
public class SAPLabs2 {

	static class Graph {
		int V; // no. of vertices
		LinkedList<Integer> adjList[];

		public Graph(int v) {
			this.V = v;
			adjList = new LinkedList[v];
			for (int i = 0; i < v; i++)
				adjList[i] = new LinkedList<Integer>();
		}

		void addEdge(int u, int v) {
			adjList[u - 1].add(v - 1);
			adjList[v - 1].add(u - 1);
		}
	}
	
	private static boolean[] visited = new boolean[200000];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int[] array = new int[N];
		String[] arr = br.readLine().split(" ");
		for (int i = 0; i < N; i++)
			array[i] = Integer.parseInt(arr[i]);

		Graph graph = new Graph(N);
		for (int i = 1; i < N; i++) {
			arr = br.readLine().split(" ");
			graph.addEdge(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
		}
		// number of queries
		int Q = Integer.parseInt(br.readLine().trim());
		int src, dest;
		for (int i = 1; i <= Q; i++) {
			arr = br.readLine().split(" ");
			src = Integer.parseInt(arr[0]);
			dest = Integer.parseInt(arr[1]);
			// find the path and number of distinct values in there
			System.out.println(numberDistinctIntegers(graph, src-1, dest-1,array));
		}
	}

	private static int numberDistinctIntegers(Graph graph, int src, int dest,int[] array) {
		// the set will hold the unique values,its size will be the distinct
		// count
		Set<Integer> set = new HashSet<>();
		checkPathToDestination(graph, src, dest, set,array);
//		numberDistinctIntegersUtil(graph, src, dest, set, array, visited);
		return set.size();
	}

	private static boolean checkPathToDestination(Graph graph, int src, int dest, 
			Set<Integer> set,int[] array) {

		if(src == dest) {
			set.add(array[dest]);
			return true;
		}
		//marking src as visited
		visited[src] = true;
		Iterator<Integer> it = graph.adjList[src].iterator();
		while(it.hasNext()) {
			int next = it.next();
			if(!visited[next] &&
					checkPathToDestination(graph, next, dest, set,array)) {
				//this path leads to destination
				set.add(array[src]);
				return true;
			}
		}
		visited[src] = false;
		return false;
	}
	
	static class QueueNode {
		Integer current;
		QueueNode parentNode;
		
		public QueueNode(Integer c,QueueNode p) {
			this.current = c;
			this.parentNode = p;
		}
		
		public int getCurrent() {
			return current;
		}
		public void setCurrent(Integer currentNode) {
			this.current = currentNode;
		}
		public QueueNode getParentNode() {
			return parentNode;
		}
		public void setParentNode(QueueNode parentNode) {
			this.parentNode = parentNode;
		}
	}
	
	private static int numberDistinctIntegersUtil(Graph graph, int src, int dest,
			Set<Integer> set,int[] array,boolean[] visited) {
		Queue<QueueNode> queue = new LinkedList<QueueNode>();
		queue.add(new QueueNode(src, null));
		visited[src] = true;
		QueueNode cur = null;
		Iterator<Integer> it = null;
		while(!queue.isEmpty()) {
			cur = queue.poll();
			if(cur.getCurrent() == dest)
				break;
			
			it = graph.adjList[cur.getCurrent()].iterator();
			while(it.hasNext()) {
				int next = it.next();
				if(!visited[next]) {
					queue.add(new QueueNode(next, cur));
					visited[next] = true;
				}
			}
		}
		
		//cur holds the destination node
		//reverse from cur to source
		while(cur != null) {
			set.add(array[cur.getCurrent()]);
			cur = cur.getParentNode();
		}
		return set.size();
	}
}
