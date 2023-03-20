package week4.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BFS_P2573 {

	static int arr[][];
	static int tempArr[][];
	static int visited[][];
	static int h;
	static int w;
	static Boolean divided = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		arr = new int[h][w];
		tempArr = new int[h][w];
		visited = new int[h][w];

		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				int val = Integer.parseInt(st.nextToken());
				arr[i][j] = val;
				tempArr[i][j] = val;
			}
		}
		
		int year = 0;
		while (!divided) {
			
			int cnt = 0;
			int bfsCnt = 0;
			boolean foundOne = false;
			for (int i = 1; i < h - 1; i++) {
				for (int j = 1; j < w - 1; j++) {
					if (tempArr[i][j] > 0) {
						shrink(i, j); // 녹이기
						if (tempArr[i][j] > 0)
							cnt++;// 녹여도 0이 아닌 경우 카운트
					}

				}
			}

			if(cnt==0) {
				year=0;
				break;
			}
			
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					arr[i][j] = tempArr[i][j];
				}
			}
			

			for (int i = 1; i < h - 1; i++) {
				if (foundOne) break;
					for (int j = 1; j < w - 1; j++) {
						if (arr[i][j] > 0) {
							bfsCnt = bfs(i, j);
							visited = new int[h][w]; //bfs 돌때마다 visited 초기화 주의!!!!!!!!!
							foundOne = true;
							break;
						}
					}

			}
			year++;
			
			if(cnt!=bfsCnt) {
				divided=true;
			}

		}
		
		System.out.println(year);

	}

	public static void shrink(int x, int y) {
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		for (int i = 0; i < 4; i++) {
			if (dx[i] + x < 0 || dx[i] + x > (h - 1) || dy[i] + y < 0 || dy[i] + y > (w - 1))
				continue;
			if (arr[dx[i] + x][dy[i] + y] == 0 && tempArr[x][y] > 0)
				tempArr[x][y] -= 1;

		}
	}

	public static int bfs(int x, int y) {
		Deque<int[]> deq = new ArrayDeque<>();
		visited[x][y] = 1;
		int visitCnt = 1;
		int[] newArr = { x, y };
		deq.offer(newArr);
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		while (!deq.isEmpty()) {
			newArr = deq.poll();
			x = newArr[0];
			y = newArr[1];
			for (int i = 0; i < 4; i++) {
				if (dx[i] + x < 1 || dx[i] + x > (h - 2) || dy[i] + y < 1 || dy[i] + y > (w - 2))
					continue;
				if (visited[dx[i] + x][dy[i] + y] == 0 && arr[dx[i] + x][dy[i] + y] != 0) {
					visited[dx[i] + x][dy[i] + y] = 1;
					visitCnt++;
					deq.offer(new int[] { dx[i] + x, dy[i] + y });
				}

			}

		}
		return visitCnt;
	}

}
