package code.practice;

/**
 * Given a string you need to print all possible strings that can be made by
 * placing spaces (zero or one) in between them.
 * 
 * Input: str[] = "ABC" Output: ABC AB C A BC A B C
 *
 */
public class ReplaceSpaceStrings {

	//i is the index in String str
	//j is index in buffer
	private static void printPatternUtil(String str, char[] buffer, int i, int j, int n) {
		
		if(i == n) {
			buffer[j] = '\0';
			System.out.println(buffer);
			return;
		}
		
		//i != n so this is a valid character ,can use space at j or place ith char at j
		
		//place char at i in j index
		buffer[j] = str.charAt(i);
		printPatternUtil(str, buffer, i+1, j+1, n);
		
		//can place space here and place char at next position
		buffer[j] = ' ';
		buffer[j+1] = str.charAt(i);
		printPatternUtil(str, buffer, i+1, j+2, n);
	}
	
	private static void printAllPattern(String str) {
		int n = str.length();
		// Buffer to hold the string containing spaces
		//if n is length of string then total possible char in buffer would be 1 + 2*(n-1) = 2n-1
	    char buffer[] = new char[2*n]; // 2n-1 characters and 1 string terminator
	    buffer[0] = str.charAt(0);       //Ist character as is
		printPatternUtil(str,buffer,1,1,n);
	}

	public static void main(String[] args) {
		String str = "ABCD";
		printAllPattern(str);
	}

	
}
