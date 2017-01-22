package hackerearth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JPEscapePlan {

	static int[] xpos = {0,1,0,-1};
	static int[] ypos = {1,0,-1,0};
	           
	static class Pair {
		int x;
		int y;
		public Pair() {
			
		}
		
		public Pair(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        int N = Integer.parseInt(arr[0]);
        int M = Integer.parseInt(arr[1]);
        long[][] heights = new long[N][M];
        for(int i = 0; i < N; i++) {
        	arr = br.readLine().split(" ");
        	for(int j = 0; j < M; j++)
        		heights[i][j] = Long.parseLong(arr[j]);
        }
        
        arr = br.readLine().split(" ");
        int dx = Integer.parseInt(arr[0]);
        int dy = Integer.parseInt(arr[1]);
        int j = Integer.parseInt(arr[2]);
        
        Pair[] path = new Pair[100000];
        int pathLength = checkAndPrintPath(path, heights,N,M,dx-1,dy-1,j);
        if(pathLength == -1) {
        	System.out.println("NO");
        } else {
        	System.out.println("YES");
        	System.out.println(pathLength);
        	for(int i = 0; i < pathLength; i++)
        		System.out.println((path[i].x + 1) + " " + (path[i].y + 1));
        }
        	
	}

	private static int checkAndPrintPath(Pair[] path,long[][] heights, int n, int m, int dx, int dy, int j) {
		
		path[0] = new Pair(dx, dy);
		if(isBoundary(dx,dy,n,m))
			return 1;
		boolean[][] visited = new boolean[n][m];
		visited[dx][dy] = true;
		
		int next_index = 1;
		for (int i = 0; i < 4; i++) {
			int next_xpos = dx + xpos[i];
			int next_ypos = dy + ypos[i];
			if(next_xpos >= 0 && next_xpos < n && next_ypos >= 0 && next_ypos < m &&
				heights[dx][dy] >= heights[next_xpos][next_ypos] && heights[dx][dy] - j <= heights[next_xpos][next_ypos]) {
				path[next_index] = new Pair(next_xpos, next_ypos);
				visited[next_xpos][next_ypos] = true;
				int len = pathToBoundaryAvailable(path,heights,n,m,next_xpos,next_ypos,j,next_index+1,visited);
				if(len != -1)
					return len;
				visited[next_xpos][next_ypos] = false;
			}
		}
		return -1;
	}

	private static int pathToBoundaryAvailable(Pair[] path, long[][] heights, int n, int m, int dx,
			int dy, int j, int index, boolean[][] visited) {
		if(isBoundary(dx,dy,n,m))
			return index;
		for (int i = 0; i < 4; i++) {
			int next_xpos = dx + xpos[i];
			int next_ypos = dy + ypos[i];
			if(next_xpos >= 0 && next_xpos < n && next_ypos >= 0 && next_ypos < m && 
				heights[dx][dy] >= heights[next_xpos][next_ypos] && heights[dx][dy] - j <= heights[next_xpos][next_ypos]) {
				path[index] = new Pair(next_xpos, next_ypos);
				visited[next_xpos][next_ypos] = true;
				int len = pathToBoundaryAvailable(path,heights,n,m,next_xpos,next_ypos,j,index+1,visited);
				if(len != -1)
					return len;
				visited[next_xpos][next_ypos] = false;
			}
		}
		return -1;
	}

	private static boolean isBoundary(int dx, int dy, int n, int m) {
		if(dx == 0 || dx == (n-1) || dy == 0 || dy == (m-1))
			return true;
		return false;
	}
}
