package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SWEA1953_탈주범검거 {

	static int T;
	static int H;
	static int W;
	static int startH;
	static int startW;
	static int time;
	static int[][] mat;
	static int[][] visited;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {

			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			startH = Integer.parseInt(st.nextToken());
			startW = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			mat = new int[H][W];
			visited = new int[H][W];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			bfs(startH, startW, 1);
			System.out.println("#" + t + " " + ans);
			ans = 0;
		}

	}

	public static void bfs(int x, int y, int cnt) {
		Deque<int[]> deq = new ArrayDeque<>();
		visited[x][y] = 1;
		deq.offer(new int[] { x, y, cnt });

		int[] dx = { -1, 0, 1, 0 };
		int[] dy = { 0, 1, 0, -1 };

		int[] moveableCase = moveableCase(mat[x][y]);

		while (!deq.isEmpty()) {
			int xy[] = deq.pollFirst();
			x = xy[0];
			y = xy[1];
			cnt = xy[2];
			moveableCase = moveableCase(mat[x][y]);
			
//			
//			System.out.println("x,y  is"  + x + " " +  y);
//			System.out.println("cnt is " + cnt);

			if (cnt <= time)
				ans++;
			else
				return;

			for (int i = 0; i < 4; i++) {
				int newX = dx[i] + x;
				int newY = dy[i] + y;
				
				if (newX < 0 || newX > (H - 1) || newY < 0 || newY > (W - 1) || moveableCase[i] == 0)
					continue;
				if (visited[newX][newY] == 0) {
					if (i == 0 && (mat[newX][newY] == 1 || mat[newX][newY] == 2 || mat[newX][newY] == 5
							|| mat[newX][newY] == 6)) {
						visited[newX][newY] = 1;
						deq.offer(new int[] { newX, newY, cnt + 1 });
					}

					if (i == 1 && (mat[newX][newY] == 1 || mat[newX][newY] == 3 || mat[newX][newY] == 6
							|| mat[newX][newY] == 7)) {
						visited[newX][newY] = 1;
						deq.offer(new int[] { newX, newY, cnt + 1 });
					}

					if (i == 2 && (mat[newX][newY] == 1 || mat[newX][newY] == 2 || mat[newX][newY] == 4
							|| mat[newX][newY] == 7)) {
						visited[newX][newY] = 1;
						deq.offer(new int[] { newX, newY, cnt + 1 });
					}
					if (i == 3 && (mat[newX][newY] == 1 || mat[newX][newY] == 3 || mat[newX][newY] == 4
							|| mat[newX][newY] == 5)) {
						visited[newX][newY] = 1;
						deq.offer(new int[] { newX, newY, cnt + 1 });
					}
				}

			}

		}

	}

	public static int[] moveableCase(int n) {
		switch (n) {
		case 1:
			return new int[] { 1, 1, 1, 1 };
		case 2:
			return new int[] { 1, 0, 1, 0 };
		case 3:
			return new int[] { 0, 1, 0, 1 };
		case 4:
			return new int[] { 1, 1, 0, 0 };
		case 5:
			return new int[] { 0, 1, 1, 0 };
		case 6:
			return new int[] { 0, 0, 1, 1 };
		case 7:
			return new int[] { 1, 0, 0, 1 };

		}
		return null;
	}

}
