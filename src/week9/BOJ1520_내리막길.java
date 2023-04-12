package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


//만약 아래로만 내려갈 수 있다면
public class BOJ1520_내리막길 {

	static int[] dx = { 0, -1, 0 };
	static int[] dy = { -1, 0, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] mat = new int[M][N];
		int[][] visited = new int[M][N];
		long[][] dp = new long[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// dp 첫줄 채우기
		dp[0][0] = 1;
		for (int i = 1; i < N; i++) {
			if ((mat[0][i - 1] > mat[0][i]) && dp[0][i-1]==1)
				dp[0][i] = 1;
		}

		// 다음줄
		for (int i = 1; i < M; i++) {
			int[] arr = mat[i].clone();
			Arrays.sort(arr);
			for (int j = N - 1; j >= 0; j--) {
				int curVal = arr[j];
//				System.out.println("curval is " + curVal);
				for (int k = 0; k < N; k++) {
					if (mat[i][k] == curVal) {
						
						// cal
						for (int k2 = 0; k2 < 3; k2++) {
							int newx = i+dx[k2];
							int newy = k+dy[k2];
							
							if(newx<0 || newx>M-1 || newy<0 || newy>N-1 || mat[newx][newy]<=mat[i][k] || visited[i][k]==1)
								continue;
							dp[i][k]+=dp[newx][newy];
							
						}
						visited[i][k] = 1;

					}
				}

			}

		}
		
//		for (int i = 0; i < M; i++) {
//			System.out.println();
//			for (int j = 0; j < N; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//		}
	
		System.out.println(dp[M-1][N-1]);

	}

}