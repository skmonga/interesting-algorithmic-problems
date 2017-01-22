package hackerrank.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CountYourProgressions {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		for(int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
		int result = 0,temp;
		int[] sum;
		int constant = 1000000009,cur;
		for(int diff = -99; diff < 100; diff++) {
			sum = new int[101];
			cur = 0;
			for(int i = 0; i < n; i++) {
				temp = 0;
				if(arr[i] - diff >= 0 && arr[i] - diff < 100)
					temp = sum[arr[i] - diff]%constant;
				++temp;
				sum[arr[i]] = (sum[arr[i]] + temp)%constant;
				cur = (cur + temp)%constant;
			}
			result = (result + cur)%constant;
			result = (result - n)%constant;
		}
		result = (result + 1 + n)%constant;
		System.out.println(result);
	}
}
