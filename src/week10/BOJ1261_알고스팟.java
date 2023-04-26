package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ1261_알고스팟 {
	static int W;
	static int H;
	static int[][] mat;
	static int[][][] visited;
	static int oneCnt;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		mat = new int[H][W];

		for (int i = 0; i < H; i++) {
			String str = br.readLine();
			for (int j = 0; j < W; j++) {
				int val = str.charAt(j) - '0';
				mat[i][j] = val;
			}
		}
		oneCnt = H + W;
		// 벽을 깬 횟수에 따라 나눌 수 있도록 3차원 visited 생성
		// 최대 벽을 깨는 횟수는 H+W (벽의 수만큼 생성하면 메모리 초과 발생)
		visited = new int[oneCnt][H][W];

		bfs();
		System.out.println(ans);

	}

	private static void bfs() {
		Deque<int[]> deq = new LinkedList<>();
		int firstBreak = 0;
		if (mat[0][0] == 1)
			firstBreak++;
		deq.offer(new int[] { 0, 0, firstBreak });

		while (!deq.isEmpty()) {
			int[] arr = deq.pollFirst();
			int x = arr[0];
			int y = arr[1];
			int breakCnt = arr[2];

			if (x == H - 1 && y == W - 1) {
				ans = Math.min(ans, breakCnt);
			}

			for (int i = 0; i < 4; i++) {
				int newx = x + dx[i];
				int newy = y + dy[i];

				if (newx < 0 || newx > H - 1 || newy < 0 || newy > W - 1)
					continue;
				// 벽을 깨는 횟수가 최대를 넘어가지 않는다면
				// 벽을 깨는 횟수가 이전에 벽을 깬 횟수보다 커도 저장해야 하는가??
				if (mat[newx][newy] == 1 && breakCnt + 1 < oneCnt && visited[breakCnt + 1][newx][newy] == 0) {
					visited[breakCnt + 1][newx][newy] = 1;
					deq.offer(new int[] { newx, newy, breakCnt + 1 });
				} else if (mat[newx][newy] == 0 && visited[breakCnt][newx][newy] == 0) {
					visited[breakCnt][newx][newy] = 1;
					deq.offer(new int[] { newx, newy, breakCnt });
				}
			}
		}

	}

}
