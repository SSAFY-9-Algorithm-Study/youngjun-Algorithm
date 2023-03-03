package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.StringTokenizer;


public class SWEA1861_정사각형방 {

	static int T;
	static int N;
	static int mat[][];
	static int max = 0;

	static class maxRoomNo {
		int max;
		int x;
		int y;

		public maxRoomNo(int max, int x, int y) {
			super();
			this.max = max;
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int i = 1; i < T + 1; i++) {
			N = Integer.parseInt(br.readLine());
			mat = new int[N][N];

			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int j2 = 0; j2 < N; j2++) {
					mat[j][j2] = Integer.parseInt(st.nextToken());
				}
			}


			ArrayList<maxRoomNo> maxRoomList = new ArrayList<maxRoomNo>();

			for (int j = 0; j < N; j++) {
				for (int j2 = 0; j2 < N; j2++) {
					int bfsRes = bfs(j, j2, 1);
					if (max <= bfsRes) {
						max = bfsRes;
						maxRoomList.add(new maxRoomNo(bfsRes, j, j2));
					}
				}
			}

			maxRoomList.sort(new Comparator<maxRoomNo>() {

				@Override
				public int compare(maxRoomNo o1, maxRoomNo o2) {
					if (o1.max == o2.max) {
						return mat[o1.x][o1.y] - mat[o2.x][o2.y];
					} else
						return o2.max - o1.max;
				}
			});
			

			System.out.println(
					"#" + i + " " + mat[maxRoomList.get(0).x][maxRoomList.get(0).y] + " " + maxRoomList.get(0).max);
			max = 0;

		}
	}

	public static int bfs(int x, int y, int cnt) {
		Deque<int[]> deq = new ArrayDeque<int[]>();
		deq.offer(new int[] { x, y, cnt });

		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		while (!deq.isEmpty()) {
			int arr[] = deq.pollFirst();
			x = arr[0];
			y = arr[1];
			cnt = arr[2];

			for (int i = 0; i < 4; i++) {
				if (x + dx[i] < 0 || x + dx[i] > (N - 1) || y + dy[i] < 0 || y + dy[i] > (N - 1))
					continue;
				if (mat[x + dx[i]][y + dy[i]] - mat[x][y] == 1) {
					deq.offer(new int[] { x + dx[i], y + dy[i], cnt + 1 });
				}
			}

		}
		return cnt;

	}

}
