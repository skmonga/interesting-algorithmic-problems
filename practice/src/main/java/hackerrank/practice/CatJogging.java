package hackerrank.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 * Refer to : https://www.hackerrank.com/challenges/cat-jogging
 *
 */
public class CatJogging {

    static class Graph {
        int V;
        LinkedList<Integer> adjList[];
        
        public Graph(int v) {
            this.V = v;
            adjList = new LinkedList[v+1];
            for(int i = 1; i <= v; i++)
                adjList[i] = new LinkedList<Integer>();
        }
        
        public void addEdge(int u,int w) {
            adjList[u].add(w);
            adjList[w].add(u);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        int N = Integer.parseInt(arr[0]);
        int M = Integer.parseInt(arr[1]),s1,s2;
        
        Graph g = new Graph(N);
        for(int i = 1; i <= M; i++) {
            arr = br.readLine().split(" ");
            s1 = Integer.parseInt(arr[0]);
            s2 = Integer.parseInt(arr[1]);
            g.addEdge(s1, s2);
        }
        
        System.out.println(numDistinctPaths(g,N));
    }

    private static int numDistinctPaths(Graph g, int N) {
        boolean[] visited = new boolean[N+1];
        //this set will contain order of vertices which form 4 vertices cycle.
        // eg 1-2-3-4 is cycle formed by 1,2,3,4 vertices or it can be 2-3-4-1 
        // or 3-4-1-2 or 4-1-2-3
        Set<String> unique = new HashSet<String>();
        //this contains the included elements
        int[] included = new int[4];
        for(int i = 1; i <= N-3; i++) {
            //include current element
            included[0] = i;
            visited[i] = true;
            addDistinctCycles(g,unique,included,visited,1,i);
            visited[i] = false;
        }
        return unique.size()/2;
    }

    private static void addDistinctCycles(Graph g,
            Set<String> unique, int[] included, boolean[] visited,int n_index,int src) {
		if (n_index > 3) {
			checkForUniquePaths(g,included,unique,src);
			return;
		}
        Iterator<Integer> it = g.adjList[src].iterator();
        while(it.hasNext()) {
            int n = it.next();
            if(!visited[n]) {
                included[n_index] = n;
                visited[n] = true;
                addDistinctCycles(g,unique,included,visited,n_index+1,n);
                visited[n] = false;
            }
        }
        
    }

    private static void checkForUniquePaths(Graph g, int[] included, Set<String> unique, int src) {
		Iterator<Integer> it = g.adjList[src].iterator();
		while(it.hasNext()) {
			if(it.next() == included[0]) {
				checkIfUniquePath(included, unique);
				break;
			}
		}
	}

	private static void checkIfUniquePath(int[] included, Set<String> unique) {
        String key = null,key1 = null;
        for(int i = 0; i < 4; i++) {
        	key = included[i%4]+"-"+included[(i+1)%4]+"-"+included[(i+2)%4]+"-"+included[(i+3)%4];
        	key1 = included[(i+3)%4]+"-"+included[(i+2)%4]+"-"+included[(i+1)%4]+"-"+included[i%4];
        	if(unique.contains(key) || unique.contains(key1)) {
        		return;
        	}
        }
        unique.add(key);
        unique.add(key1);
    }
}
