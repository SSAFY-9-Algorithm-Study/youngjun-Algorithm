package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2447_별찍기10 {
	static String[][][] dp;
	static int[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int log3N = (int )(Math.log10(n) / Math.log10(3));
		System.out.println("log3n is " + log3N);
		dp = new String[log3N+1][n][n];
		visited = new int[log3N+1];

		dp[1] = new String[][] { { "*", "*", "*" }, { "*", " ", "*" }, { "*", "*", "*" } };
		visited[1] = 1;
		printStar(log3N);
		String res[][] = dp[log3N];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(res[i][j]);
			}
			System.out.println();
		}
	}

	public static void printStar(int n) {

		
		int mod = n/3;
		int log3N = (int )(Math.log10(n) / Math.log10(3));
		if (visited[mod] == 0 && mod>0) {
			printStar(mod);
			visited[mod] = 1;
		}

		for (int i = 0; i < mod+1; i++) {
			for (int j = 0; j < n; j++) {
				if (i >= mod && i < n - mod && j >= mod && j < n - mod)
					dp[log3N][i][j] = " ";
				else
					dp[log3N][i][j] = dp[log3N-1][i % mod][j % mod];
			}
		}
	}

}
