package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SafeNetwork {

	static class Graph
	{
	    private int V;   // No. of vertices
	    
		// Array  of lists for Adjacency List Representation
	    private LinkedList<Integer> adj[];
	    
	    private int degree[];
	    
	    public int getV() {
			return V;
		}

		public LinkedList<Integer>[] getAdj() {
			return adj;
		}
	 
	    // Constructor
	    public Graph(int v)
	    {
	        V = v;
	        adj = new LinkedList[v];
	        degree = new int[v];
	        for (int i=0; i<v; ++i) {
	            adj[i] = new LinkedList();
	            degree[i] = 0;
	        }
	    }
	 
	    //Function to add an edge into the graph
	    void addEdge(int u, int v)
	    {
	        adj[u].add(v);
	        adj[v].add(u);
	        degree[u] += 1;
	        degree[v] += 1;
	    }
	 
	    // A function used by DFS
	    void DFSUtil(int v,boolean visited[])
	    {
	        // Mark the current node as visited and print it
	        visited[v] = true;
	 
	        // Recur for all the vertices adjacent to this vertex
	        Iterator<Integer> i = adj[v].listIterator();
	        while (i.hasNext())
	        {
	            int n = i.next();
	            if (!visited[n])
	                DFSUtil(n,visited);
	        }
	    }
	 
		public boolean isConnected() {
			boolean visited[] = new boolean[V];
			DFSUtil(0, visited);
			for(int i = 0; i < V; i++) {
				if(!visited[i]) {
					return false;
				}
			}
			return true;	
		}
		
		public boolean isSafe() {
			boolean visited[] = new boolean[V];
			for(int i = 0; i < V; i++) {
				if(degree[i] == 1)
					continue;
				if(!checkForSafety(i,visited))
					return false;
			}
			return true;
		}

		private boolean checkForSafety(int v,boolean[] visited) {
			visited[v] = true;
			Iterator<Integer> it = adj[v].listIterator();
			while(it.hasNext()) {
				int n = it.next();
				if(!visited[v] && (degree[n] == 1 || !checkForSafety(n,visited)))
					return false;
			}
			visited[v] = false;
			return true;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr = br.readLine().split(" ");
		int N = Integer.parseInt(arr[0]);
		int M = Integer.parseInt(arr[1]);
		Graph graph = new Graph(N);
		for(int i = 1; i <= M; i++) {
			arr = br.readLine().split(" ");
			int u = Integer.parseInt(arr[0]);
			int v = Integer.parseInt(arr[1]);
			graph.addEdge(u-1, v-1);
		}
		if(graph.isConnected() && graph.isSafe()) {
			System.out.println("Safe");
		} else {
			System.out.println("Unsafe");
		}
	}
}
