package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1277_발전소설치_다익스트라 {
	static long[][] mat;
	static double[][] dp;
	static Queue<Integer> que;
	static int[] visited;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		double M = Double.parseDouble(br.readLine());
		mat = new long[N + 1][N + 1];
		dp = new double[N + 1][N + 1];
		visited = new int[N+1];
		que = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return  Double.compare(dp[1][o1],dp[1][o2]);
			}
		});

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			mat[i] = new long[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
		}

		for (int i = 1; i <= N; i++) {
			for (int j = i; j <= N; j++) {
				double dist = distCal(i, j);
				if (dist <= M) {
					dp[i][j] = dist;
					dp[j][i] = dist;
				} else {
					dp[i][j] = Double.MAX_VALUE;
					dp[j][i] = Double.MAX_VALUE;
				}

			}
		}
		
		for (int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine());
			int linkStart = Integer.parseInt(st.nextToken());
			int linkEnd = Integer.parseInt(st.nextToken());
			dp[linkStart][linkEnd] = 0;
			dp[linkEnd][linkStart] = 0;
		}
		
		search();
		System.out.println((int )Math.floor(dp[1][N]*1000));
	}

	private static void search() {
		que.add(1);
		
		while(!que.isEmpty()) {
			
			int cur = que.poll();
			if(visited[cur]==1) continue;
			visited[cur]=1;
			
			
			for (int i = 1; i <= N; i++) {
				int curEnd = i;
				dp[1][curEnd] = Math.min(dp[1][curEnd], dp[1][cur] + dp[cur][curEnd]);
				que.add(curEnd);
			} 
		}
	}

	public static double distCal(int elOne, int elTwo) {
		long arr1X = mat[elOne][0];
		long arr1Y = mat[elOne][1];
		long arr2X = mat[elTwo][0];
		long arr2Y = mat[elTwo][1];
		return Math.sqrt( Math.pow(Math.abs(arr1X - arr2X), 2)+ Math.pow(Math.abs(arr1Y - arr2Y), 2));
	}

}
