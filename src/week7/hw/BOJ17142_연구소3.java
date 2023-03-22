package week7.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17142_연구소3 {

	static int N;
	static int M;
	static int mat[][];
	static int visited[][];
	static int temp[][];
	static int vir;
	static int minTime = 0;
	static int ans = Integer.MAX_VALUE;
	static Deque<Point> deq = new ArrayDeque<>();

	static class Point {
		int x;
		int y;
		int dist;

		public Point(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static List<Point> virList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mat = new int[N][N];
		temp = new int[N][N];
		visited = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int val = Integer.parseInt(st.nextToken());
				
				//bfs 거리와 햇갈리지 않기 위해 바이러스, 벽을 음수로 저장
				if (val == 2) {
					val = -2;
					vir++;
					virList.add(new Point(i, j, 0));
				} else if (val == 1) {
					val = -1;
				}
				temp[i][j] = val;
				mat[i][j] = val;
			}
		}

		combi(new int[M], 0, 0);

		if (ans == Integer.MAX_VALUE)
			ans = -1;
		System.out.println(ans);

	}

	
	//조합 선택
	public static void combi(int[] res, int level, int begin) {
		if (level == M) {
			//선택된 조합을 큐에 담음
			for (int i = 0; i < res.length; i++) {
				Point chosenPoint = virList.get(res[i]);
				visited[chosenPoint.x][chosenPoint.y] = 1;
				deq.offer(chosenPoint);
			}
			
			
			//선택된 조합으로 bfs
			bfs();

			
			//0이 남아있는 경우 안되는 것으로 판별 (되도록 배열 다 순회하지 말고 bfs과정에서 구하기)
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (mat[i][j] == 0) {
						minTime = -1;
						break;
					}
				}
			}
			
			//여러가지 경우를 따지기 위해 원래의 입력값으로 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					mat[i][j] = temp[i][j];

				}
			}

			if (minTime != -1 && minTime < ans)
				ans = minTime;
			
			//초기화
			visited = new int[N][N];
			minTime = 0;
			deq.clear();

			return;
		}

		else {
			for (int i = begin; i < vir; i++) {
				res[level] = i;
				combi(res, level + 1, i + 1);
			}
		}
	}

	public static void bfs() {

		while (!deq.isEmpty()) {
			Point point = deq.pollFirst();
			int x = point.x;
			int y = point.y;
			int dist = point.dist;

			for (int i = 0; i < 4; i++) {
				int newx = x + dx[i];
				int newy = y + dy[i];

				if (newx < 0 || newx > N - 1 || newy < 0 || newy > N - 1 || mat[newx][newy] == -1
						|| visited[newx][newy] == 1)
					continue;
				visited[newx][newy] = 1;
				
				//바이러스위치라면 거리가 늘어나지 않음
				if (mat[newx][newy] != -2) {
					//거리 늘어나면 최대거리 업데이트
					mat[newx][newy] = dist + 1;
					if(dist+1 > minTime)
						minTime = dist+1;
				}

				deq.offer(new Point(newx, newy, dist + 1));
			}
		}
	}

}
