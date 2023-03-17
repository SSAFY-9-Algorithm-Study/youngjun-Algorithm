package week5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ17070_파이프옮기기1 {
	
	static int N;
	static int mat[][];
	static int dx[];
	static int dy[];
	static int ans = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		mat = new int[N+1][N+1];
		
		
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N+1; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		if(mat[N][N]==1)
			System.out.println(ans);
		else {
			move(1,2,0);
			System.out.println(ans);
		}
		
		
	}
	
	public static void move(int x, int y, int dir) {
		
		Deque<int[]> deq = new LinkedList<>();
		deq.offer(new int[] {x,y,dir});
		
		while(!deq.isEmpty()) {
			int[] arr = deq.pollFirst();
			x = arr[0];
			y = arr[1];
			dir = arr[2];
			
//			System.out.println(x);
//			System.out.println(y);
			
			if(x==N && y==N)
				ans++;
			
			int[] dx0 = new int[] {0,1};
			int[] dy0 = new int[] {1,1};
			int[] dx1= new int[] {1,1};//아래로가기, 대각선으로 가
			int[] dy1 = new int[] {0,1};
			int[] dx2 = new int[] {1,1,0};
			int[] dy2 = new int[] {0,1,1};
			
			if(dir==0) {
				dx = dx0;
				dy = dy0;
			}
			if(dir==1) {
				dx = dx1;
				dy = dy1;
			}
			if(dir==2) {
				dx = dx2;
				dy = dy2;
			}
			
			
			for (int i = 0; i < dx.length; i++) {
				int newX = x+dx[i];
				int newY = y+dy[i];
				
				if(newX>(N) || newY>(N) || mat[newX][newY]==1)
					continue;
				if(newX>x && newY==y)
					deq.offer(new int[] {newX, newY, 1});
				if(newX>x && newY>y && mat[newX][y]==0 && mat[x][newY]==0)
					deq.offer(new int[] {newX, newY, 2});
				if(newX==x && newY>y)
					deq.offer(new int[] {newX, newY, 0});
				
			}
			
		}
	}
	
//	public static void dxdyForDir(int dir) {
//		switch (dir) {
//		case 0: //가로
//			dx = new int[] {0,1};//가로로 가기, 대각선으로 가기
//			dy = new int[] {1,1};
//			break;
//		case 1: //세로
//			dx = new int[] {1,1};//아래로가기, 대각선으로 가
//			dy = new int[] {0,1};
//			break;
//		case 2: //대각선
//			dx = new int[] {1,1,0};//아래로 가기, 대각선으로 가기, 가로로 가
//			dy = new int[] {0,1,1};
//			break;
//		default:
//			break;
//		}
//	}

}
