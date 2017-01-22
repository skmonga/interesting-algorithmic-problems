package hackerrank.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Refer to :
 * https://www.hackerrank.com/contests/w22/challenges/number-of-sequences
 *
 */
public class NumberOfSequences {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int[] num = new int[N];
		String[] arr = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(arr[i]);
		}

		System.out.println(countNiceSequences(num, N));
	}

	private static long countNiceSequences(int[] num, int n) {
		long num_seq = 1;
		long constant = 1000000007;
		int[] possible = new int[n];
		Arrays.fill(possible, -1);
		Map<Integer, Set<Integer>> map = new HashMap<>();
		// first element will always be a 0
		possible[0] = 1;
		// so we start from second element (index 1 in array)
		for (int i = 1; i < n; i++) {
			int step = i + 1;
			boolean checkDependents = false;
			if (num[i] == -1) {
				for (int j = 2 * i + 1; j < n; j += step) {
					if (num[j] != -1) {
						// since dependent is not -1,we have to assign
						// a fixed value to current element at index i
						num[i] = num[j] % step;
						possible[i] = 1;
						checkDependents = true;
						break;
					}
				}
			} else {
				possible[i] = 1;
			}

			if (num[i] == -1) {
				// was -1 and remained -1
				possible[i] = step;
			} else if (checkDependents) {
				// was -1 but changed to some non negative integer
				// so change all dependents which are -1 themselves
				for (int j = 2 * i + 1; j < n; j += step) {
					if (num[j] == -1) {
						if (map.get(j) == null) {
							map.put(j, new HashSet<Integer>());
							int k = 0;
							while (num[i] + k * step <= j) {
								map.get(j).add(num[i] + k * step);
								k++;
							}
						} else {
							// create set of new values
							// keep only those values which are common to both
							Set<Integer> new_set = new HashSet<>();
							int k = 0;
							while (num[i] + k * step <= j) {
								new_set.add(num[i] + k * step);
								k++;
							}
							Iterator<Integer> it = map.get(j).iterator();
							while (it.hasNext()) {
								int it_val = it.next();
								if (!new_set.contains(it_val))
									it.remove();
							}
						}
					}
				}
			}
		}

		for (int i = 1; i < n; i++) {
			if (possible[i] == -1)
				possible[i] = map.get(i).size();
		}

		for (int i = 1; i < n; i++)
			num_seq = ((num_seq % constant) * (possible[i] % constant)) % constant;

		return num_seq;
	}
}
