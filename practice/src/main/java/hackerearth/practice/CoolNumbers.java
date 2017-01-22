package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Refer to :
 * https://www.hackerearth.com/capillary-java-developers-challenge-1/problems/
 * 11813941da4842518506003ae6666bc6/
 * 
 * Refer to : https://www.hackerearth.com/submission/6734183/
 * for the best submission
 * 
 * @author skm
 *
 */
public class CoolNumbers {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		String[] arr = null;
		long l = 0, r = 0;
		for (int i = 1; i <= T; i++) {
			arr = br.readLine().split(" ");
			l = Long.parseLong(arr[0]);
			r = Long.parseLong(arr[1]);
			System.out.println(sumCoolNumbers(l, r));
		}
	}

	private static long sumCoolNumbers(long l, long r) {
		long sum = 0;
		long cool_num = 0;
		for (long i = l; i <= r; i++) {
			if (cool_num < i) {
				cool_num = nextCoolNumber(i, cool_num);
			}
			sum += cool_num;
		}
		return sum;
	}

	private static long nextCoolNumber(Long i, Long last_cool_num) {
		char[] cool_arr;
		int length = i.toString().length();
		int bool_mapping = 0;
		if (last_cool_num == 0) {
			cool_arr = new char[length];
			Arrays.fill(cool_arr, '2');
			last_cool_num = Long.parseLong(String.valueOf(cool_arr));
			if (last_cool_num >= i)
				return last_cool_num;
			bool_mapping = 0;
		} else {
			cool_arr = last_cool_num.toString().toCharArray();
			for (int j = 0; j < cool_arr.length; j++) {
				if (cool_arr[j] == '5') {
					bool_mapping += (1 << (cool_arr.length -1 - j));
				}
			}
		}

		for (int j = bool_mapping + 1; j < (1 << length); j++) {
			for (int k = 0; k < length; k++) {
				if ((j & (1 << (cool_arr.length - 1 -k))) == 0)
					cool_arr[k] = '2';
				else
					cool_arr[k] = '5';
			}
			long next_val = Long.parseLong(String.valueOf(cool_arr));
			if (next_val >= i)
				return next_val;
		}

		cool_arr = new char[length + 1];
		Arrays.fill(cool_arr, '2');
		return Long.parseLong(String.valueOf(cool_arr));
	}

}
