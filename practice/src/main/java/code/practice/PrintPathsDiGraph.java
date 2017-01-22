package code.practice;

import java.util.Iterator;

/**
 * Refer to : http://www.geeksforgeeks.org/find-paths-given-source-destination/
 * Print all paths from a given source to a destination. Given a directed graph,
 * a source vertex ‘s’ and a destination vertex ‘d’, print all paths from given
 * ‘s’ to ‘d’.
 * This solution uses recursion. See PrintPathsDiGraphWithoutRecursion for another 
 * solution without recursion.
 */
public class PrintPathsDiGraph {

	//graph - the original graph
	//src - src vertex
	//dest - destination vertex
	//visited - the array which keeps track whether node visited or not
	//path - this array stores the path 
	//index_in_path - index where the current element will be inserted in path
	private static void printPathsUtil(Graph graph, int src, int dest, 
			boolean[] visited, int[] path, int index_in_path) {
		
		visited[src] = true;
		path[index_in_path] = src;
		
		if(src == dest) {
			for(int i = 0; i <= index_in_path; i++) {
				System.out.print(path[i] + " ");
			}
				System.out.println();
		}
		else {
			Iterator<Integer> it = graph.getAdj()[src].iterator();
			while(it.hasNext()) {
				int d = it.next();
				if(!visited[d])  {
					printPathsUtil(graph, d, dest, visited, path, index_in_path + 1);
				}
			}
		}
		
		//mark this element as unvisited
		visited[src] = false;
		
		}
	
	
	private static void PrintPaths(Graph graph, int src, int dest) {

		//all not visited initially
		boolean visited[] = new boolean[graph.getV()];
		//this array stores path
		int path[] = new int[graph.getV()];
		printPathsUtil(graph,src,dest,visited,path,0);
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
