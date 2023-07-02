package week17;

import java.io.*;
import java.util.*;

public class BOJ11066_파일합치기 {

	static int T, M;
	static int ans;
	static int DP[][];
	static int sum[]; // 누적합 배열 sum
	static boolean visited[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T + 1; t++) {
			ans = 0;
			M = Integer.parseInt(br.readLine());
			DP = new int[M + 1][M + 1];
			sum = new int[M + 1];
			visited = new boolean[M + 1][M + 1];

			st = new StringTokenizer(br.readLine());

			for (int i = 1; i <= M; i++) {

				for (int j = 1; j <= M; j++) {
					DP[i][j] = Integer.MAX_VALUE;
				}
			}

			for (int i = 1; i <= M; i++) {
				//한 개의 파일을 합칠 때의 드는 비용은 0
				DP[i][i] = 0;
				int val = Integer.parseInt(st.nextToken());
				sum[i] = sum[i - 1] + val;
				visited[i][i] = true;
			}

			System.out.println(dp(1, M));
		}

	}

	private static int dp(int start, int end) {
		if (visited[start][end])
			return DP[start][end];
		for (int i = start; i < end; i++) {
			
			// 어느 구간의 DP(합치는 데에 드는 비용)값은 그 구간을 두 구간으로 나눈 각각의 DP + 두 구간을 합칠 때 드는 비용(즉, 두 구간들 각각의 구간합)
			// 40 30 30 50 (시작idx = 1) 인 경우
			// DP[2][3] 은 DP[2][2] (0) + DP[3][3](0) + 구간합[2][2](30) + 구간[3][3](50) 일 것이 
			
			DP[start][end] = Math.min(dp(start, i) + dp(i + 1, end) + partSum(start, i) + partSum(i + 1, end),
					DP[start][end]);
		}
		visited[start][end] = true;
		return DP[start][end];
	}

	// 구간합 구하기
	private static int partSum(int start, int end) {
		return sum[end] - sum[start - 1];
	}

}
