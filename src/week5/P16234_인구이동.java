package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class P16234_인구이동 {

	static int N;
	static int L;
	static int R;
	static int mat[][];
	static int visited[][];
	static int sum;
	static int cnt = 1;
	static int visitedNum = 1;
	static boolean moveable = true;
	static List<int[]> point;
	static int days = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		mat = new int[N][N];
		visited = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (moveable) {

			if (visitedNum > N * N) {
				moveable = false;
			}

			visited = new int[N][N];
			visitedNum = 1;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j] == 0) {
						check(i, j, visitedNum);
						int avg = sum / cnt;

						for (int[] val : point) {
							mat[val[0]][val[1]] = avg;
						}

						sum = 0;
						cnt = 1;
						visitedNum++;

					}

				}
			}

			days++;
		}

		System.out.println((days - 2));

	}

	public static void check(int x, int y, int visitedNum) {

		sum = mat[x][y];
		visited[x][y] = visitedNum;
		point = new ArrayList<int[]>();
		point.add(new int[] { x, y });
		Deque<int[]> deq = new ArrayDeque<int[]>();

		deq.offer(new int[] { x, y, visitedNum });
		int dx[] = { -1, 1, 0, 0 };
		int dy[] = { 0, 0, -1, 1 };

		while (!deq.isEmpty()) {

			int arr[] = deq.pollFirst();
			x = arr[0];
			y = arr[1];
			visitedNum = arr[2];

			for (int i = 0; i < 4; i++) {
				if (x + dx[i] < 0 || x + dx[i] > (N - 1) || y + dy[i] < 0 || y + dy[i] > (N - 1))
					continue;
				int sub = Math.abs(mat[x + dx[i]][y + dy[i]] - mat[x][y]);
				if (visited[x + dx[i]][y + dy[i]] == 0 && sub >= L && sub <= R) {
					visited[x + dx[i]][y + dy[i]] = visitedNum;
					sum += mat[x + dx[i]][y + dy[i]];
					cnt++;
					deq.offer(new int[] { x + dx[i], y + dy[i], visitedNum });
					point.add(new int[] { x + dx[i], y + dy[i] });
				}
			}
		}
	}

}
