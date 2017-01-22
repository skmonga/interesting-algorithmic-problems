package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Refer to :
 *         https://www.hackerearth.com/practice/algorithms/graphs/minimum-
 *         spanning-tree/practice-problems/algorithm/efficient-network/
 *
 */
public class SimulateNetwork {

    static class Edge {
    	int src;
    	int dest;
    	public Edge(int src,int dest) {
    		this.src = src;
    		this.dest = dest;
    	}
    	
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + dest;
			result = prime * result + src;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Edge other = (Edge) obj;
			if (dest != other.dest)
				return false;
			if (src != other.src)
				return false;
			return true;
		}
    	
    	
    }
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        int N = Integer.parseInt(arr[0]);
        int M = Integer.parseInt(arr[1]);
        //this map will contain an edge as key and edge 
        //will contain lower index as src and upper as dest
        //value will be latency of this edge
        Map<Edge, Integer> latencies = new HashMap<>();
        int src,dest,temp,weight;
        for(int i = 1; i <= M; i++) {
        	arr = br.readLine().split(" ");
        	src = Integer.parseInt(arr[0]);
        	dest = Integer.parseInt(arr[1]);
        	weight = Integer.parseInt(arr[2]);
        	if(src == dest)
        		continue;
        	if(src > dest) {
        		temp = src;
        		src = dest;
        		dest = temp;
        	}
        	Edge current_edge = new Edge(src,dest);
        	if(latencies.get(current_edge) == null ||
        			latencies.get(current_edge) > weight) {
        		latencies.put(current_edge, weight);
        	}
        }
        //create graph from these edges
        //remove any cycle in graph formed by these edges
        
        
        int Q = Integer.parseInt(br.readLine());
        int[] new_cables;
        if(Q != 0) {
        arr = br.readLine().split(" ");
        new_cables = new int[Q];
        for(int i = 0; i < Q; i++)
        	new_cables[i] = Integer.parseInt(arr[i]);
        } else {
        	new_cables = new int[]{Integer.MAX_VALUE};
        }
        
        Arrays.sort(new_cables);
        
        List<Map.Entry<Edge, Integer>> list = new ArrayList<>(latencies.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Edge, Integer>>() {

			@Override
			public int compare(Entry<Edge, Integer> o1, Entry<Edge, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
        
        int list_index = 0,array_index = 0,total_edges = 0,list_size = list.size();
        long total_latency = 0;
        while(total_edges < N-1) {
        	total_edges++;
        	if(list_index == list_size) {
        		//take from new cables
        		total_latency += new_cables[array_index++];
        		continue;
        	}
        	if(array_index == Q) {
        		//take from existing cables
        		total_latency += list.get(list_index).getValue();
        		list_index++;
        		continue;
        	}
        	if(new_cables[array_index] < list.get(list_index).getValue()) {
        		total_latency += new_cables[array_index];
        		array_index++;
        	} else {
        		total_latency += list.get(list_index).getValue();
        		list_index++;
        	}
        }
        System.out.println(total_latency);
	}
	
}
