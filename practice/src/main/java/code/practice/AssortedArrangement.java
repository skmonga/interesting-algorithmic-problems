package code.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AssortedArrangement {

	public static void main(String args[] ) throws Exception {
	       
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] arr = line.split(" ");
        int n = Integer.parseInt(arr[0]);
        int m = Integer.parseInt(arr[1]);
        
        //read colors and assign
        int[] colors = new int[m];
        line = br.readLine();
        arr = line.split(" ");
        for(int i = 0; i < m; i++)
        	colors[i] = Integer.parseInt(arr[i]);
        
        //read and assign painted colors
        int[] painted_colors = new int[n];
        line = br.readLine();
        arr = line.split(" ");
        for(int i = 0; i < n; i++)
        	painted_colors[i] = Integer.parseInt(arr[i]);
        
        //numbers we generate
        int[] numbers = new int[n];
        //next number must be greater than previous ,so min index calculated by division
        //and then check constraints
        
        int max = 0,div = 0;
        for(int i = 0; i < n; i++) {
        	if(max == 0) {
        		numbers[i] = colors[painted_colors[i] - 1];
        		numbers[i] = checkOrReassign(numbers[i],painted_colors[i],colors,m);
        		max = numbers[i] + 1;
        	} else {
        		div = max / (colors[painted_colors[i] - 1]);
        		if(max % (colors[painted_colors[i] - 1]) != 0) {
        			div += 1;
        		}
        		numbers[i] = div * colors[painted_colors[i] - 1];
        		numbers[i] = checkOrReassign(numbers[i],painted_colors[i],colors,m);
        		max = numbers[i] + 1;	
        	}
        }
        
        System.out.println(max-1);     
        
    }

	private static int checkOrReassign(int num, int painted_color, int[] colors, int m) {
		int start = painted_color + 1;
		for(int i = start; i <= m; i++) {
			if(num % (colors[i-1]) == 0) {
				int next_num = num + colors[painted_color-1];
				return checkOrReassign(next_num, painted_color, colors, m);
			}		
		}
		return num;
	}
	
}
