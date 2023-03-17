package week6.hw;

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
				bfs(i, j, 1, mat[i][j]);
				visited = new int[H][W];
			}

			System.out.println("ans is " + ans);

		}
	}

	public static void bfs(int x, int y, int level, int sum) {
		Deque<int[]> deq = new ArrayDeque<int[]>();
		deq.offer(new int[] { x, y, level, sum });

		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		while (!deq.isEmpty()) {
			int arr[] = deq.pollFirst();
			x = arr[0];
			y = arr[1];
			level = arr[2];
			sum = arr[3];

			if (level == 3) {
				System.out.println(sum);
				
				
				
			} else {
				for (int i = 0; i < 4; i++) {
					int newx = x + dx[i];
					int newy = y + dy[i];
					if (newx < 0 || newx > (H - 1) || newy < 0 || newy > (W - 1))
						continue;
					if (visited[newx][newy] == 0) {
						visited[newx][newy] = 1;
						deq.offer(new int[] { newx, newy, level + 1, sum + mat[newx][newy] });
					}
				}
			}

		}
	}

}
