package week4.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BFS_2589 {
	static int h;
	static int w;
	static char[][] arr;
	static int[][] visited;
	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		arr = new char[h][w];
		visited = new int[h][w];
		for (int i = 0; i < h; i++) {
			String str = br.readLine();
			for (int j = 0; j < w; j++) {
				arr[i][j] = str.charAt(j);
			}
			
		}
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if(arr[i][j]=='L') {
					visited[i][j]=1;
					int dist = bfs(i,j,0);
//					System.out.println("dist of " + i +" "+ j + " is " + dist);
					max = Math.max(max, dist);
					visited = new int[h][w];
				}
					
			}
			
		}
		
		System.out.println(max);
		
	}
	
	public static int bfs(int x, int y, int level) {
		Deque<int[]> deq = new ArrayDeque<>();
		deq.offer(new int[] {x,y,level});
		int maxLevel = 0;
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		while(!deq.isEmpty()) {
			int[] newArr = deq.pollFirst();
			x = newArr[0];
			y = newArr[1];
			level = newArr[2];
			
			maxLevel = Math.max(maxLevel, level);
			
			for (int i = 0; i < 4; i++) {
				if(x+dx[i]<0 || x+dx[i]>(h-1) || y+dy[i]<0 || y+dy[i]>(w-1)) 
					continue;
				if(visited[x+dx[i]][y+dy[i]]==0 && arr[x+dx[i]][y+dy[i]]=='L') {
					visited[x+dx[i]][y+dy[i]]=1;
					deq.offer(new int[] {x+dx[i], y+dy[i], level+1});
				}
			}
			
			
			
		}
		return maxLevel;
	}

}
