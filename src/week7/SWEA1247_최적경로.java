package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA1247_최적경로 {
	
	static int T;
	static int N;
	static Point comp;
	static Point home;
	static Point[] pointList;
	static int ans = Integer.MAX_VALUE;
	
	static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			N = Integer.parseInt(br.readLine());
			pointList = new Point[N];
			
			st = new StringTokenizer(br.readLine());
			comp = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			home = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			for (int i = 0; i < N; i++) {
				pointList[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			perm(0,new int[N], new int[N]);
			System.out.println("#" + t + " " + ans);
			ans = Integer.MAX_VALUE;
			
		}
	}
	
	public static void perm(int level, int[] res, int visited[]) {
		if(level==N) {
//			System.out.println(Arrays.toString(res));
			ans = Math.min(ans, cal(res));
		}
		
		else {
			for (int i = 0; i < N; i++) {
				
				if(visited[i]==0) {
					res[level] = i;
					visited[i] = 1;
					perm(level+1, res, visited);
					res[level] = 0;
					visited[i] = 0;
				}
				
			}
		}
	}
	
	public static int cal(int[] res) {
		int sum = 0;
		int x = comp.x;
		int y = comp.y;
		
		Point curPoint;
		for (int i = 0; i < N; i++) {
			curPoint = pointList[res[i]];
			sum+= (Math.abs(x-curPoint.x) + Math.abs(y-curPoint.y));
			x= curPoint.x;
			y = curPoint.y;
		}
		
		sum+=(Math.abs(x-home.x) + Math.abs(y-home.y));
		return sum;
		
	}

}
