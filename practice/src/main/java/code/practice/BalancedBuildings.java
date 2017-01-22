package code.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BalancedBuildings {

	public static void main(String args[] ) throws Exception {
	       
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer n,bonus,minusCost,plusCost;
        String line = br.readLine();
        String[] arr = line.split(" ");
        n = Integer.parseInt(arr[0]);
        bonus = Integer.parseInt(arr[1]);	
        minusCost = Integer.parseInt(arr[2]);
        plusCost = Integer.parseInt(arr[3]);
        
        //read heights
        arr = br.readLine().split(" ");
        int[] heights = new int[n];
        for(int i = 0; i < n; i++)
        	heights[i] = Integer.parseInt(arr[i]);
        
        System.out.println(maxProfit(heights,n,bonus,minusCost,plusCost));
        
	}

	private static int maxProfit(int[] heights, int n, int bonus, int minusCost, int plusCost) {
		int[] cur_heights = new int[n];
		int min_ht = 0,max_ht = 0;
		Set<Integer> present = new HashSet<>();
		for(int i = 0; i < n; i++) {
			if(heights[i] > max_ht)
				max_ht = heights[i];
			if(heights[i] < min_ht)
				min_ht = heights[i];
			if(!present.contains(heights[i]))
				present.add(heights[i]);
		}
		return maxProfitUtil(heights,cur_heights,n,0,bonus,minusCost,plusCost,min_ht,max_ht,present);
	}

	private static int maxProfitUtil(int[] heights, int[] cur_heights, int n, int cur_index,int bonus,
			int minusCost, int plusCost, int min_ht, int max_ht,Set<Integer> present) {
		if(cur_index == n) {
			int profit = 0,diff = 0;
			for(int i = 0; i < n; i++) {
				diff = cur_heights[i] - heights[i];
				if(i >= 1 &&  cur_heights[i] == cur_heights[i-1])
					profit += bonus;
				if(diff == 0)
					continue;
				else if(diff > 0) {
					profit -= (diff*plusCost);
				} else {
					profit -= (Math.abs(diff)*minusCost);
				}
			}
			return profit;
		}
		int max_value = Integer.MIN_VALUE;
		for(int ht = min_ht; ht <= max_ht; ht++) {
			if(!present.contains(ht))
				continue;
			cur_heights[cur_index] = ht;
			int retValue = maxProfitUtil(heights, cur_heights, n, cur_index+1, 
					bonus, minusCost, plusCost, min_ht, max_ht,present);
			if(retValue > max_value)
				max_value = retValue;
		}
		return max_value;
	}
	
}
