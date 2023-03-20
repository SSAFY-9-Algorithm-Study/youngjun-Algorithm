package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ14500_테트로미노 {

	static int H;
	static int W;
	static int[][] mat;
	static int[][] visited;
	static int ans;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Deque<int[]> deq = new ArrayDeque<int[]>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		visited = new int[H][W];
		mat = new int[H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				visited[i][j] = 1;
				dfs(i, j, 0, mat[i][j]);
				visited[i][j] = 0;
				notDfsShape(i, j);
			}

		}
		System.out.println(ans);
	}

	public static void dfs(int x, int y, int level, int sum) {

		if (level == 3) {
			if (sum > ans)
				ans = Math.max(sum, ans);
			return;

		}

		else {
			for (int i = 0; i < 4; i++) {
				int newx = x + dx[i];
				int newy = y + dy[i];

				if (newx < 0 || newx > (H - 1) || newy < 0 || newy > (W - 1) || visited[newx][newy] == 1)
					continue;
				visited[newx][newy] = 1;
				dfs(newx, newy, level + 1, sum + mat[newx][newy]);
				visited[newx][newy] = 0;

			}
		}
	}

	public static void notDfsShape(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int newx1 = x + dx[i];
			int newy1 = y + dy[i];
			if (newx1 < 0 || newx1 > H - 1 || newy1 < 0 || newy1 > W - 1)
				continue;

			for (int j = i + 1; j < 4; j++) {

				int newx2 = x + dx[j];
				int newy2 = y + dy[j];
				if (newx2 < 0 || newx2 > H - 1 || newy2 < 0 || newy2 > W - 1)
					continue;

				for (int j2 = j + 1; j2 < 4; j2++) {

					int newx3 = x + dx[j2];
					int newy3 = y + dy[j2];
					if (newx3 < 0 || newx3 > H - 1 || newy3 < 0 || newy3 > W - 1)
						continue;

					int sum = mat[x][y] + mat[newx1][newy1] + mat[newx2][newy2] + mat[newx3][newy3];
					if (sum > ans) {
//							System.out.println("max changed to" + sum);
//							System.out.println(x + " " + y);
						ans = sum;
					}

				}
			}
		}
	}

}
