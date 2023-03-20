package week7.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17822_원판돌리기 {
	
	static int N;
	static int M;
	static int T;
	static int xi;
	static int di;
	static int ki;
	static int[] dx = {-1,1}; 
	
	static class Point{
		int val;
		Boolean visited;
		Boolean erased;
		
		public Point(int val, Boolean visited, Boolean erased) {
			super();
			this.val = val;
			this.visited = visited;
			this.erased = erased;
		}
		@Override
		public String toString() {
			return "Point [val=" + val + ", visited=" + visited + ", erased=" + erased + "]";
		}
	}
	
	static List<Point[]> circleList = new ArrayList<Point[]>();
	static int[][] deleteArr;
	static int[][] visited;
	static int sum = 0;
	static Boolean isNearNum = false;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		deleteArr = new int[N][M];
		visited = new int[N][M];
		cnt = N * M;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Point[] arr = new Point[M];
			for (int j = 0; j < M; j++) {
				int val  = Integer.parseInt(st.nextToken());
				arr[j]=new Point(val,false,false);
				sum+=val;
			}
			circleList.add(arr);
		}
		
		st = new StringTokenizer(br.readLine());
		xi = Integer.parseInt(st.nextToken());
		di = Integer.parseInt(st.nextToken());
		ki = Integer.parseInt(st.nextToken());
		
//		System.out.println(circleList);
		for (int i = 0; i < N; i++) {
			System.out.print(Arrays.toString(circleList.get(i)));
			System.out.println();
		}
		System.out.println();
		
//		turn();
//		
//		for (int i = 0; i < N; i++) {
//			System.out.print(Arrays.toString(circleList.get(i)));
//			System.out.print(" ");
//		}
//		
//		for (int i = 0; i < N; i++) {
//			System.out.print(Arrays.toString(circleList.get(i)));
//			System.out.print(" ");
//		}
		
		
		
		
		while(sum>0 && T>0) {
			isNearNum = false;
			turn();
			
			System.out.println("---------------turned-----------------");
			for (int i = 0; i < N; i++) {
				System.out.print(Arrays.toString(circleList.get(i)));
				System.out.println();
			}
			System.out.println();
			
			erase();
			
			
			
			
			System.out.println("---------------erased-----------------");
			for (int i = 0; i < N; i++) {
				System.out.print(Arrays.toString(circleList.get(i)));
				System.out.println();
			}
			System.out.println();
			
			
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					circleList.get(i)[j].visited = false;
				}
			}
			
			
			if(!isNearNum) {
				double avg = (double) sum/ (double) cnt;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						if(circleList.get(i)[j].val>avg) {
							circleList.get(i)[j].val-=1;
						}
						else if(circleList.get(i)[j].val<avg)
							circleList.get(i)[j].val+=1;
					}
				}
				System.out.println();
				System.out.println("sum is" + sum);
			}
			T--;
		}
		System.out.println(sum);

	}
	
	public static void erase() {
		for (int i2 = 0; i2 < N; i2++) {
			for (int i = 0; i < M; i++) {
				Point[] arr = circleList.get(i2);
				if(arr[i].visited || arr[i].erased)
					continue;
				arr[i].visited = true;
				Boolean erase = false;
				for (int j = 0; j < 2; j++) {
					int newx = i+dx[j];
					if(newx<0)
						newx = M-1;
					if(newx > M-1)
						newx = 0;
					if(arr[i].val==arr[newx].val) {
						erase = true;
						arr[newx].visited = true;
						arr[newx].erased = true;
						System.out.println(arr[newx].val);
						sum-=arr[newx].val;
						cnt--;
					}
				}
				
				for (int j = 0; j < 2; j++) {
					int newx = i2+dx[j];
					if(newx<0 || newx>M-1)
						continue;
					Point[] arr2 = circleList.get(newx);
					
					if(arr[i].val==arr2[i].val) {
						erase = true;
						arr2[i].visited = true;
						arr2[i].erased = true;
						System.out.println(arr2[i].val);
						sum-=arr2[i].val;
						cnt--;
					}
				}
				
				if(erase) {
					cnt--;
					arr[i].erased = true;
					System.out.println(arr[i].val);
					sum-=arr[i].val;
					isNearNum = true;
					
				}
				
			}
		}
	}
	
	public static void turn() {
		for (int turns = 0; turns < ki; turns++) {
			if(di==1) {
				for (int i = 0; i < N; i++) {
					if((i+1)%xi==0) {
						Point[] arr = circleList.get(i);
						Point temp = arr[0];
						for (int j = 0; j < M-1; j++) {
							arr[j] = arr[j+1];
						}
						arr[M-1] = temp;
						circleList.set(i, arr);
					}
				}
			}
			else {
				for (int i = 0; i < N; i++) {
					if((i+1)%xi==0) {
						Point[] arr = circleList.get(i);
						Point temp = arr[M-1];
						for (int j = M-1; j > 0; j--) {
							arr[j] = arr[j-1];
						}
						arr[0] = temp;
						circleList.set(i, arr);
					}
				}
			}
		}

	}
	
	

}
