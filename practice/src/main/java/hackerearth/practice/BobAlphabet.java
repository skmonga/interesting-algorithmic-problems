package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Refer to : https://www.hackerearth.com/viacom-java-hiring-challenge/problems/940ed6e8e22347dab60fce6c6f92c50e/
 *
 */
public class BobAlphabet {
 
	static class Pair {
		int length;
		int currentIndex;
		
		public Pair(int l,int ci) {
			length = l;
			currentIndex = ci;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		String input;
		List<Integer> indexes[];
		for(int i = 1; i <= T; i++) {
			input = br.readLine().trim();
			indexes = new ArrayList[26];
			populateIndexes(indexes,input);
			System.out.println(minLengthChain(indexes,'a'));
		}
	}
	
	

	private static int minLengthChain(List<Integer>[] indexes, char c) {
		Iterator<Integer> it = indexes[c-'a'].iterator();
		int min = Integer.MAX_VALUE;
		Pair pair = null;
		while(it.hasNext()) {
			int idx = it.next();
		    pair = minLengthChainUtil(indexes, (char) (c+1), idx);
		    if(pair != null) {
		    	if(pair.length + pair.currentIndex - idx < min)
		    		min = pair.length + pair.currentIndex - idx;
		    }
		}
		return min;
	}



	private static Pair minLengthChainUtil(List<Integer>[] indexes,char current,int index) {
		if(current - 'a' > 25)
			return new Pair(0,0);
		Pair pair = null,returnPair = null;
		Iterator<Integer> it = indexes[current-'a'].iterator();
		while(it.hasNext()) {
			int idx = it.next();
			if(idx < index) {
				it.remove();
				continue;
			}
			pair = minLengthChainUtil(indexes, (char) (current+1), idx);
			if(pair != null) {
				if (returnPair == null) {
					if (pair.length == 0 && pair.currentIndex == 0)
						returnPair = new Pair(0, idx);
					else
						returnPair = new Pair(pair.length + pair.currentIndex - idx, idx);
				} else {
					
				}
			}
		}
		return returnPair;
	}

	private static void populateIndexes(List<Integer>[] indexes, String input) {
		char current = 0;
		int len = input.length();
		for(int i = 0; i < len; i++) {
			current = input.charAt(i);
			if(indexes[current - 'a'] == null)
				indexes[current - 'a'] = new ArrayList<>();
			int cur_index = i;
			while(i+1 < len && input.charAt(i+1) == current) {
				i++;
			}
			if(current == 'a')
				indexes[current - 'a'].add(i);
			else 
				indexes[current - 'a'].add(cur_index);
		}
	}
}
