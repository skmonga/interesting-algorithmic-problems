package hackerrank.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Refer to : https://www.hackerrank.com/challenges/waiter
 *
 */
public class Waiter {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr = br.readLine().split(" ");
		int N = Integer.parseInt(arr[0]);
		int Q = Integer.parseInt(arr[1]);
		int size = 50001;
		int[] primes = new int[Q];
		populatePrimes(primes,Q,size);
		List<Integer> numbers = new LinkedList<>();
		arr = br.readLine().split(" ");
		for(int i = 0; i < N; i++)
			numbers.add(Integer.parseInt(arr[i]));
		
		List<Integer> list[] = new LinkedList[Q+1];
		for(int i = 0; i <= Q; i++)
			list[i] = new LinkedList<Integer>();
		
		int index_list = 0;
		Iterator<Integer> it;
		while(true) {
			it = numbers.iterator();
			while(it.hasNext()) {
				if(primes[index_list] == 0) {
					index_list = Q-1;
					break;
				}
				int num = it.next();
				if(num % primes[index_list] == 0) {
					list[index_list].add(num);
					it.remove();
				} 
			}
			index_list++;
			if(index_list == Q) {
				it = numbers.iterator();
				while(it.hasNext()) {
					list[index_list].add(it.next());
				}
				break;
			}
		}
		
		for(int i = 0; i <= Q; i++) {
			if(i != Q) {
				if(i % 2 != 0) {
					Collections.reverse(list[i]);
				}
				it = list[i].iterator();
			} else {
				if(i % 2 == 0) {
					Collections.reverse(list[i]);
				}
				it = list[i].iterator();
			}
			while(it.hasNext()) {
				System.out.println(it.next());
			}
		}
		
		
	}

	
	
	private static void populatePrimes(int[] primes, int q,int size) {

		boolean[] isPrime = new boolean[size];
		for(int i = 2; i < size; i++)
			isPrime[i] = true;
		
		int index_prime = 0;
		for(int i = 2; i*i < size; i++) {
			if(isPrime[i]) {
				primes[index_prime++] = i;
				if(index_prime == q)
					break;
				for(int j = 2*i ; j < size; j += i) {
					isPrime[j] = false;
				}
			}
		}
	}
}
