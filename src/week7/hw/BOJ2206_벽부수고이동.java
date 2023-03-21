package week7.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;



public class BOJ2206_벽부수고이동 {
	
	static int H;
	static int W;
	static int[][] mat;
	static int[][][] visited;
	static int ans = -1;
	
	static int[] dx = {-1,1,0,0}; 
	static int[] dy = {0,0,-1,1}; 
 	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		mat = new int[H][W];
		visited =new int[H][W][2];
		
		for (int i = 0; i < H; i++) {
			String str = br.readLine();
			for (int j = 0; j < W; j++) {
				mat[i][j] = str.charAt(j)-'0';
			}
		}
		
		bfs();
		System.out.println(ans);
	}

	private static void bfs() {
		Deque<int[]> deq = new ArrayDeque<int[]>();
		deq.offer(new int[] {0,0,1,1});
		visited[0][0][1]=1;
		
		while(!deq.isEmpty()) {
			int arr[] = deq.pollFirst();
			int x = arr[0];
			int y = arr[1];
			int breakable = arr[2];
			int dist = arr[3];
			
			if(x==H-1 && y==W-1) {
				ans = dist;
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int newx = x+dx[i];
				int newy = y+dy[i];
				
				
				if(newx<0 || newx>H-1 || newy<0 || newy>W-1 || (breakable==0 && mat[newx][newy]==1) ||  visited[newx][newy][breakable]==1)
					continue;
				visited[newx][newy][breakable] = 1;
				
				if(mat[newx][newy]==1 )
					deq.offer( new int[] {newx,newy,breakable-1,dist+1});
				else
					deq.offer( new int[] {newx,newy,breakable,dist+1});
				
			}
			
		}
		
	}

}
