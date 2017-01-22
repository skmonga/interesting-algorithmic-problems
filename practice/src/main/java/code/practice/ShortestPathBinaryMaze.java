package code.practice;

/**
 * Refer: http://www.geeksforgeeks.org/shortest-path-in-a-binary-maze/
 * This solution doesn't fulfil the complexity but is a valid solution.It uses DFS.
 * The complexity optimal solution uses BFS. Refer to the link for the same.
 * Shortest path in a Binary Maze.
 *  Given a MxN matrix where each element can
 * either be 0 or 1. We need to find the shortest path between a given source
 * cell to a destination cell. The path can only be created out of a cell if its
 * value is 1.
 * 
 * Expected time complexity is O(MN).
 *
 */

public class ShortestPathBinaryMaze {

	private static int min(int a,int b) {
		return a < b?a:b;
	}
	
	private static int shortestPathUtil(int[][] mat, boolean[][] visited, int s_x, int s_y,
			int d_x, int d_y, int M,int N) {
		int min_dist = Integer.MAX_VALUE;
		if(s_x == d_x && s_y == d_y)
			return 0;
		
		if(s_x < 0 || s_x >= M || s_y < 0 || s_y >= N || visited[s_x][s_y] || (mat[s_x][s_y] == 0))
			return min_dist;
		
		//this is a valid position ,so see where can we go from here
		//only neighbours with value 1 are valid
		//marking current element as visited
		visited[s_x][s_y] = true;
		int left = shortestPathUtil(mat, visited, s_x, s_y-1, d_x, d_y, M, N);
		int right = shortestPathUtil(mat, visited, s_x, s_y+1, d_x, d_y, M, N);
		int top = shortestPathUtil(mat, visited, s_x-1, s_y, d_x, d_y, M, N);
		int down = shortestPathUtil(mat, visited, s_x+1, s_y, d_x, d_y, M, N);
		int x_mov = min(left,right);
		int y_mov = min(top,down);
		if(x_mov == Integer.MAX_VALUE && y_mov == Integer.MAX_VALUE) {
			min_dist = x_mov;
		} else {
			min_dist = 1 + min(x_mov,y_mov);
		}
		
		//all paths from this are calculated so mark it as unvisited and try other solutions
		visited[s_x][s_y] = false;
		
		return min_dist;
		
	}
	
	private static int shortestPath(int[][] mat, int s_x, int s_y, int d_x, int d_y, int M, int N) {
		boolean visited[][] = new boolean[M][N];
		return shortestPathUtil(mat,visited,s_x,s_y,d_x,d_y,M,N);
	}


	public static void main(String[] args) {
		int M = 9,N = 10;
		int mat[][] = {
		        { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
		        { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
		        { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
		        { 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
		        { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
		        { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 },
		        { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
		        { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
		        { 1, 1, 0, 0, 0, 0, 1, 0, 0, 1 }
		    };
		int s_x = 0,s_y = 0;
		int d_x = 3,d_y = 4;
		
		System.out.println(shortestPath(mat,s_x,s_y,d_x,d_y,M,N));
		
	}

	
}
