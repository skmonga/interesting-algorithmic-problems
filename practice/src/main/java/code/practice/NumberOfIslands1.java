package code.practice;

/**
 * http://www.geeksforgeeks.org/find-number-of-islands/
 * for a second implementation check 
 * http://www.geeksforgeeks.org/find-the-number-of-islands-set-2-using-disjoint-set
 * 
 * Given a boolean 2D matrix, find the number of islands.
 * 
 * A group of connected 1s forms an island. 
 * sA cell in 2D matrix can be connected to 8 neighbors.
 * 
 * This is an variation of the standard problem: “Counting number of connected
 * components in a undirected graph”
 */
public class NumberOfIslands1 {

	static int[] hor = {-1,-1,-1,0,0,1,1,1};
	static int[] ver = {-1,0,1,-1,1,-1,0,1};
	
	private static void markVisitedElements(int[][] arr, boolean[][] visited, int i, int j, int M, int N) {
		if(i < 0 || i >= M || j < 0 || j >= N || arr[i][j] == 0 || visited[i][j])
			return;
		visited[i][j] = true;
		for(int k = 0; k < 8; k++)
			markVisitedElements(arr, visited, i+hor[k], j+ver[k], M, N);
	}
	
	private static int connectedComponents(int[][] arr, int M, int N) {
		int connectedComponents = 0;
		boolean[][] visited = new boolean[M][N];
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(visited[i][j] || arr[i][j] == 0)
					continue;
				//this is a starting point
				connectedComponents++;
				markVisitedElements(arr,visited,i,j,M,N);
			}
		}
		return connectedComponents;
	}

	public static void main(String[] args) {
		int[][] arr = { { 1, 1, 0, 0, 0 },
				{ 0, 1, 0, 0, 1 },
				{ 1, 0, 0, 1, 1 },
				{ 0, 0, 0, 0, 0 },
				{ 1, 0, 1, 0, 1 } };

		System.out.println(connectedComponents(arr,5,5));
	}
	
}
