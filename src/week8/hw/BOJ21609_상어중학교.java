package week8.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ21609_상어중학교 {

	static int N;
	static int M;
	static int[][] mat;
	static int[][] visited;

	// bfs 영역 기록, 회전 배열 담아둘 임시 배열
	static int[][] temp;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	// bfs마다 갱신할 최대 크기값
	static int size;
	// bfs마다 갱신할 최대 기준
	static Point standPoint = new Point(-1, -1);
	// bfs마다 갱신할 무지개 수
	static int rainbowCnt;
	static int ans;

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	// 찾은 영역 비교용 클래스 + comparable 구현
	static class BlockGroup implements Comparable<BlockGroup> {
		int size;
		int rainbowCnt;
		int x;
		int y;
		int[][] block;

		public BlockGroup(int size, int rainbowCnt, int x, int y, int[][] block) {
			super();
			this.size = size;
			this.rainbowCnt = rainbowCnt;
			this.x = x;
			this.y = y;
			this.block = block;
		}

		@Override
		public int compareTo(BlockGroup o) {
			if (this.size != o.size)
				return o.size - this.size;
			if (this.rainbowCnt != o.rainbowCnt)
				return o.rainbowCnt - this.rainbowCnt;
			if (this.x != o.x)
				return o.x - this.x;
			return o.y - this.y;
		}
	}

	static List<BlockGroup> groupList = new ArrayList<>();
	static Deque<Point> deq = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mat = new int[N][N];
		visited = new int[N][N];
		temp = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {

			// init
			groupList.clear();
			visited = new int[N][N];
			size = 0;

			// bfs 탐
			for (int i = 0; i <= N - 1; i++) {
				for (int j = 0; j <= N - 1; j++) {
					if (visited[i][j] == 0 && mat[i][j] > 0) {

						// init
						deq.clear();
						temp = new int[N][N];
						int[] bfsRes = bfs(i, j);
						int[][] arr = new int[N][N];

						// 찾은 영역의 크기가 size 이상인 경우에만 blockGroup으로 생성하여 리스트에 추
						if (bfsRes[0] >= size) {
							size = bfsRes[0];
							for (int k = 0; k < N; k++) {
								for (int k2 = 0; k2 < N; k2++) {
									arr[k][k2] = temp[k][k2];
								}
							}
							groupList.add(new BlockGroup(bfsRes[0], bfsRes[1], i, j, arr));
						}
						temp = new int[N][N];
					}
				}
			}

			// 들어온 group이 없으면 답 반환
			if (groupList.size() == 0)
				break;
			// 정렬하여 첫번째 인덱스값 가져옴
			Collections.sort(groupList);
			BlockGroup blockGroup = groupList.get(0);

			// 가장 최대 사이즈를 가지는 값이 1이면 답 반환
			if (blockGroup.size == 1)
				break;
			temp = blockGroup.block;
			size = blockGroup.size;
			ans += Math.pow(size, 2);
			// 답에 값 추가 및 빈 공간 -2로 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (temp[i][j] == 1)
						mat[i][j] = -2;
				}
			}
			gravity();
			rotate();
			gravity();
		}

		System.out.println(ans);

	}

	private static void rotate() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[N - 1 - j][i] = mat[i][j];
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				mat[i][j] = temp[i][j];
			}
		}
	}

	// 빈공간(-2)가 밑행에 있는 경우 계속 밑으로 가서 인덱스 가져옴
	// 마지막 -2가 있는 좌표와 현재 좌표를 swap
	private static void gravity() {
		for (int j = 0; j < N; j++) {
			// 행의 밑에서부터 갱신해야됨
			for (int i = N - 1; i >= 0; i--) {
				if (mat[i][j] >= 0) {
					int val = mat[i][j];
					int newi = i;
					while (newi + 1 < N && mat[newi + 1][j] == -2) {
						newi++;
					}
					if (newi != i) {
						mat[newi][j] = val;
						mat[i][j] = -2;
					}
				}
			}
		}
	}

	private static int[] bfs(int x, int y) {
		int size = 1;
		int rainbowCnt = 0;
		int originx = x;
		int originy = y;
		temp[x][y] = 1;
		visited[x][y] = 1;
		Point point = new Point(x, y);
		deq.offer(point);
		while (!deq.isEmpty()) {
			point = deq.pollFirst();

			x = point.x;
			y = point.y;

			for (int i = 0; i < 4; i++) {
				int newx = x + dx[i];
				int newy = y + dy[i];
				// 같은 숫자이거나 0인경우에만 이동
				if (newx < 0 || newx > N - 1 || newy < 0 || newy > N - 1 || visited[newx][newy] == 1
						|| mat[newx][newy] < 0 || (mat[newx][newy] > 0 && mat[newx][newy] != mat[originx][originy]))
					continue;
				size++;

				temp[newx][newy] = 1;
				visited[newx][newy] = 1;
				if (mat[newx][newy] == 0)
					rainbowCnt++;
				deq.offer(new Point(newx, newy));
			}
		}

		// 0인 경우에는 visited를 다시 0으로 초기화 : 무지개 영역은 다른 숫자로 이뤄진 bfs 영역에서도 접근 가능하니까
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (mat[i][j] == 0)
					visited[i][j] = 0;
			}
		}
		// 총 영역과 무지개 숫자 반환
		return new int[] { size, rainbowCnt };
	}
}
