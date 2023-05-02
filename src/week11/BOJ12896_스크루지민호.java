package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12896_스크루지민호 {
	
	//dfs 하자!

	static int N;
	static int[][] mat;
	static int[][] dp;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		mat = new int[N + 1][N + 1];
		dp = new int[N + 1][N + 1];
		for (int i = 0; i < N + 1; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
			dp[i][i] = 0;
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			mat[start][end] = 1;
			mat[end][start] = 1;
		}

		for (int i = 1; i <= N; i++) {
//			System.out.println("bfs");
			bfs(i);
		}
		for (int i = 1; i <= N; i++) {
			int maxDist = 0;
			for (int j = 1; j <= N; j++) {
				maxDist = Math.max(maxDist, dp[i][j]);
			}
			ans = Math.min(maxDist, ans);
		}

		System.out.println(ans);

	}

	private static void bfs(int i) {
		int[] visited = new int[N + 1];
		int visitedCnt = 0;
		Queue<int[]> que = new LinkedList<>();
		visited[i] = 1;
		que.offer(new int[] { i, 0 });

		while (!que.isEmpty()) {
			if (visitedCnt > N - i)
				break;

			int[] cur = que.poll();
			for (int j = 1; j < N + 1; j++) {
				if (mat[cur[0]][j] == 1 && visited[j] == 0) {
					visited[j] = 1;
					que.add(new int[] { j, cur[1] + 1 });
					dp[i][j] = cur[1] + 1;
					dp[j][i] = cur[1] + 1;
					if (j > i)
						visitedCnt++;
				}
			}
		}
	}

}
