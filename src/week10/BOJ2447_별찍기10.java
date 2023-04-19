package week10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ2447_별찍기10 {
	static int N;
	static String[][][] dp;
	static int[] visited;
	static BufferedWriter buf =  new BufferedWriter(new OutputStreamWriter(System.out));;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int log3N = (int) (Math.log10(N) / Math.log10(3));
//		System.out.println("log3n is " + log3N);
		dp = new String[log3N + 1][N][N];
		visited = new int[N + 1];

		dp[1] = new String[][] { { "*", "*", "*" }, { "*", " ", "*" }, { "*", "*", "*" } };
		visited[1] = 1;

		
		if(N==3) {
			String res[][] = dp[log3N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(res[i][j]);
				}
				System.out.println();
			}
			
		}
		else
			printStar(N);
		
	}

	public static void printStar(int n) throws IOException {
//		System.out.println("visiting " + (n));

		int mod = n / 3;
		int log3N = (int) (Math.log10(n) / Math.log10(3));

		if (n == 3) {
			return;
		}
		
		if (visited[n / 3] == 0) {
			
			printStar(n / 3);
		}

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {
				if (i >= mod && i < n - mod && j >= mod && j < n - mod)
					dp[log3N][i][j] = " ";
				else
					dp[log3N][i][j] = dp[log3N - 1][i % mod][j % mod];
				if (n == N) {
					buf.write(dp[log3N][i][j]);
				}
			}
			if (n == N)
				buf.write("\n");
		}
		buf.flush();
		visited[n] = 1;
	}

}
