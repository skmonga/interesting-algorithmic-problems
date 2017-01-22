package code.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StringMatching {

		public static boolean checkIfProperPassword(String actual,String pwd) {
			int m = actual.length();
			int n = pwd.length();
			
			if(n != 2*m)
			return false;
			
			String wouldBePassword;
			StringBuilder builder;
			for(int i = 1; i <= m; i++) {
				//take part of length i and paste ahead of actual and rest at end
				builder = new StringBuilder();
				wouldBePassword = actual.substring(0,i) + actual + actual.substring(i);
				if(wouldBePassword.equals(pwd))
					return true;
			}
			
			return false;
		}
		
	    public static void main(String args[] ) throws Exception {
	       
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String line = br.readLine();
	        int N = Integer.parseInt(line);
	        String actual,pwd;
	        String[] arr;
	        boolean possible;
	        for (int i = 0; i < N; i++) {
	        	line = br.readLine();
	        	arr = line.split(" ");
	        	actual = arr[0];
	        	pwd = arr[1];
	            possible = checkIfProperPassword(actual,pwd);
	            if(possible)
	            System.out.println("Possible");
	            else 
	             System.out.println("Impossible");
	        }
	    }
	}
