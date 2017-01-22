package hackerrank.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Refer to : https://www.hackerrank.com/challenges/truck-tour
 *
 */
public class TruckTour {

	static class PPump {
		int amt;
		int distToNext;
		
		public PPump(int a,int dist) {
			this.amt = a;
			this.distToNext = dist;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] arr;
		PPump[] pumps = new PPump[N];
		for(int i = 0; i < N; i++) {
			arr = br.readLine().split(" ");
			pumps[i] = new PPump(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
		}
		System.out.println(findSmallestIndexForTour(pumps,N));
	}

	private static int findSmallestIndexForTour(PPump[] pumps, int N) {
		Queue<Integer> qFront,qBack;
		qFront = new LinkedList<>();
		qBack = new LinkedList<>();
		int front_accum = 0,idx = 0;
		while(idx < N) {
			if(front_accum + pumps[idx].amt - pumps[idx].distToNext >= 0) {
				qFront.add(idx);
				front_accum += pumps[idx].amt - pumps[idx].distToNext;
			} else {
				//empty the front queue and put all in back queue
				while(!qFront.isEmpty()) {
					qBack.add(qFront.poll());
				}
				qBack.add(idx);
				front_accum = 0;
			}
			idx++;
		}
		return qFront.poll();
	}
	
}
