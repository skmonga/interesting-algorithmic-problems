package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Refer to : https://www.hackerearth.com/se-india-test-05-201608241526/problems
 *
 */
public class MagicalNumbers {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int i = 1; i <= T; i++) {
			int n = Integer.parseInt(br.readLine());
			if(n == 1) {
				System.out.println(1);
			} else if(n == 2) {
				System.out.println(3);
			} else {
				System.out.println(findMagicalNumber(n-3));
			}
		}
	}

	private static int findMagicalNumber(int n) {
		int num_before = 0,ext_digits = 0,diff = 0;
		for(int i = 1; ; i++) {
			int next = (int) Math.pow(2, (i+1)/2);
			if(num_before + next > n) {
				ext_digits = i;
				diff = n - num_before;
				break;
			} else {
				num_before += next;
			}
		}
		int num_combs = (int) Math.pow(2, (ext_digits+1)/2);
		int[] arr = new int[ext_digits+2];
		arr[0] = arr[arr.length-1] = 1;
		/*int ext_digits = (n == 0)?1:1+(int) (Math.log(n)/Math.log(2));
		int[] arr = new int[ext_digits+2];
		arr[0] = arr[arr.length-1] = 1;
		int num_before = 2;
		for(int i = 1; i < ext_digits; i++) {
			num_before += Math.pow(2, (i+1)/2);
		}
		int num_combs = (int) Math.pow(2, (ext_digits+1)/2);
		int diff = n - num_before + 2;*/
		/*for(int i = 1; i < arr.length/2; i++) {
			if(diff <= (num_combs/2)) {
				arr[i] = 0;
				arr[arr.length-i-1] = 0;
			} else {
				arr[i] = 1;
				arr[arr.length-i-1] = 1;
			}
			diff -= num_combs/2;
			num_combs = num_combs/2;
		}
		if(ext_digits % 2 != 0) {
			if(diff == 0) {
				arr[arr.length/2] = 0;
			} else {
				arr[arr.length/2] = 1;
			}
		}*/
		
		int index = 1;
		while(diff != 0) {
			if((diff + 1) > num_combs/2) {
				arr[index] = 1;
				arr[arr.length-index-1] = 1;
				diff -= num_combs/2;
			} else {
				arr[index] = 0;
				arr[arr.length-index-1] = 0;
			}
			num_combs /= 2;
			index++;
		}
		
		int r = 0,num = 0;
		for(int i = ext_digits + 1; i >= 0; i--) {
			if(arr[i] != 0) {
			num += Math.pow(2, arr[i]*r);
			}
			r += 1;
		}
		return num;
	}
}
