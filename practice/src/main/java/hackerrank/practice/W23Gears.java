package hackerrank.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class W23Gears {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int q = Integer.parseInt(br.readLine());
		int n;
		for(int i = 1; i <= q; i++) {
			n = Integer.parseInt(br.readLine());
			if(n % 2 == 0)
				System.out.println("Yes");
			else
				System.out.println("No");
		}
	}
}
