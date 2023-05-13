package week12;

import java.io.*;
import java.util.*;

public class BOJ10711_모래성 {
	static int H, W;
	static char mat[][];
	static boolean visited[][];
	static int dx[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int dy[] = { 0, 1, -1, -1, 1, 0, 1, -1 };
	static Queue<Integer> que = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		mat = new char[H][W];
		visited = new boolean[H][W];

		for (int i = 0; i < H; i++) {
			String str = br.readLine();
			for (int j = 0; j < W; j++) {
				mat[i][j] = str.charAt(j);
			}
		}

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (mat[i][j] == '.' || mat[i][j] == '9')
					continue;
				if (!check(i, j)) {
					que.offer(i);
					que.offer(j);
					visited[i][j] = true;
				}
			}
		}
		System.out.println(bfs());
	}

	static int bfs() {
		int time = 0;
		while (!que.isEmpty()) {
			int size = que.size();

			for (int i = 0; i < size / 2; i++) {
				int X = que.poll();
				int Y = que.poll();

				mat[X][Y] = '.';

				for (int j = 0; j < 8; j++) {
					int dX = X + dx[j];
					int dY = Y + dy[j];

					if (dX < 0 && dX >= H && dY < 0 && dY >= W)
						continue;
					if (visited[dX][dY])
						continue;
					if (mat[dX][dY] == '.')
						continue;

					if (!check(dX, dY)) {
						que.offer(dX);
						que.offer(dY);
						visited[dX][dY] = true;
					}
				}
			}
			time++;
		}
		return time;
	}

	static boolean check(int x, int y) {
		int cnt = 0;

		for (int i = 0; i < 8; i++) {
			int X = x + dx[i];
			int Y = y + dy[i];

			if (X < 0 && X >= H && Y < 0 && Y >= W)
				continue;
			if (mat[X][Y] == '.')
				cnt++;
		}

		return mat[x][y] - '0' > cnt;
	}
}