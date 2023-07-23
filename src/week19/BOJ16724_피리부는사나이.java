package week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16724_피리부는사나이 {
	static int N, M, cnt;
	static int[] dx = { -1, 0, 1, 0 }; // ULDR
	static int[] dy = { 0, -1, 0, 1 };
	static int[][] visited;
	static char[][] mat;
	static int visitVal = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mat = new char[N][M];
		visited = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				mat[i][j] = str.charAt(j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if ((visited[i][j] == 0)) {
					track(i, j);
					visitVal++;
				}
			}
		}

		System.out.println(cnt);

	}

	private static void track(int i, int j) {
		visited[i][j] = visitVal;
		forwardTrack(i, j);
//		backwardTrack(i, j);

	}

	// 앞으로 추적
	private static void forwardTrack(int x, int y) {
		int dir = getDxDy(mat[x][y]);
		int newx = x + dx[dir];
		int newy = y + dy[dir];
		// 루프가 돌거나 벽에 만나면 종료
		if (wall(newx, newy) || visited[newx][newy] == visitVal) {
			cnt++;
			return;
		}
		// 다른 visited 값이라는 건 같은 루프에 있다는 의미이므로 cnt++ 안시킴
		if (visited[newx][newy] != 0)
			return;
		visited[newx][newy] = visitVal;
		forwardTrack(newx, newy);
	}

	// 뒤로 추적
	// 뒤로 추적할 때는 다른 아직 접근 안했을 수는 있어도 다른 visited값이 들어와 있을 리는 없음
	// 이미 추적이 끝까지 되었을거니까
//	private static void backwardTrack(int x, int y) {
//		for (int i = 0; i < 4; i++) {
//			int newx = x + dx[i];
//			int newy = y + dy[i];
//
//			if (wall(newx, newy))
//				continue;
//			if (visited[newx][newy] != 0)
//				continue;
//			if ((getDxDy(mat[newx][newy]) + 2) % 4 == i) {
//
//				visited[newx][newy] = visitVal;
//				backwardTrack(newx, newy);
//			}
//		}
//
//	}

	// 현재 배열의 방향
	private static int getDxDy(char dir) {
		switch (dir) {
		case 'U':
			return 0;
		case 'L':
			return 1;
		case 'D':
			return 2;
		case 'R':
			return 3;
		default:
			return 0;
		}
	}

	private static boolean wall(int x, int y) {
		return (x < 0 || x > N - 1 || y < 0 || y > M - 1);
	}

}































