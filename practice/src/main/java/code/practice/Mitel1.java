package code.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Mitel1 {

	
	public static void main(String args[] ) throws Exception {
	       

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int Q = Integer.parseInt(line);
        String[] arr;
        int N = 0,L = 0,constant = 1000000007;
        int[] dpTable = new int[26];
        fillTable(dpTable,constant);
        while((line=br.readLine()) != null) {
        	arr = line.split(" ");
        	N = Integer.parseInt(arr[0]);
        	L = Integer.parseInt(arr[1]);
        	int numValid = validStrings(N,L,constant);
        	System.out.println(numValid);
        }  

    }

	private static void fillTable(int[] dpTable, int constant) {
		dpTable[0] = 0;
		int i = 1; 
		while(i < 26) {
			int j = 1,result = 1;
			while(j <= i) {
				result = (result * i)%constant;
				j++;
			}
			dpTable[i] = result;
			i++;
		}		
	}

	private static int validStrings(int N, int L, int constant) {
		if(N == 1)
			return 0;
		int result = 1;
		int i = 1; 
		while(i <= N) {
			result = (result * (N-1))%constant;
			i++;
		}
		//now i = N
		while(L - i < 0) {
			result = (result * N)%constant;
			i++;
		}
		return result;
	}

}
