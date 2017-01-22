package hackerrank.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Refer to : https://www.hackerrank.com/challenges/components-in-graph
 * This problem is based on disjoint sets
 * For disjoint sets ,some good reads are:
 * https://www.hackerearth.com/practice/notes/disjoint-set-union-union-find/
 * http://www.geeksforgeeks.org/union-find-algorithm-set-2-union-by-rank/
 *
 */
public class ConnectedCompMaxMinSize {

	static class Vertex {
		int index;
		int parent;
		Integer rank;
		
		public Vertex(int index,int pindex,int r) {
			this.index = index;
			this.rank = r;
			this.parent = pindex;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] arr = null;
		Map<Integer, Vertex> elements = new HashMap<>();
		Set<Vertex> leaders = new HashSet<>();
		int first,second;
		for(int i = 1; i <= N; i++) {
			arr = br.readLine().split(" ");
			first = Integer.parseInt(arr[0]);
			second = Integer.parseInt(arr[1]);
			if(elements.get(first) == null) {
				elements.put(first, new Vertex(first,first,1));
			}
			if(elements.get(second) == null) {
				elements.put(second, new Vertex(second,second,1));
			}
			doUnion(elements,leaders,first,second);
		}
		List<Vertex> leaderList = new ArrayList<>(leaders);
		Collections.sort(leaderList, new Comparator<Vertex>() {
			public int compare(Vertex v1, Vertex v2) {
				return v1.rank.compareTo(v2.rank);
			}
		});
		
		System.out.println(leaderList.get(0).rank + " " + leaderList.get(leaderList.size()-1).rank);
	}
	
	private static int find(Map<Integer, Vertex> elements,Vertex vertex) {
		if(vertex.parent != vertex.index) {
			vertex.parent = find(elements,elements.get(vertex.parent));
		}
		return vertex.parent;
	}

	private static void doUnion(Map<Integer, Vertex> elements, Set<Vertex> leaders, int first, int second) {
		Vertex f_root = elements.get(find(elements, elements.get(first)));
	    Vertex s_root = elements.get(find(elements, elements.get(second)));
		
	    /*if(f_root == s_root) {
	    	return;
	    }*/
	    
		if(f_root.rank < s_root.rank) {
			f_root.parent = s_root.index;
			s_root.rank += f_root.rank;
			leaders.add(s_root);
			leaders.remove(f_root);
		} else if(f_root.rank >= s_root.rank) {
			s_root.parent = f_root.index;
			f_root.rank += s_root.rank;
			leaders.add(f_root);
			leaders.remove(s_root);
		}
	}
	
}
