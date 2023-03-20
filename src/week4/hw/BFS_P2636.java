package week4.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BFS_P2636 {

	static int arr[][];
	static int visited[][];
	static Deque<int[]> deq = new ArrayDeque<>();
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
		cntList.add(cnt);

		while (cnt > 0) {

			for (int i = 1; i < h - 1; i++) {
				for (int j = 1; j < w - 1; j++) {
					if (arr[i][j] == 1) {
						bfs(i, j);
						deq.clear();
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

			for (int i = 1; i < h - 1; i++) {
				for (int j = 1; j < w - 1; j++) {
					if (arr[i][j] == 2)
						arr[i][j] = 0;
				}

			}

		}

		System.out.println(cntList.size()-1);
		if(cntList.size()>=2) {
			System.out.println(cntList.get(cntList.size() - 2));
		}
		else
			System.out.println(cntList.get(0));
			
		

	}

	public static void bfs(int x, int y) {
		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { -1, 1, 0, 0 };

		int start[] = { x, y };
		deq.add(start);
		while (!deq.isEmpty()) {
			x = deq.getFirst()[0];
			y = deq.getFirst()[1];
			deq.pollFirst();
			if (x == h - 2 || x == 1 || y == w - 2 || y == 1) {
				melt = true;
				return;
			}

			for (int i = 0; i < 4; i++) {
				if (x + dx[i] > (h - 2) || x + dx[i] < 1 || y + dx[i] > (w - 2) || y + dy[i] < 1)
					continue;
				if (arr[x + dx[i]][y + dy[i]] == 0 && visited[x + dx[i]][y + dy[i]] == 0) {
					visited[x + dx[i]][y + dy[i]] = 1;
					int arr[] = { x + dx[i], y + dy[i] };
					deq.add(arr);
				}

			}

		}

	}

}
