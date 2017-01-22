package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Refer to : https://www.hackerearth.com/rang-de-java-hiring-challenge/problems/870ec477da03408cbddcada21e66315c/
 * @author skm
 *
 */
public class DistinctPowers {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int num = 0;
		for(int i = 1; i <= T; i++) {
			num = Integer.parseInt(br.readLine());
			int smallestAddition = markSetBits(num);
			System.out.println(num + smallestAddition);
		}
	}
	
	private static int markSetBits(int num) {
		for(int i = 0; i < 32; i++) {
			if((num & (1 << i)) == 0)
				return 1<<i;
		}
		return 0;
	}

}
