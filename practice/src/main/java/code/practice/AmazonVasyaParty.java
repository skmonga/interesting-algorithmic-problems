package code.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AmazonVasyaParty {

	public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr;
        String line = br.readLine();
        arr = line.split(" ");
        int N = Integer.parseInt(arr[0]);
        int M = Integer.parseInt(arr[1]);
        
        //knowledge levels
        int[] knowledge = new int[N];
        arr = br.readLine().split(" ");
        for(int i = 0; i < N; i++)
        	knowledge[i] = Integer.parseInt(arr[i]);
        
        //elements are numbered from 1 to N
        //every element has a map which contains the group to which it belongs
        //every group has a list of all associated elements
        Map<Integer, Integer> elem_to_group = new HashMap<>();
        Map<Integer,List<Integer>> group_to_elem = new HashMap<>();
        int u,v,group_num = 1;
        for(int i = 1; i <= M; i++) {
        	arr = br.readLine().split(" ");
        	u = Integer.parseInt(arr[0]);
        	v = Integer.parseInt(arr[1]);
        	Integer group_u = elem_to_group.get(u);
        	Integer group_v = elem_to_group.get(v);
        	if(group_u == null && group_v == null) {
        		elem_to_group.put(u, group_num);
        		elem_to_group.put(v,group_num);
        		List<Integer> new_list = new ArrayList<>();
        		new_list.add(u);
        		new_list.add(v);
        		group_to_elem.put(group_num, new_list);
        		group_num += 1;
        	} else if(group_u == null || group_v == null) {
        		if(group_u == null) {
        			elem_to_group.put(u, group_v);
        			List<Integer> elems = group_to_elem.get(group_v);
        			elems.add(u);
        		} else {
        			elem_to_group.put(v, group_u);
        			List<Integer> elems = group_to_elem.get(group_u);
        			elems.add(v);
        		}
        	} else {
        		elem_to_group.put(v, group_u);
        		List<Integer> list_u = group_to_elem.get(group_u);
        		List<Integer> list_v = group_to_elem.get(group_v);
        		list_u.addAll(list_v);
        		list_v.removeAll(list_v);
        		group_to_elem.remove(group_v);
        	}
        }
        
        Iterator<Entry<Integer, List<Integer>>> iterator = group_to_elem.entrySet().iterator();
        double result = 1;
        double constant = (Math.pow(10, 9) + 7);
        while(iterator.hasNext()) {
        	Entry<Integer, List<Integer>> entry = iterator.next();
        	int count_max_knowledge = findMaxKnowledgeCount(entry,knowledge);
        	result = (result*count_max_knowledge)%constant;
        }
        
        
        System.out.println(result);
       
    }

	private static int findMaxKnowledgeCount(Entry<Integer, List<Integer>> entry,int[] knowledge) {
		List<Integer> elements = entry.getValue();
		int max_know = Integer.MIN_VALUE;
		Iterator<Integer> it = elements.iterator();
		while(it.hasNext()) {
			Integer value = knowledge[it.next()-1];
			if(value > max_know)
				max_know = value;
		}
		
		it = null;
		it = elements.iterator();
		int count = 0;
		while(it.hasNext()) {
			Integer value = knowledge[it.next()-1];
			if(value == max_know)
				count += 1;
		}
		return count;
	}
}
