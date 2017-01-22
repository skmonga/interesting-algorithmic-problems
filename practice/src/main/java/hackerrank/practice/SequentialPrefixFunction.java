package hackerrank.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Refer to :
 * https://www.hackerrank.com/contests/w22/challenges/sequential-prefix-function
 *
 */
public class SequentialPrefixFunction {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		String[] arr;
		int[] lps = new int[N];
		int[] elems = new int[N];
		int length = 0; // length of array formed
		int run_length = 0; // length of previous longest prefix suffix
		for (int i = 0; i < N; i++) {
			arr = br.readLine().split(" ");
			if (arr[0].charAt(0) == '+') {
				// new element added
				int next_elem = Integer.parseInt(arr[1]);
				elems[length] = next_elem;
				if (length == 0) {
					lps[length] = 0;
				} else {
					while (true) {
						if (next_elem == elems[run_length]) {
							 run_length++;
							 lps[length] = run_length;
							 break;
						} else {
							if(run_length != 0) {
								run_length = lps[run_length-1];
							} else {
								lps[length] = 0;
								break;
							}
						}
					}
				}
				length++;
			} else {
				//last element removed
				length--;
				if(length != 0) {
				run_length = lps[length-1];
				} else {
					run_length = 0;
				}
			}
			if(length == 0)
				System.out.println(0);
			else
				System.out.println(lps[length-1]);
		}
	}
}
