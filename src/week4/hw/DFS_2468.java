package week4.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DFS_2468 {

	static int cnt;
	static int w;
	static int[][] arr;
	static int[][] mat;
	static int visited[][];
	static int max = 0;
	static int maxCnt = 1;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		w = Integer.parseInt(br.readLine());
		arr = new int[w][w];
		mat = new int[w][w];
		visited = new int[w][w];

		for (int i = 0; i < w; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				int val = Integer.parseInt(st.nextToken());
				arr[i][j] = val;
				max = val > max ? val : max;
			}
		}


		for (int i = 1; i <= max - 1; i++) {
			for (int j = 0; j < w; j++) {
				for (int j2 = 0; j2 < w; j2++) {
					if (arr[j][j2] > i) {
						mat[j][j2] = 1;
					}

				}
			}

			for (int j = 0; j < w; j++) {
				for (int j2 = 0; j2 < w; j2++) {
					if (mat[j][j2] == 1 && visited[j][j2] == 0) {
						dfs(j, j2);
						cnt++;
					}

				}
			}
			
			
			//다른 물 높이로 계산할 떄마다 visited, 물높이 배열 mat 초기화 작업
			if (cnt > maxCnt)
				maxCnt = cnt;
			cnt = 0;
			for (int j = 0; j < w; j++) {
				Arrays.fill(mat[j], 0);
				Arrays.fill(visited[j], 0);
			}

		}

		System.out.println(maxCnt);

	}

	public static void dfs(int x, int y) {
		visited[x][y] = 1;
		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { -1, 1, 0, 0 };

		for (int i = 0; i < 4; i++) {

			if ((x + dx[i]) >= 0 && (x + dx[i]) < w && (y + dy[i]) >= 0 && (y + dy[i]) < w
					&& mat[(x + dx[i])][(y + dy[i])] == 1 && visited[(x + dx[i])][(y + dy[i])] == 0) {
				dfs(x + dx[i], y + dy[i]);
			}
		}

	}

}
