package code.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Refer to : https://www.hackerrank.com/challenges/hyper-strings
 *
 */
public class HyperStrings {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		String[] arr = line.split(" ");
		int N = Integer.parseInt(arr[0]);
		int M = Integer.parseInt(arr[1]);
		arr = new String[N];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine();
		}
		Map<Integer, List<String>> map = new HashMap<>();
		populateLengthStrings(map, arr, N);

		// 1 length strings are all unique
		// from 2 to 10 length strings, we check if this can be produced
		// using smaller lengths

		// count[i] stores the number of strings of length i+1
		int[] count = new int[10];

		// now for 2-10 length ,check if this string can be produced from
		// smaller parts
		for (int len = 2; len <= 10; len++) {
			removeDuplicates(len, map);
		}

		for (int i = 0; i < 10; i++) {
			if (map.get(i + 1) == null) {
				count[i] = 0;
			} else {
				count[i] = map.get(i + 1).size();
			}
		}

		int constant = 1000000007;
		long[] numWays = new long[M + 1];
		numWays[0] = 1; // the empty string
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= i && j <= 10; j++) {
				numWays[i] = (numWays[i]%constant + ((count[j - 1] * (numWays[i - j]%constant))%constant))%constant;
			}
		}

		long total = 0;
		for (int i = 0; i <= M; i++) {
			total = (total%constant + numWays[i]%constant)%constant;
		}

		System.out.println(total);

	}

	/**
	 * @param len
	 *            the current length for which we are removing duplicates
	 * @param map
	 *            this method will remove the strings of length len which can be
	 *            created using string of length less than len
	 */
	private static void removeDuplicates(int len, Map<Integer, List<String>> map) {
		if (map.get(len) == null) // no strings of length len
			return;
		List<String> strings = map.get(len);
		Iterator<String> ite = strings.iterator();
		while(ite.hasNext()) {
			String str = ite.next();
			if (possibleFromSmallerLength(str, len, map))
				ite.remove();
		}
	}

	private static boolean possibleFromSmallerLength(String str, int len, Map<Integer, List<String>> map) {
		return possibleFromSmallerLengthUtil(str, len, map, 0);
	}

	private static boolean possibleFromSmallerLengthUtil(String str, int len, Map<Integer, List<String>> map,
			int index) {
		if (index == len)
			return true;

		List<String> strings;
		//here i is the length that we are considering for possible checks
		for (int i = 1; i <= len - index; i++) {

			if(i == len || map.get(i) == null)
				continue;
			strings = map.get(i);
			for(String s : strings) {
				//from index to index+i-1
				if(s.equals(str.substring(index, index+i)) && 
						possibleFromSmallerLengthUtil(str, len, map, index+i)) {
					return true;
				}		
			}
		}
		return false;
	}

	private static void populateLengthStrings(Map<Integer, List<String>> map, String[] arr, int n) {
		int len = 0;
		for (int i = 0; i < n; i++) {
			len = arr[i].trim().length();
			if (map.get(len) == null) {
				map.put(len, new ArrayList<String>());
			}
			map.get(len).add(arr[i]);
		}
	}

}
