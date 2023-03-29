package week8.hw;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14722_우유도시 {

	static int N;
	static int[][] mat;
	static int[][] DP;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		mat = new int[N + 1][N + 1];
		DP = new int[N + 1][N + 1];

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				int fromTop = DP[i - 1][j];
				int fromLeft = DP[i][j - 1];

				if (mat[i][j] == fromTop % 3) {
					fromTop++;
				}
				if (mat[i][j] == fromLeft % 3) {
					fromLeft++;
				}

				DP[i][j] = Math.max(fromTop, fromLeft);
			}
		}

		System.out.println(DP[N][N]);
	}

}
