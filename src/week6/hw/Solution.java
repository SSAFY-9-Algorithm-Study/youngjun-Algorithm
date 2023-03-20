package week6.hw;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

//5
//5
//0 0 0 0 0
//0 0 0 3 0
//0 1 0 0 0
//0 0 2 0 0
//0 0 0 0 0


//1
//5
//0 0 0 0 0
//0 1 0 0 0
//0 0 0 2 0
//0 0 0 0 0
//0 0 0 0 0

//1
//5
//0 0 0 0 0
//0 1 7 3 0
//0 9 2 5 0
//0 4 6 8 0
//0 0 0 0 0

//1
//10
//0 0 0 0 0 0 0 0 0 0
//0 2 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 4 0
//0 0 0 0 0 0 10 0 0 0
//0 0 0 0 0 0 0 9 0 0
//0 0 0 0 5 0 0 0 8 0
//0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 6 0 0 0
//0 3 0 0 0 0 0 7 1 0
//0 0 0 0 0 0 0 0 0 0

public class Solution {
	
	static int T;
	static int N;
	static int mat[][];
	static int visited[][][];
	static int apple;
	static int dx[];
	static int dy[];
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for (int i = 1; i < T+1; i++) {
			
			
			N = Integer.parseInt(br.readLine());
			mat = new int[N][N];
			visited = new int[N][N][4];
			
			
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int j2 = 0; j2 < N; j2++) {
					int val = Integer.parseInt(st.nextToken());
					mat[j][j2] = val;
					if(val>apple)
						apple = val;
				}
			}
			
			bfs(0,1,0,0,1);
			System.out.println("#" + i + " " + ans);
			ans = Integer.MAX_VALUE;
		}
		
		
	}
	
	public static void bfs(int x, int y, int dir, int turnCnt, int nextApple) {
		
		Deque<int[]> deq = new ArrayDeque<>();
		deq.offer(new int[] {x,y,dir,turnCnt,nextApple});
		
		while(!deq.isEmpty()) {
			int arr[] = deq.pollFirst();
			x = arr[0];
			y = arr[1];
			dir = arr[2];
			turnCnt = arr[3];
			nextApple = arr[4];
			
			if(mat[x][y] == nextApple) {
				if(nextApple == apple) {
					if(ans>turnCnt){
						ans = turnCnt;
					}
				}
//				System.out.println("x is " + x + " y is " + y + " turn is " + turnCnt + " targetApple is " + nextApple);
				visited = new int[N][N][4];
				bfs (x,y,dir,turnCnt,nextApple+1);
			}

			movableDxDy(dir);
			
			for (int i = 0; i < 2; i++) {
				int newx = x + dx[i];
				int newy = y + dy[i];
				int newDir = 0;
				
				if(newx<0 || newx>(N-1) || newy<0 || newy>(N-1))
					continue;
				
				if(dx[i]==0 && dy[i]==1)
					newDir = 0;
				if(dx[i]==1 && dy[i]==0)
					newDir = 1;
				if(dx[i]==0 && dy[i]==-1)
					newDir = 2;
				if(dx[i]==-1 && dy[i]==0)
					newDir = 3;
				
				
				if(visited[newx][newy][newDir]==0) {
					visited[newx][newy][newDir]=1;

					if(dir == newDir) {
						deq.offer(new int[] {newx, newy, newDir,turnCnt, nextApple});
						
					}
						
					else {
						deq.offer(new int[] {newx, newy, newDir,turnCnt+1, nextApple});
					}			
				}
			}
		}
		
	}
	
	public static void movableDxDy(int dir) {
		if(dir==0) {
			dx = new int[] {0,1}; //현재 우 이면 우 혹은 하 //우, 하
			dy = new int[] {1,0}; 
		}
		
		if(dir==1) {
			dx = new int[] {0,1}; //현재 하 이면 좌 혹은 하 // 좌, 하
			dy = new int[] {-1,0};
		}
		
		if(dir==2) {
			dx = new int[] {0,-1}; //현재 좌 이면 좌 혹은 상
			dy = new int[] {-1,0};
		}
		
		if(dir==3) {
			dx = new int[] {0,-1}; //현재 상 이면 우 혹은 상
			dy = new int[] {1,0};
		}
	}
	
	
	

}
