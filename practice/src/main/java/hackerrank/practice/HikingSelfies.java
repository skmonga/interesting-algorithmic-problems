package hackerrank.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Refer to : https://www.hackerrank.com/contests/walmart-codesprint-algo/challenges/emma-and-her-camera
 *
 */
public class HikingSelfies {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int x = Integer.parseInt(br.readLine());
		System.out.println((int)Math.abs(Math.pow(2, n) - 1 - x));
	}
}
