package hackerrank.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class W23Strings {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int m = Integer.parseInt(br.readLine());
//        int len = s.length();
//        Map<Character, List<Integer>> charIndexes = processInput(s,len);
//        int min_len_repeat_pattern = findMinLengthRepeat(charIndexes,len);
        int constant = 1000000007;
//        System.out.println((m/min_len_repeat_pattern)%constant);
        String repeated = s.replaceAll("(.+?)\\1+", "$1");
        int repeated_length = repeated.length();
        System.out.println((m / repeated_length)%constant);
    }

    private static int findMinLengthRepeat(
            Map<Character, List<Integer>> charIndexes, int len) {
        Iterator<Entry<Character, List<Integer>>> iterator = charIndexes.entrySet().iterator();
        int min = Integer.MAX_VALUE;
        char min_times_char = 0;
        while(iterator.hasNext()) {
            Entry<Character, List<Integer>> next = iterator.next();
            int size = next.getValue().size();
            if(size < min) {
                min = size;
                min_times_char = next.getKey();
            }
        }
        if(min == 1)
            return len;
        if(min == len)
            return 1;
        
        List<Integer> min_list = charIndexes.get(min_times_char);
        int size = min_list.size();
        int gap = 1;
        while(gap <= size/2) {
        	if(size % gap != 0)
        		continue;
        	boolean isValidDiff = true;
        	int diff = min_list.get(gap) - min_list.get(0);
        	int space = size/gap;
        	for(int i = 0; i < gap; i++) {
        		boolean breakNow = false;
    			for(int j = i; j < size-gap; j += gap) {
    				if(min_list.get(j+gap) - min_list.get(j) != diff) {
    					breakNow = true;
    					isValidDiff = false;
    					break;
    				}
    			}
    			if(breakNow)
    				break;
    		}
        	if(isValidDiff && checkDifferenceValidity(charIndexes,size/gap,diff,min_times_char))
        		return (len/space);
        	
        	gap++;
        }
        return len;
    }

    private static boolean checkDifferenceValidity(Map<Character, List<Integer>> charIndexes, int gap, int diff,
			char min_times_char) {
    	Iterator<Entry<Character, List<Integer>>> iterator = charIndexes.entrySet().iterator();
    	while(iterator.hasNext()) {
    		Entry<Character, List<Integer>> next = iterator.next();
    		if(next.getKey() == min_times_char)
    			continue;
    		List<Integer> list = next.getValue();
    		int size = list.size();
    		int space = size/gap;
    		if(size % gap != 0)
    			return false;
    		for(int i = 0; i < space; i++) {
    			for(int j = i; j < size-space; j += space) {
    				if(list.get(j+space) - list.get(j) != diff)
    					return false;
    			}
    		}
    	}
		return true;
	}

	private static Map<Character, List<Integer>> processInput(String s,int len) {
        Map<Character, List<Integer>> charIndexes = new HashMap<>();
        for(int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if(charIndexes.get(c) == null)
                charIndexes.put((Character)c, new ArrayList<Integer>());
            charIndexes.get(c).add(i);
        }
        return charIndexes;
    }
    
}

