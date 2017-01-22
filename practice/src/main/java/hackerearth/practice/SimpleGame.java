package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SimpleGame {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        int N = Integer.parseInt(arr[0]);
        int M = Integer.parseInt(arr[1]);
        int[] A = new int[N];
        int[] B = new int[M];
        
        arr = br.readLine().split(" ");
        for(int i = 0; i < N; i++)
        	A[i] = Integer.parseInt(arr[i]);
        
        arr = br.readLine().split(" ");
        for(int i = 0; i < M; i++)
        	B[i] = Integer.parseInt(arr[i]);
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        Map<Integer, Integer> countMonk = new HashMap<>();
        Map<Integer, Integer> countNotMonk = new HashMap<>();
        
        int[] fMonk,fNotMonk;
        fMonk = new int[N];
        fNotMonk = new int[M];
        
        fillCountMaps(A,countMonk);
        fillCountMaps(B,countNotMonk);
        
        calculateLowerScores(A,B,fMonk,countNotMonk);
        calculateLowerScores(B,A,fNotMonk,countMonk);
        
        long scoreMonk = findScore(A,fMonk,countNotMonk,M);
        long scoreNotMonk = findScore(B,fNotMonk,countMonk,N);
        
        if(scoreMonk == scoreNotMonk) {
        	System.out.println("Tie");
        } else if(scoreMonk > scoreNotMonk) {
        	System.out.println("Monk " + (scoreMonk-scoreNotMonk));
        } else {
        	System.out.println("!Monk " + (scoreNotMonk-scoreMonk));
        }
	}

	private static void fillCountMaps(int[] a, Map<Integer, Integer> count) {
		for(int i = 0; i < a.length; i++) {
			count.put(a[i], 1 + ((count.get(a[i]) == null)?0:count.get(a[i])));
		}
	}

	private static long findScore(int[] a, int[] fMonk, Map<Integer, Integer> count,int other_size) {
		long score = 0;
		int count_less = 0;
		for(int i = 0; i < a.length; i++) {
			count_less = other_size - ((count.get(a[i]) == null)?0:count.get(a[i])) - fMonk[i];
			score += (fMonk[i]*count_less);
		}
		return score;
	}

	private static void calculateLowerScores(int[] a, int[] b, int[] fMonk,
			Map<Integer, Integer> count) {
		int i,j = 0;
		for(i = 0; i < a.length; i++) {
			if(i != 0 && a[i] == a[i-1]){
				fMonk[i] = fMonk[i-1];
				continue;
			}
			int extra_count = 0;
			if(j < b.length) {
			while(j < b.length && a[i] > b[j]) {
//				count.put(b[j], 1 + (count.get(b[j]) == null?0: count.get(b[j])));
				j++;
				extra_count++;
			}
			}
			fMonk[i] = ((i != 0)?fMonk[i-1]:0) + extra_count;
		}
		
	}
}
