package hackerrank.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Refer to : https://www.hackerrank.com/contests/w22/challenges/box-moving
 *
 */
public class MatchingSets {

	 public static void main(String[] args) throws NumberFormatException, IOException {
			BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
			int N = Integer.parseInt(rd.readLine().trim());
			String[] arr1,arr2;
			List<Integer> list1,list2;
			list1 = new ArrayList<Integer>(N);
			list2 = new ArrayList<Integer>(N);
			arr1 = rd.readLine().split(" ");
			arr2 = rd.readLine().split(" ");
			for(int i = 0; i < N; i++) {
				list1.add(Integer.parseInt(arr1[i]));
				list2.add(Integer.parseInt(arr2[i]));
			}
			Collections.sort(list1);
			Collections.sort(list2);
			
			System.out.println(numOperationsToMakeSame(list1.toArray(),list2.toArray(),N));
		}

		private static int numOperationsToMakeSame(Object[] arr1, Object[] arr2, int n) {
			int num_operations = 0;
			for(int i = 0; i < n-1; i++) {
				int cur_op = 0;
				cur_op = Math.abs((Integer)arr1[i] - (Integer)arr2[i]);
				num_operations += cur_op;
				if((Integer)arr1[i] > (Integer)arr2[i]) {
					//need to decrease this element 
					if(!checkAndIncrement(arr1,arr2,n,i+1,cur_op))
						return -1;
				} else if((Integer)arr1[i] < (Integer)arr2[i]) {
					//need to increase this element 
					if(!checkAndDecrement(arr1,arr2,n,i+1,cur_op))
						return -1;
				}
			}
			return num_operations;
		}

		private static boolean checkAndDecrement(Object[] arr1, Object[] arr2,
				int n, int start_index, int cur_op) {
			for(int i = start_index; i < n && cur_op != 0; i++) {
				if((Integer)arr1[i] > (Integer)arr2[i]) {
					int diff = (Integer)arr1[i] - (Integer)arr2[i];
					if(diff <= cur_op) {
						arr1[i] = arr2[i];
						cur_op -= diff;
					} else {
						arr1[i] = (Integer)arr1[i] - (Integer)cur_op;
						cur_op = 0;
					}
				}
			}
			return (cur_op == 0);
		}

		private static boolean checkAndIncrement(Object[] arr1, Object[] arr2,
				int n,int start_index, int cur_op) {
			for(int i = start_index; i < n && cur_op != 0; i++) {
				if((Integer)arr1[i] < (Integer)arr2[i]) {
					int diff = (Integer)arr2[i] - (Integer)arr1[i];
					if(diff <= cur_op) {
						arr1[i] = arr2[i];
						cur_op -= diff;
					} else {
						arr1[i] = (Integer)arr1[i] + (Integer)cur_op;
						cur_op = 0;
					}
				}
			}
			return (cur_op == 0);
		}

}
