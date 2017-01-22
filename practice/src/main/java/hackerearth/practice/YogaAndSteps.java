package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class YogaAndSteps {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] arr = br.readLine().split(" ");
		int[] num = new int[N];
		for(int i = 0; i < N; i++)
			num[i] = Integer.parseInt(arr[i]);
		
		System.out.println(minStepsToSort(num,N));
	}

	private static int minStepsToSort(int[] num, int n) {
		int min_steps = Integer.MAX_VALUE;
		int min_steps_inc_sort = 0;
		int i,j;
		for(i = 1; i < n; i++) {
			
		}
		return min_steps;
	}
}
