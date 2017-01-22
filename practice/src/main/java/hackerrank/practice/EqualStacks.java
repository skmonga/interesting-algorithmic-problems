package hackerrank.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Refer to : https://www.hackerrank.com/challenges/equal-stacks
 *
 */
public class EqualStacks {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n1,n2,n3;
		String[] arr;
		arr = br.readLine().split(" ");
		n1 = Integer.parseInt(arr[0]);
		n2 = Integer.parseInt(arr[1]);
		n3 = Integer.parseInt(arr[2]);
		
		LinkedList<Integer> list1,list2,list3;
		list1 = new LinkedList<>(); list2 = new LinkedList<>(); list3 = new LinkedList<>();
		int cur_ht1 = intializeList(list1,n1,br.readLine().split(" "));
		int cur_ht2 = intializeList(list2,n2,br.readLine().split(" "));
		int cur_ht3 = intializeList(list3,n3,br.readLine().split(" "));
		
		if(cur_ht1 == cur_ht2 && cur_ht2 == cur_ht3) {
			System.out.println(cur_ht1);
		} else {
			System.out.println(findOptimalHeight(list1,cur_ht1,list2,cur_ht2,list3,cur_ht3));
		}
	}
	
	static int min(int a,int b) {
		return a < b?a:b;
	}
	
	static int min(int a,int b,int c) {
		return min(a,min(b,c));
	}

	private static int findOptimalHeight(LinkedList<Integer> list1, int cur_ht1, LinkedList<Integer> list2, int cur_ht2,
			LinkedList<Integer> list3, int cur_ht3) {
		while (true) {
			if (cur_ht1 == cur_ht2 && cur_ht2 == cur_ht3) {
				return cur_ht1;
			} else {
				int min_ht = min(cur_ht1, cur_ht2, cur_ht3);
				Pair pair = null;
				if (min_ht == cur_ht1) {
					pair = updateLists(list2, cur_ht2, list3, cur_ht3, min_ht);
					cur_ht2 = pair.first;
					cur_ht3 = pair.second;
				} else if (min_ht == cur_ht2) {
					pair = updateLists(list1, cur_ht1, list3, cur_ht3, min_ht);
					cur_ht1 = pair.first;
					cur_ht3 = pair.second;
				} else {
					pair = updateLists(list1, cur_ht1, list2, cur_ht2, min_ht);
					cur_ht1 = pair.first;
					cur_ht2 = pair.second;
				}
			}
		}
	}
	
	static class Pair {
		Integer first;
		Integer second;
		
		public Pair(Integer f,Integer s) {
			this.first = f;
			this.second = s;
		}
	}

	private static Pair updateLists(LinkedList<Integer> list1, int cur_ht1, LinkedList<Integer> list2, int cur_ht2, int min_ht) {

		int cur_ht = 0;
		while (cur_ht1 > min_ht) {
			cur_ht = list1.peek();
			list1.removeFirst();
			cur_ht1 -= cur_ht;
		}
		while (cur_ht2 > min_ht) {
			cur_ht = list2.peek();
			list2.removeFirst();
			cur_ht2 -= cur_ht;
		}
		return new Pair(cur_ht1, cur_ht2);
	}

	private static int intializeList(List<Integer> list, int size,String[] split) {
		int ht = 0,cur_ht = 0;
		for(int i = 0; i < size; i++) {
			ht = Integer.parseInt(split[i]);
			list.add(ht);
			cur_ht += ht;
		}
		return cur_ht;
	}
}
