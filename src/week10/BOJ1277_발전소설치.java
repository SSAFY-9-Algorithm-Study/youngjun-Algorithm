package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1277_발전소설치 {
	static long[][] mat;
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		double M = Double.parseDouble(br.readLine());
		mat = new long[N+1][N+1];
		double[][] dp = new double[N+1][N+1];
		
		
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			mat[i] = new long[] {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
			
		}
		
		for (int i = 1; i < N+1; i++) {
			for (int j = i; j < N+1; j++) {
				double dist = distCal(i,j);
				if(dist<=M) {
					dp[i][j] = dist;
					dp[j][i] = dist;
				}
				else {
					dp[i][j] = Integer.MAX_VALUE;
					dp[j][i] = Integer.MAX_VALUE;
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
		
		
		for (int i = 0; i < N+1; i++) {
			dp[i][i]=0;
		}
		
//		for (int i = 1; i < N; i++) {
//			System.out.println();
//			for (int j = 1; j < N; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= i ; j++) {
//				System.out.println("cur i is " + i);
//				System.out.println("cur j is " + j);
				if(dp[j][i]>M)
					continue;
//				System.out.println("dp[1][j]+dp[j][i] " + (dp[1][j]+dp[j][i]));
				dp[1][i] = Math.min(dp[1][i], dp[1][j]+dp[j][i]);
			}
		}
		
		System.out.println((int) Math.floor(dp[1][N]*1000));
//		for (int i = 1; i <= N; i++) {
//		System.out.println();
//		for (int j = 1; j <= N; j++) {
//			System.out.print(dp[i][j] + " ");
//		}
//	}
		
	}
	
	public static double distCal(int elOne, int elTwo) {
		long arr1X = mat[elOne][0];
		long arr1Y = mat[elOne][1];
		long arr2X = mat[elTwo][0];
		long arr2Y = mat[elTwo][1];
		return Math.sqrt(Math.pow(Math.abs(arr1X-arr2X), 2) + Math.pow(Math.abs(arr1Y-arr2Y), 2));
	}

}
