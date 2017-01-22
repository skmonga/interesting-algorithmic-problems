package hackerrank.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import hackerrank.graph.ShortestReach.Graph;

/**
 * Refer to : https://www.hackerrank.com/challenges/even-tree
 *
 */
public class EvenTree {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N,M;
		String[] arr = br.readLine().split(" ");
		N = Integer.parseInt(arr[0]);
		M = Integer.parseInt(arr[1]);
		Graph graph = new Graph(N);
		for(int i = 1; i <= M; i++) {
			arr = br.readLine().split(" ");
			graph.addEdge(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
		}
		System.out.println(numRemovedEdges(graph));
	}

	private static int numRemovedEdges(Graph graph) {
		int removedEdges = 0;
		List<Integer> list = null;
		for(Integer i = 0; i < graph.V; i++) {
			list = graph.adjList[i];
			if(list.size() == 1)     //cannot remove the single edge
				continue;
			for(Integer j = 0; j < list.size();j++) {
				Integer dest = list.get(j);
				if(graph.adjList[dest].size() == 1)
					continue;
				//try to remove this edge and check if property of even tree is maintained
				list.remove(dest);
				graph.adjList[dest].remove(i);
				if(checkIfEvenTree(graph,i,dest)) {
					removedEdges++;
				} else {
					list.add(dest);
					graph.adjList[dest].add(i);
				}
			}
		}
		return removedEdges;
	}

	private static boolean checkIfEvenTree(Graph graph,int elem1,int elem2) {
		int size1 = numberOfNodes(graph,elem1);
		if((size1 & 1) != 0)
		return false;
		int size2 = numberOfNodes(graph,elem2);
		if((size2 & 1) != 0)
		return false;
		return true;
	}

	private static int numberOfNodes(Graph graph, int elem) {
		int num = 1;
		boolean[] visited = new boolean[graph.V];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(elem);
		Integer current = null,next = null;
		Iterator<Integer> it = null;
		while(!queue.isEmpty()) {
			current = queue.poll();
			visited[current] = true;
			it = graph.adjList[current].iterator();
			while(it.hasNext()) {
				next = it.next();
				if(!visited[next]) {
					queue.add(next);
					num++;
				}
			}
		}
		return num;
	}
}
