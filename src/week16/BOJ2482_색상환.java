package week16;

import java.io.*;
import java.util.*;

public class BOJ2482_색상환 {

	static int N, M;
	static boolean[][] visited;
	static long[][] DP;
	static long MOD = 1000_000_003;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		DP = new long[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			DP[i][1] = i;
			DP[i][0] = 1;
			visited[i][0] = true;
			visited[i][1] = true;
		}

		System.out.println((dp(N - 1, M) + dp(N - 3, M - 1)) % MOD);
	}

	private static long dp(int i, int j) {
		if (!visited[i][j]) {
			if (j * 2 > i + 1 || j > i)
				return 0;

			DP[i][j] = (dp(i - 1, j) + dp(i - 2, j - 1)) % MOD;
			visited[i][j] = true;
		}
		return DP[i][j] % MOD;
	}
}
