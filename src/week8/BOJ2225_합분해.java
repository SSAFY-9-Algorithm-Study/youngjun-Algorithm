package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2225_합분해 {

	static int N;
	static int K;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		long[][] DP = new long[K + 1][N + 1];

		for (int i = 0; i <= N; i++) {
			DP[1][i] = 1;
		}

		for (int i = 2; i <= K; i++) {
			for (int j = 0; j <= N; j++) {

				for (int j2 = 0; j2 <= j; j2++) {
					DP[i][j] += DP[i - 1][j2];
					DP[i][j] %= 1000_000_000;
				}

			}
		}

		System.out.println(DP[K][N] % 1000_000_000);
	}

}
