package code.practice;

import java.util.Vector;

/**
 * Refer to :
 * http://www.geeksforgeeks.org/online-algorithm-for-checking-palindrome-in-a-
 * stream/
 *
 * Online algorithm for checking palindrome in a stream.
 * Given a stream of
 * characters (characters are received one by one), write a function that prints
 * ‘Yes’ if a character makes the complete string palindrome, else prints ‘No’.
 */
public class PalindromeCheckStream {

	private static void checkPalindrome(String str) {
		char[] stream = str.toCharArray();
		int len = stream.length;
		boolean[][] isPalindrome = new boolean[len][len];
		for(int i = 0; i < len; i++)
			isPalindrome[i][i] = true;
		
		//now consuming a single character
		for(int i = 1; i < len; i++) {
			if(stream[i] == stream[0]) {
				
			} else {
				
			}
		}
	}
	
	public static void main(String[] args) {
		String str = "abcba";   //assume char received one by one
//		checkPalindrome(str);
		System.out.println(str.length());
	}

}