package week14;

import java.io.*;
import java.util.*;

public class BOJ14712_넴모넴모 {

	static int N;
	static int M;
	static int ans;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N + 1][M + 1];

		dfs(1, 1);
		System.out.println(ans);

	}

	private static void dfs(int x, int y) {

		if (y == M + 1) {
			ans++;
			return;
		}

		int nextX = x;
		int nextY = y;
		if (x == N) {
			nextX = 1;
			nextY++;
		} else {
			nextX++;
		}

		if (visited[x - 1][y - 1] && visited[x][y - 1] && visited[x - 1][y]) {
			visited[x][y] = false;
			dfs(nextX, nextY);

		} else {
			visited[x][y] = false;
			dfs(nextX, nextY);

			visited[x][y] = true;
			dfs(nextX, nextY);
		}

	}

}
