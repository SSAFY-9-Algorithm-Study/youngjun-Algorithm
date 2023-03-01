package week4.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BFS_P15686_치킨배달 {
	
	static int n;
	static int m;
	static int mat[][];
	static int visited[][];
	static List<int[]> chickens = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		mat = new int[n][n];
		visited = new int[n][n];
		

		int ans = 0;
		List<Integer> dist = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int val = Integer.parseInt(st.nextToken());
				if(val==2) chickens.add(new int[] {i,j});
				mat[i][j] = val;
			}
		}
		
//		for (int i = 0; i < n; i++) {
//			System.out.println();
//			for (int j = 0; j < n; j++) {
//				System.out.print(mat[i][j]);
//			}
//			
//		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(mat[i][j]==2) {
					dist.add(bfs(i,j));
					visited = new int[n][n];
				}	
			}
			
		}
		
		Collections.sort(dist);
		
		
	}
	
	public static int bfs(int x, int y) {
		visited[x][y] = 1;
		int cnt = 0;
		Deque<int[]> deq = new ArrayDeque<>();
		deq.offer(new int[] {x,y,0});
		
		int dx[] = {0,0,-1,1};
		int dy[] = {-1,1,0,0};
		
		while(!deq.isEmpty()) {
			int[] xy =  deq.pollFirst();
			x = xy[0];
			y = xy[1];
			cnt = xy[2];
//			System.out.println();
//			System.out.println(x);
//			System.out.println(y);
//			System.out.println(cnt);
			
			if(mat[x][y]==1) {
//				System.out.println("sdfsdf");
				return cnt;
			}
				
			
			for (int i = 0; i < 4; i++) {
				if(x+dx[i]<0 || x+dx[i]>(n-1) || y+dy[i]<0 || y+dy[i]>(n-1)) {
//					System.out.println("다음 갈 곳");
//					System.out.println(x+dx[i]);
//					System.out.println(y+dy[i]);
					continue;
					
				}
//				System.out.println("visited확인");
					
//				System.out.println(visited[x+dx[i]][y+dy[i]]);
				if(visited[x+dx[i]][y+dy[i]]==0) {
//					System.out.println("true");
					visited[x+dx[i]][y+dy[i]]=1;
					deq.add(new int[] {x+dx[i], y+dy[i], cnt+1});
				}
			}
		}
		return cnt;
		
		
		
	}

}
