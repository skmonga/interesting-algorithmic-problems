package code.practice;

import java.awt.Point;
import java.util.LinkedList;

public class LargestSum {

	/*private int m;
	private int n;
	private int arr[][];
	
	public LargestSum(int arr[][],int m,int n) {
		this.arr = arr;
		this.m = m;
		this.n = n;
	}
	
	public int sum(int x,int y) {
		if(x > (m-1) || y > (n-1))
			return 0;
		int left_move = sum(x+1,y);
		int right_move = sum(x,y+1);
		return arr[x][y]+(left_move > right_move ? left_move:right_move);
	}*/
	
	private static int maxKTurns(int i, int j, int m, int n, int turns, int maxTurns, boolean right, boolean down) {
		if(i == m-1 && j == n-1) {
			if(turns <= maxTurns)
				return 1;
			else
				return 0;
		}
		
		if( i > m-1 || j > n-1 || turns > maxTurns)
			return 0;
		
		return maxKTurns(i, j+1, m, n, turns+((down)?1:0), maxTurns, true, false) +
				maxKTurns(i+1, j, m, n, turns+((right)?1:0), maxTurns, false, true);
	}
	
	public static void main(String[] args) {
		/*int arr[][] = {{15,25,30},{45,25,60},{70,75,10}};
		int arr1[][] = new int[3][];
		LargestSum lSum = new LargestSum(arr, 3, 3);
		System.out.println(lSum.sum(0, 0));*/
		int [][] arr = new int[3][3];
		System.out.println(maxKTurns(0,0,4,5,0,2,false,false));
	}

	
}
