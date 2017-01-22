package code.practice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

public class PrintPathsDiGraphWithoutRecursion {

	private static void printPathFromParent(int dest,Integer d, boolean[] visited, Map<Integer, Integer> parentPointers) {
		visited[d] = false;
		System.out.print(dest+" ");
		while(d != null) {
			System.out.print(d+" ");
			d = parentPointers.get(d);
		}
		System.out.println();
	}
	
	private static void PrintPaths(Graph graph, int src, int dest) {
		//all not visited initially
		boolean[] visited = new boolean[graph.getV()];
		Stack<Integer> stack = new Stack<>();
		stack.push(src);
		visited[src] = true;
		
		Map<Integer, Integer> parentPointers = new HashMap<>();
		parentPointers.put(src, null);
		while(!stack.isEmpty()) {
			int d = stack.pop();
			if(d == dest) {
				printPathFromParent(dest,d,visited,parentPointers);
				continue;
			}
			visited[d] = true;
			Iterator<Integer> it = graph.getAdj()[d].iterator();
			while(it.hasNext()) {
				int next = it.next();
				if(!visited[next]) {
					stack.push(next);
					parentPointers.put(next, d);
				}
			}
		}
		
	}

	public static void main(String[] args) {
		Graph graph = new Graph(4);
		graph.addEdge(2, 0);
		graph.addEdge(0, 2);
		graph.addEdge(0, 3);
		graph.addEdge(2, 1);
		graph.addEdge(0, 1);
		graph.addEdge(1, 3);

		// print all paths from 2 to 3
		PrintPaths(graph, 2, 3);

}

}
