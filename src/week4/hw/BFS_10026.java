package week4.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BFS_10026 {

	static int w;
	static char[][] arr;
	static int[][] visited;
	static int normalCnt;
	static int colorWeakCnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		w = Integer.parseInt(br.readLine());
		arr = new char[w][w];
		visited = new int[w][w];

		for (int i = 0; i < w; i++) {
			String str = br.readLine();
			for (int j = 0; j < w; j++) {
				arr[i][j] = str.charAt(j);
			}

		}

		for (int i = 0; i < w; i++) {
			for (int j = 0; j < w; j++) {
				if (visited[i][j] == 0) {
					char col = arr[i][j];
					bfs(i, j, colorToInt(col));
					normalCnt++;
				}
			}

		}

		visited = new int[w][w];

		for (int i = 0; i < w; i++) {
			for (int j = 0; j < w; j++) {
				if (visited[i][j] == 0) {
					char col = arr[i][j];
					bfsForWeak(i, j, colorWeakToInt(col));
					colorWeakCnt++;
				}
			}

		}

		System.out.print(normalCnt + " ");
		System.out.print(colorWeakCnt);

	}

	public static int colorToInt(char col) {
		if (col == 'R')
			return 1;
		else if (col == 'G')
			return 2;
		else
			return 3;

	}

	public static int colorWeakToInt(char col) {
		if (col == 'B')
			return 1;
		else
			return 3;

	}

	public static void bfs(int x, int y, int col) {
		visited[x][y] = 1;
		Deque<int[]> deq = new ArrayDeque<int[]>();

		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		deq.offer(new int[] { x, y, col });
		while (!deq.isEmpty()) {
			int[] xy = deq.pollFirst();
			x = xy[0];
			y = xy[1];
			col = xy[2];

			for (int i = 0; i < 4; i++) {
				if (x + dx[i] < 0 || x + dx[i] > (w - 1) || y + dy[i] < 0 || y + dy[i] > (w - 1))
					continue;
				if (visited[x + dx[i]][y + dy[i]] == 0 && colorToInt(arr[x + dx[i]][y + dy[i]]) == col) {
					visited[x + dx[i]][y + dy[i]] = 1;
					deq.offer(new int[] { x + dx[i], y + dy[i], col });
				}
			}
		}
	}

	public static void bfsForWeak(int x, int y, int col) {
		visited[x][y] = 1;
		Deque<int[]> deq = new ArrayDeque<int[]>();

		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		deq.offer(new int[] { x, y, col });
		while (!deq.isEmpty()) {
			int[] xy = deq.pollFirst();
			x = xy[0];
			y = xy[1];
			col = xy[2];

			for (int i = 0; i < 4; i++) {
				if (x + dx[i] < 0 || x + dx[i] > (w - 1) || y + dy[i] < 0 || y + dy[i] > (w - 1))
					continue;
				if (visited[x + dx[i]][y + dy[i]] == 0 && colorWeakToInt(arr[x + dx[i]][y + dy[i]]) == col) {
					visited[x + dx[i]][y + dy[i]] = 1;
					deq.offer(new int[] { x + dx[i], y + dy[i], col });
				}
			}
		}
	}

}
