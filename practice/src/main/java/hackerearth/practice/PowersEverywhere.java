package hackerearth.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Refer to : https://www.hackerearth.com/altimetrik-java-hiring-challenge/problems/
 *
 */
public class PowersEverywhere {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		int Q,num;
		for(int i = 1; i <= T; i++) {
			Q = Integer.parseInt(br.readLine().trim());
			for(int j = 1; j <= Q; j++) {
				num = Integer.parseInt(br.readLine().trim());
				System.out.println(minDiffToSpecialNumberUsingLog(num));
			}
		}
	}

	private static int minDiffToSpecialNumberUsingLog(int num) {
		int min_diff = Integer.MAX_VALUE,min_diff_current;
		double num_log = Math.log(num),cur_log;
		int power;
		for(int i = 2; i <= Math.sqrt(num)+1; i++) {
			//check the first power of i which is greater than num
			cur_log = Math.log(i);
			power = (int) (num_log/cur_log);
			int val1 = (int) Math.pow(i, power);
			min_diff_current = (Math.abs(num - val1) > Math.abs(num - val1*i)) ?Math.abs(num - val1*i):Math.abs(num - val1);
			if(Math.abs(min_diff_current) < min_diff)
				min_diff = min_diff_current;
		}
		return min_diff;
	}

	private static int minDiffToSpecialNumber(int num) {
		int min_diff = Integer.MAX_VALUE,min_diff_current;
		for(int i = 2; i <= Math.sqrt(num)+1 ; i++) {
			int next_special = i;
			while(next_special < num) {
				next_special *= i;
			}
			int prev_power = (next_special/i);
			min_diff_current = ((num - prev_power) > (next_special - num)) ?(next_special-num):(num - prev_power);
			if(min_diff_current < min_diff)
				min_diff = min_diff_current;
		}
		return min_diff;
	}
}
