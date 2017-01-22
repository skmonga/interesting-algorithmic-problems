package code.practice;

import java.util.Iterator;
import java.util.LinkedList;

public class CycleDirected {
	
	private static boolean isCyclic(Graph g) {
		int v = g.getV();
		boolean visited[] = new boolean[v];
		boolean recStack[] = new boolean[v];

		for (int i = 0; i < v; i++) {
			if (isCyclicUtil(g, i, visited, recStack))
				return true;
		}

		return false;
	}

	private static boolean isCyclicUtil(Graph g, int v, boolean[] visited, boolean[] recStack) {
		if (visited[v] == false) {
			// mark this visited and part of recursion stack
			visited[v] = true;
			recStack[v] = true;

			Iterator<Integer> it = g.getAdj()[v].listIterator();
			while (it.hasNext()) {
				int n = it.next();
				if (!visited[n] && isCyclicUtil(g, n, visited, recStack)) {
					return true;
				} else if (recStack[n]) {
					return true;
				}
			}
		}
		recStack[v] = false;
		return false;
	}

	public static void main(String[] args) {
		Graph g = new Graph(4);
		g.addEdge(0, 1);
	    g.addEdge(0, 2);
	    g.addEdge(1, 2);
	    g.addEdge(2, 0);
	    g.addEdge(2, 3);
	    g.addEdge(3, 3);
	    
	    if(isCyclic(g)) {
	    	System.out.println("Cycle is present");
	    } else {
	    		System.out.println("No cycle present");
	    	}
	}
	
}

