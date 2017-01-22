package hackerrank.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Refer to : https://www.hackerrank.com/challenges/poisonous-plants
 *
 */
public class PoisonousPlants {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] arr = br.readLine().split(" ");
		int[] pest = new int[N];
		for(int i = 0; i < N; i++)
			pest[i] = Integer.parseInt(arr[i]);
		
		System.out.println(numberOfDaysTillAllAlive(pest,N));
	}

	private static int numberOfDaysTillAllAlive(int[] pest, int n) {
		int[] days = new int[n];
		days[0] = -1;
		int min = pest[0];
		int last_pos = -1;
		for (int i = 1; i < n; i++) {
			if (pest[i] >= pest[i - 1]) {
				if (pest[i] == pest[i - 1]) {
					days[i] = (days[i - 1] == -1) ? -1 : 1 + days[i - 1];
				} else {
					days[i] = 1;
				}
				last_pos = -1;
			} else if (pest[i] <= min) {
				days[i] = -1;
				min = pest[i];
				last_pos = -1;
			} else {
				if (last_pos == -1)
					last_pos = i - 2;

				days[i] = 1 + days[i - 1];
				int count = 0;
				while (last_pos >= 0) {
					if (days[last_pos] == -1 || (pest[last_pos] < pest[i] && days[last_pos] >= days[i] + count))
						break;
					else {
						count++;
						last_pos--;
					}
				}
//				days[i] += count-((count == 0)?0:1);
				days[i] += count;
			}
		}
		
		int min_days = Integer.MIN_VALUE;
		int num_min_value = 1;
		for (int i = 1; i < n; i++) {
			if (days[i] == -1) {
				num_min_value += 1;
				continue;
			}
			if (days[i] > min_days)
				min_days = days[i];
		}
		return (num_min_value == n)?0:min_days;
		 
	}
}
