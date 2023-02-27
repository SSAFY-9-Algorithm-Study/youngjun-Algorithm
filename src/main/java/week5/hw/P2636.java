package week5.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P2636 {

	static int arr[][];
	static int visited[][];
	static List<Integer> cntList = new ArrayList<>();
	static int h;
	static int w;
	static Boolean melt = false;
	static int cnt;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		arr = new int[h][w];
		visited = new int[h][w];
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				int val = Integer.parseInt(st.nextToken());
				arr[i][j] = val;
				if (val == 1)
					cnt++;

			}
		}

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (arr[i][j] == 1) {
					dfs(i, j);
					if (melt == true) {
						cnt--;
						arr[i][j] = 2;
						melt = false;
					}

					visited = new int[h][w];

				}
			}
		}
		cntList.add(cnt);

	}

	public static void dfs(int x, int y) {
		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { -1, 1, 0, 0 };

		if (x == h - 1 || x == 0 || y == w - 1 || y == 0) {
			melt = true;
			return;
		}

		else {

			for (int i = 0; i < 4; i++) {
				if (x + dx[i] > (h - 1) || x + dx[i] < 0 || y + dx[y] > (w - 1) || y + dy[i] < 0)
					continue;
				if (arr[x + dx[i]][y + dy[i]] == 0 || visited[x + dx[i]][y + dy[i]] == 0) {
					visited[x + dx[i]][y + dy[i]] = 1;
					dfs(x + dx[i], y + dy[i]);
				}

			}

		}

	}

}
