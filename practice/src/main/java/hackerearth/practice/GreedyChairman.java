package hackerearth.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Refer to : https://www.hackerearth.com/august-clash-16/algorithm/greedy-
 * chairmanaugclash/
 *
 */
public class GreedyChairman {
	
	private static int[] fact = new int[200001];

	static {
		int constant = 1000000007;
		fact[0] = 1;
		fact[1] = 1;
		for(int i = 2; i < 200001; i++) {
			fact[i] = (fact[i-1]*i)%constant;
		}
	}

	static class KeyClass {

		int start;
		int end;

		public KeyClass(int s, int e) {
			this.start = s;
			this.end = e;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + end;
			result = prime * result + start;
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
			KeyClass other = (KeyClass) obj;
			if (end != other.end)
				return false;
			if (start != other.start)
				return false;
			return true;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, M;
		String[] arr = br.readLine().split(" ");
		N = Integer.parseInt(arr[0]);
		M = Integer.parseInt(arr[1]);
		int[] salary = new int[N];
		arr = br.readLine().split(" ");
		for (int i = 0; i < N; i++)
			salary[i] = Integer.parseInt(arr[i]);

		Map<KeyClass, TreeMap<Integer, Integer>> map = processInput(salary, N);
		KeyClass input = null;
		int l, r, k;
		for (int i = 1; i <= M; i++) {
			arr = br.readLine().split(" ");
			l = Integer.parseInt(arr[0]);
			r = Integer.parseInt(arr[1]);
			k = Integer.parseInt(arr[2]);
			input = new KeyClass(l-1, r-1);
			System.out.println(numWays(input, k, map));
		}
	}

	private static long numWays(KeyClass input, int k, Map<KeyClass, TreeMap<Integer, Integer>> map) {
		long num_ways = 0;
		long constant = 1000000007;
		int count = 0;
		Iterator<Entry<Integer, Integer>> it = map.get(input).entrySet().iterator();
		while (it.hasNext()) {
			Entry<Integer, Integer> next = it.next();
			if (count + next.getValue() < k) {
				count += next.getValue();
			} else {
				return numCombinations(next.getValue(), k - count, constant);
			}
		}

		return num_ways;
	}

	public static long fact(int num) {
		return fact[num];
	}

	private static long numCombinations(int n, int r,long constant) {
		if(n == r)
			return 1;
		return ((fact(n) / (fact(n - r))) * fact(r)) % constant;
	}

	private static Map<KeyClass, TreeMap<Integer, Integer>> processInput(int[] salary, int n) {
		Map<KeyClass, TreeMap<Integer, Integer>> map = new HashMap<>();
		TreeMap<Integer, Integer> valueMap = null;
		for (int len = 1; len <= n; len++) {
			for (int i = 0; i <= n - len; i++) {
				int j = i+len-1;
				valueMap = new TreeMap<>();
				if(i != j) {
					valueMap.putAll(map.get(new KeyClass(i, j-1)));
				}
				valueMap.put(salary[j], valueMap.get(salary[j]) == null ? 1 : valueMap.get(salary[j]) + 1);
				map.put(new KeyClass(i, i+len-1), valueMap);
			}
		}
		return map;
	}
}
