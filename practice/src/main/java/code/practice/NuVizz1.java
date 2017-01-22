package code.practice;


/* IMPORTANT: Multiple classes and nested static classes are supported */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Integer;

class NuVizz1 {
    public static void main(String args[] ) throws Exception {
       
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        String day_activity;
        int[] code_streak_start = new int[N];
        int[] code_streak_end = new int[N];
        int max_streak = Integer.MIN_VALUE;
        int day_index = 0;
        while((line=br.readLine()) != null && !line.equals("TEST")) {
        	int max_this_day = findMaxCodeStreak(line,day_index,code_streak_start,code_streak_end);
        	if(max_this_day > max_streak)
        		max_streak = max_this_day;
        	
        	day_index++;
        }
        
        int longest_code_streak = Integer.MIN_VALUE,temp;
        for(int i = 1; i < N; ) {
        	temp = code_streak_end[i-1];
        	if(code_streak_start[i] == line.length()) {
        		while(code_streak_start[i] == line.length()) {
        			temp += code_streak_start[i++];
        		}
        	}
        	temp += code_streak_start[i];
        	i++;
        	if(temp > longest_code_streak)
        		longest_code_streak = temp;
        }
        
        if(longest_code_streak < max_streak)
        	longest_code_streak = max_streak;
        
        System.out.println(max_streak + " " + longest_code_streak);
 

       
    }

	private static int findMaxCodeStreak(String line, int day_index,int[] code_streak_start, int[] code_streak_end) {
		int max_streak = Integer.MIN_VALUE,i = 0;
		if(line.charAt(i) == 'C') {
			int max_start = 0;
			while(i < line.length()) {
				if(line.charAt(i++) == 'C') {
					max_start++;
				} else {
					break;
				}
			}
			code_streak_start[day_index] = max_start;
			max_streak = max_start;
			if(i == line.length()) {
				code_streak_end[day_index] = max_start;
				return max_start;
			}
		} else {
			code_streak_start[day_index] = 0;
		}
		
		for(int j = i; j < line.length(); ) {
			if(line.charAt(j) == 'C') {
				int streak = 0;
				while(j < line.length()) {
					if(line.charAt(j++) == 'C') {
						streak++;
					} else {
						break;
					}
				}
				if(streak > max_streak)
					max_streak = streak;
				if(j == line.length()) {
					code_streak_end[day_index] = streak;
				}
			} else {
				j++;
			}
		}
		
		return max_streak;
	}
}
