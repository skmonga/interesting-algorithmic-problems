package code.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class DivisiblityBy7 {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine().trim();
		int Q = Integer.parseInt(br.readLine());
		String[] arr = null;
		int left, right;
		Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
		populateDP(line, map);
		for (int i = 1; i <= Q; i++) {
			arr = br.readLine().split(" ");
			left = Integer.parseInt(arr[0]);
			right = Integer.parseInt(arr[1]);
			if (checkDivisibilityBy7(left, right, map))
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}

	private static void populateDP(String line, Map<Integer, Map<Integer, Integer>> map) {
		Map<Integer, Integer> current = null;
		for (int i = 0; i < line.length(); i++) {
			current = new HashMap<Integer, Integer>();
			map.put(i, current);
			for (int j = i; j < line.length(); j++) {
				int val = ((((j > i) ? map.get(i).get(j-1) : 0) * 10) % 7
						+ (Integer.parseInt(String.valueOf(line.charAt(j)))) % 7) % 7;
				current.put(j, val);
			}
		}
	}

	private static boolean checkDivisibilityBy7(int left, int right, Map<Integer, Map<Integer, Integer>> map) {
		Map<Integer, Integer> thisMap = map.get(left - 1);
		return thisMap.get(right - 1) == 0;
	}

}
