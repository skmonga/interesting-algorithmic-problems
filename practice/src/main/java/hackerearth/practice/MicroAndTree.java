package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Refer to : https://www.hackerearth.com/moonfrog-backend-hiring-challenge/problems/
 *
 */
public class MicroAndTree {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] degree = new int[N+1];
		List<Integer> adjacent[] = new ArrayList[N+1];
		for(int i = 1; i <= N; i++)
			adjacent[i] = new ArrayList<>();
		String[] arr;
		for(int i = 1; i < N; i++) {
			arr = br.readLine().split(" ");
			int x = Integer.parseInt(arr[0]);
			int y = Integer.parseInt(arr[1]);
			degree[x] += 1;
			degree[y] += 1;
			adjacent[x].add(y);
			adjacent[y].add(x);
		}
		if(checkIfValid(degree,N+1)) {
			System.out.println(0);
		} else {
			int num_ops = numberOfOperations(degree,adjacent,N+1);
			System.out.println(num_ops);
		}
			
	}

	private static int numberOfOperations(int[] degree, List<Integer>[] adjacent, int size) {
		int num_ops = 0;
		for(int i = 1; i < size; i++) {
			if(degree[i] != 1 && degree[i] != 2) {
				Iterator<Integer> it = adjacent[i].iterator();
				while(true && it.hasNext()) {
					int dest_index = it.next();
					if(degree[dest_index] == 1 || degree[dest_index] == 2) {
						num_ops += 1;
						it.remove();
						degree[i] -= 1;
						adjacent[dest_index].remove((Integer) i);
						attachToLeafNode(degree,adjacent,size,dest_index,i);
						if(degree[i] == 2)
							break;
					}
				}
			}
		}
		return num_ops;
	}

	private static void attachToLeafNode(int[] degree, List<Integer>[] adjacent, int size, int dest_index, int remove) {

		for(int i = 1; i < size; i++) {
			if(degree[i] != 1 || i == remove || i == dest_index)
				continue;
			degree[i] += 1;
			adjacent[i].add(dest_index);
			adjacent[dest_index].add(i);
			break;
		}
	}

	private static boolean checkIfValid(int[] degree, int size) {
		for(int i = 1; i < size; i++) {
			if(degree[i] != 1 && degree[i] != 2)
				return false;
		}
		return true;
	}
}
