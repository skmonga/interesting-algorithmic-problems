package hackerrank.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Refer to :
 * https://www.hackerrank.com/contests/morgan-stanley-2016/challenges/jesse-and-
 * profit
 *
 */
public class JesseAndProfit {

	static class Pair {
		Integer buy;
		Integer sell;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr = null;
		int N,D;
		arr = br.readLine().split(" ");
		N = Integer.parseInt(arr[0]);
		D = Integer.parseInt(arr[1]);
		int[] prices = new int[N];
		Map<Integer, List<Integer>> map = new HashMap<>();
		arr = br.readLine().split(" ");
		Integer price = null;
		List<Integer> priceDayList = null;
		for(int i = 1; i <= N; i++) {
			price = Integer.parseInt(arr[i-1]);
			prices[i-1] = price;
			if(map.get(price) == null) {
				map.put(price, new LinkedList<Integer>());
			}
			priceDayList = map.get(price);
			priceDayList.add(i);
		}
		Integer profit = null;
		Pair pair = null;
		for(int i = 1; i <= D; i++) {
			profit = Integer.parseInt(br.readLine().trim());
			pair = possibleToMakeProfit(prices,map,profit);
			if(pair != null) {
				System.out.println(pair.buy + " " + pair.sell);
			} else {
				System.out.println(-1);
			}
		}
	}

	private static Pair possibleToMakeProfit(int[] prices, Map<Integer, List<Integer>> map, Integer profit) {
		Pair pair = null;
		for(int i = 0; i < prices.length-1; i++) {
			int cur_price = prices[i];
			if(map.get(cur_price+profit) == null)
				continue;
			Iterator<Integer> it = map.get(cur_price+profit).iterator();
			while(it.hasNext()) {
				int profit_day = it.next();
				if(profit_day > i+1) {
					if(pair == null || ((profit_day - i -1) < (pair.sell - pair.buy))) {
						if(pair == null)
							pair = new Pair();
						pair.buy = i+1;
						pair.sell = profit_day;
					} 
				}
			}
		}
		return pair;
	}
}
