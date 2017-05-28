package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class WordRank {

	private static final long[] fact = new long[21];
	
	static {
		fact[0] = 1;
		fact[1] = 1;
		for(int i = 2; i <= 20; i++) {
			fact[i] = i * fact[i-1];
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		String[] arr = null;
		String str = null;
		long pos = 0;
		for(int i = 1; i <= T; i++) {
			arr = br.readLine().split(" ");
			str = arr[0];
			pos = Long.parseLong(arr[1]);
			char[] chars = str.toCharArray();
			Arrays.sort(chars);
			System.out.println(permutation(chars,pos-1));
		}
	}

	private static String permutation(char[] chars, long pos) {
		//assume we have no dups initially
		int cur_pos = 0;
		int length = chars.length;
		char[] result = new char[length];
		while(pos > 0) {
			int idx = (int) (pos/(fact[length-1]));
			result[cur_pos] = chars[idx];
			for(int j = idx; j < length-1; j++)
				chars[j] = chars[j+1];
			pos -= idx * fact[length-1];
			cur_pos++;
			length--;
		}
		
		for(int i = cur_pos; i < result.length; i++) {
			result[i] = chars[i-cur_pos];
		}
		
		return String.valueOf(result);
	}
}
