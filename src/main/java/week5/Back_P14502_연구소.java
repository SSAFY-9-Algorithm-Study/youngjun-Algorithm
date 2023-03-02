package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Back_P14502_연구소 {
	static int[][] mat;
	static int[][] visited;
	static int[]  chosenZeros;
	static List<int[]> zeros;
	static int h;
	static int w;
	static int safeCnt = 0;
	static int max = Integer.MIN_VALUE;
	static int combCount = 0;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		mat = new int[h][w];
		visited = new int[h][w];
		zeros = new ArrayList<int[]>();
		for (int i = 0; i < h; i++) {
			st= new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				int val = Integer.parseInt(st.nextToken());
				mat[i][j] = val;
				if(val==0) safeCnt++;
			}
		}
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if(mat[i][j] == 0) {
					zeros.add(new int[] {i,j});
				}
			}
		}
		//0인 좌표들 모두 저장
		chosenZeros = new int[zeros.size()];
		chooseZero(0, chosenZeros);
		System.out.println(max);
		System.out.println(combCount);
//		for (int i = 0; i < h; i++) {
//			for (int j = 0; j < w; j++) {
//				if(mat[i][j] == 2) {
//					spread(i,j,mat);
//				}
//			}
//		}
		
		
		
	}
	
	public static int spread(int x, int y, int[][] mat, int cnt) {
		visited[x][y]=1;
		Deque<int[]> deq = new ArrayDeque<int[]>();
		deq.offer(new int[] {x,y});
		
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		while(!deq.isEmpty()) {
			int[] xySafe = deq.pollFirst();
			x = xySafe[0];
			y = xySafe[1];
			
			for (int i = 0; i < 4; i++) {
				if(x+dx[i]<0 || x+dx[i]>(h-1) || y+dy[i]<0 || y+dy[i]>(w-1))
					continue;
				if(visited[x+dx[i]][y+dy[i]]==0 && mat[x+dx[i]][y+dy[i]]==0) {
					cnt--;
					visited[x+dx[i]][y+dy[i]]=1;
//					System.out.println();
//					System.out.println(x+dx[i]);
//					System.out.println(y+dy[i]);
					deq.offer(new int[] {x+dx[i], y+dy[i]});
				}
			}
			
		}
		
//		for (int i = 0; i < h; i++) {
//			System.out.println();
//			for (int j = 0; j < w; j++) {
//				System.out.print(visited[i][j]);
//			}
//			
//		}
//		System.out.println("cnt is " + cnt);
		return cnt;
		
		
	}
	
	public static void chooseZero(int level, int chosenZeros[]) {
		if(level==3) {
			
			System.out.println(Arrays.toString(chosenZeros));
			
			int[][] newMat = new int[w][h];
			for (int i = 0; i < w; i++) {
				for (int j = 0; j < h; j++) {
					newMat[i][j] = mat[i][j];
				}
			}
			
			for (int i = 0; i < chosenZeros.length; i++) {
				if(chosenZeros[i]==1) {
					newMat[zeros.get(i)[0]][zeros.get(i)[1]]=1;
				}
			}
			
//			for (int i = 0; i < h; i++) {
//				System.out.println();
//				for (int j = 0; j < w; j++) {
//					System.out.print(newMat[i][j]);
//					}
//				}
			
			
//			System.out.println();
			int cnt = safeCnt;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if(newMat[i][j] == 2) {
						cnt = spread(i,j,newMat,cnt);
						
					}
				}
			}
			
			
//			System.out.println("cnt is " + cnt);
			combCount++;
			max = Math.max(max, cnt);
		
			visited = new int[w][h];
		}
		
		else {
			for (int i = 0; i < chosenZeros.length; i++) {
				if(chosenZeros[i]==0) {
					chosenZeros[i]=1;
					chooseZero(level+1, chosenZeros);
					chosenZeros[i]=0;
				}
			}
		}
	}

}
