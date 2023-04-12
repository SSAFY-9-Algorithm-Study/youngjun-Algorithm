package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1520_내리막길2 {
	static int M;
	static int N;
	static int[][] mat;
	static int[][] dp;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		mat = new int[M + 1][N + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 1; j <= N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp = new int[M + 1][N + 1];
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i][j] = -1;
			}
		}

		System.out.println(dfs(1, 1));

		for (int i = 1; i <= M; i++) {
			System.out.println();
			for (int j = 1; j <= N; j++) {
				System.out.print(dp[i][j]);
			}
		}
	}

	public static int dfs(int x, int y) {
		if (x == M && y == N) {
			return 1;
		}

		// 이미 탐색된 dfs 라면
		if (dp[x][y] != -1) {
			return dp[x][y];
		}

		// visited 처리
		else {
			dp[x][y] = 0;
			for (int i = 0; i < 4; i++) {
				int newx = x + dx[i];
				int newy = y + dy[i];

				if (newx < 1 || newy < 1 || newx > M || newy > N) {
					continue;
				}

				if (mat[x][y] > mat[newx][newy]) {
					dp[x][y] += dfs(newx, newy);
				}
			}

			return dp[x][y];
		}
		
	}

}