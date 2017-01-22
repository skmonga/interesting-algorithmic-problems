package hackerrank.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Refer to : https://www.hackerrank.com/contests/w22/challenges/polygon-making
 *
 */
public class PolygonMaking {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		List<Integer> list = new ArrayList<>();
		String[] arr = br.readLine().split(" ");
		for(int i = 0; i < n; i++)
			list.add(Integer.parseInt(arr[i]));
		
		Collections.sort(list);
		if(list.size() <= 2)
			System.out.println(0);
		int num_cuts = 0;
		int max_index = list.size() -1;
		int min = list.get(0), second_min = list.get(1);
		int max = list.get(max_index);
		while(min + second_min <= max) {
			num_cuts += 1;
			max_index--;
			max = list.get(max_index);
		}
		System.out.println(num_cuts);
	}

	private static int cutLengthForCurrentValues(int min, int second_min, int max) {

		int max_length_after_cut = 0;
		//max can be broken into smaller pieces
		for(int len = min+second_min-1; len > 0;len--) {
			int rest_part = max - len;
			if(rest_part > second_min) {
				max_length_after_cut = len;
				break;
			}
		}
		return max_length_after_cut;
	}
}
