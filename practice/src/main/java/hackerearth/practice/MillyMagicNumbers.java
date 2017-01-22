package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author skm
 * Refer to : https://www.hackerearth.com/problem/algorithm/milly-and-the-magic-numbers-22/
 * This problem uses bitmasking
 *
 */
public class MillyMagicNumbers {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] primes = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47};
		String[] arr = null;
		long l,r;
		int n;
		for(int i = 1; i <= T; i++) {
			arr = br.readLine().split(" ");
			n = Integer.parseInt(arr[0]);
			l = Long.parseLong(arr[1]);
			r = Long.parseLong(arr[2]);
			System.out.println(countMagicNumbers(primes,n,l,r));
		}
	}

	private static long countMagicNumbers(int[] primes, int n, long l, long r) {
		long count = 0;
		long range_right = r;
		long range_left = l-1;
		int count_primes = 0;
		while(count_primes < primes.length && primes[count_primes] <= n)
			count_primes++;
		int mask = 1;
		int count_included = 0;
		long mult = 1;
		while(mask < (1 << count_primes)) {
			count_included = 0;
			mult = 1;
			for(int j = 0; j < count_primes; j++) {
				if((mask & (1 << j)) != 0) {
					count_included++;
					mult *= primes[j];
				}
			}
			if(count_included % 2 != 0) {
				count += ((range_right/mult) - (range_left/mult));
			} else {
				count -= ((range_right/mult) - (range_left/mult));
			}
			mask++;
		}
		return count;
	}
}
