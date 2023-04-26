package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

//시간 순서대로 bfs
public class BOJ16569_화산쇄설류 {

	static int M, N, V, X, Y;
	static int[][] mat;
	static List<Volcano> volList = new ArrayList<>();
	static int curTime = 0;
	static boolean bfsDone = false;
	static Deque<int[]> spreadDeq = new LinkedList<>();
	static Deque<int[]> meDeq = new LinkedList<>();

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int[][] volVisited;
	static int[][] meVisited;
	static int[][] volArr;

	static int ansHeight;
	static int ansTime = 0;

	static class Volcano {
		int x;
		int y;
		int t;

		public Volcano(int x, int y, int t) {
			super();
			this.x = x;
			this.y = y;
			this.t = t;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		mat = new int[M + 1][N + 1];
		volVisited = new int[M + 1][N + 1];
		meVisited = new int[M + 1][N + 1];
		volArr = new int[M + 1][N + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			volList.add(new Volcano(x, y, t));
			// 화산 위치도 갈 수 없으니 화산 위치 저장하여 bfs 탐색 안하도록 제어
			volArr[x][y] = 1;
		}

		ansHeight = mat[X][Y];

		meDeq.offer(new int[] { X, Y, 0 });
		while (!bfsDone) {
			bfs();
		}

		System.out.println(ansHeight + " " + ansTime);

	}

	// 단위 시간 당 que 크기만큼 이동
	private static void bfs() {

		int size = meDeq.size();
		if (size == 0)
			bfsDone = true;

		spread();

		for (int j = 0; j < size; j++) {
			int arr[] = meDeq.pollFirst();
			int x = arr[0];
			int y = arr[1];
			int dist = arr[2];
			int height = mat[x][y];

			if (height > ansHeight) {
				ansHeight = height;
				ansTime = dist;
			}

			for (int i = 0; i < 4; i++) {
				int newx = x + dx[i];
				int newy = y + dy[i];
				if (newx < 1 || newx > M || newy < 1 || newy > N || meVisited[newx][newy] == 1
						|| volVisited[newx][newy] == 1 || volArr[newx][newy] == 1)
					continue;
				meVisited[newx][newy] = 1;
				meDeq.offer(new int[] { newx, newy, dist + 1 });
			}

		}

		curTime++;

	}

	// 단위 시간당 지속적으로 que 크기만큼 확장
	private static void spread() {
		for (int i = 0; i < volList.size(); i++) {
			Volcano vol = volList.get(i);
			if (vol.t == curTime) {
				volVisited[vol.x][vol.y] = 1;
				spreadDeq.offer(new int[] { vol.x, vol.y });
			}
		}

		int size = spreadDeq.size();
		for (int i = 0; i < size; i++) {
			int[] arr = spreadDeq.pollFirst();
			int x = arr[0];
			int y = arr[1];

			for (int j = 0; j < 4; j++) {
				int newx = x + dx[j];
				int newy = y + dy[j]; 
				if (newx < 1 || newx > M || newy < 1 || newy > N || volVisited[newx][newy] == 1)
					continue;
				volVisited[newx][newy] = 1;
				spreadDeq.offer(new int[] { newx, newy });

			}
		}

	}

}
