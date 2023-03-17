package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SWEA1226 {

	static int T = 10;
	static int w = 16;
	static int arr[][] = new int[w][w];
	static Deque<int[]> deq = new ArrayDeque<int[]>();
	static int possible = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int i = 1; i < T + 1; i++) {
			int test = Integer.parseInt(br.readLine());
			for (int j = 0; j < w; j++) {
				String line = br.readLine();
				for (int j2 = 0; j2 < w; j2++) {
					arr[j][j2] = (line.charAt(j2)) - '0';
				}
			}

			for (int j = 0; j < w; j++) {
				for (int j2 = 0; j2 < w; j2++) {
					if (arr[j][j2] == 2) {
						bfs(j, j2);
						System.out.println("#" + i + " " + possible);
						break;
					}

				}
				if (possible == 1) {
					possible = 0;
					break;
				}

			}
		}
	}

	public static void bfs(int x, int y) {
		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { -1, 1, 0, 0 };

		int[] xy = { x, y };
		deq.add(xy);

		while (!deq.isEmpty()) {
			int[] popped = deq.poll();

			int newX = popped[0];
			int newY = popped[1];

			if (arr[newX][newY] == 3) {
				possible = 1;
				return;
			}
			
			arr[newX][newY] = 1;

			for (int i = 0; i < 4; i++) {
				if (dx[i] + newX < 0 || dx[i] + newX > w - 1 || dy[i] + newY < 0 || dy[i] + newY > w - 1)
					continue;
				if (arr[newX + dx[i]][newY + dy[i]] == 0 || arr[newX + dx[i]][newY + dy[i]] == 3) {
					int[] newXnewY = { newX + dx[i], newY + dy[i] };
					deq.add(newXnewY);
				}

			}

		}
	}

}
