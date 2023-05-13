package week12;

import java.io.*;
import java.util.*;

public class BOJ1915_가장큰정사각형DP {

	static int H;
	static int W;
	static int[][] mat;
	static int[][] dp;
	static int maxSize;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		mat = new int[H + 1][W + 1];
		dp = new int[H + 1][W + 1];

		for (int i = 1; i <= H; i++) {
			String str = br.readLine();
			for (int j = 1; j <= W; j++) {
				mat[i][j] = str.charAt(j - 1) - '0';
			}
		}

		// 내 위치가 1인 경우 최대 정사각형 너비는 dp위쪽, 왼쪽, 대각선 왼쪽의 최솟값에서 1을 더한 값
		for (int i = 1; i <= H; i++) {
			for (int j = 1; j <= W; j++) {
				if (mat[i][j] == 1) {
					int max = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
					maxSize = Math.max(max + 1, maxSize);
					dp[i][j] = max + 1;
				}
			}
		}
		System.out.println(maxSize * maxSize);
	}
}
