package week8.extra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SWEA4193_수영대회결승전 {
	
	static int T;
	static int N;
	static int[][] mat;
	static int[][] visited;
	static Point start;
	static Point end;
	static int ans = -1;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static class Point{
		int x;
		int y;
		int sec = 0;
		
		public Point(int x, int y, int sec) {
			super();
			this.x = x;
			this.y = y;
			this.sec = sec;
		}

	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			N = Integer.parseInt(br.readLine());
			mat = new int[N][N];
			visited = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			st = new StringTokenizer(br.readLine());
			start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
			st = new StringTokenizer(br.readLine());
			end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
			
			bfs(start);
			System.out.println("#" + t + " " + ans);
			ans = -1;
		}
	}

	private static void bfs(Point start) {
		Deque<Point> deq = new ArrayDeque<>();
		deq.offer(start);
		
		while(!deq.isEmpty()) {
			Point curPoint = deq.pollFirst();
			
			if(curPoint.x == end.x && curPoint.y == end.y) {
				ans = curPoint.sec;
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int newx = curPoint.x + dx[i];
				int newy = curPoint.y + dy[i];
				int sec = curPoint.sec;
				
				if(newx<0 || newx>N-1 || newy<0 || newy>N-1 || visited[newx][newy] == 1 || mat[newx][newy] == 1)
					continue;
				if(mat[newx][newy]!=2) {
					deq.offer(new Point(newx, newy, sec+1));
					visited[newx][newy] = 1;
				}
				else if(mat[newx][newy]==2 && sec%3!=2) {
					deq.offer(new Point(curPoint.x, curPoint.y, sec+1));
					
				}
					
				else {
					deq.offer(new Point(newx, newy, sec+1));
					visited[newx][newy] = 1;
				}
			}
		}
		
	}

}
