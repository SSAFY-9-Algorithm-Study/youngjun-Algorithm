package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ16236_아기상어 {

	static List<Integer> eatable = new ArrayList<>();
	static int N;
	static int mat[][];
	static int visited[][];
	static ArrayList<int[]> possibleXY;
	static boolean foundFish = false;
	static int lastMove;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		mat = new int[N][N];
		visited = new int[N][N];
		possibleXY = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}

		}

		for (int i = 1; i <= 6; i++) { // 인덱스가 곧 레벨, 인덱스의 값이 현재 상어가 먹을 수 있는 값
			for (int j = 0; j < i + 1; j++) {
				eatable.add(i);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (mat[i][j] == 9) {
					mat[i][j] = 0; // 9가 있던 자리를 0으로 초기화
					bfs(i, j, 0, 0);
				}
			}

		}

		System.out.println(lastMove);

	}

	public static void bfs(int x, int y, int curEatable, int move) {
		Deque<int[]> deq = new ArrayDeque<>();
		deq.offer(new int[] { x, y, curEatable, move });
		visited[x][y] = 1;
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		while (!deq.isEmpty()) {
			int[] arr = deq.pollFirst();
			x = arr[0];
			y = arr[1];
			curEatable = arr[2];
			move = arr[3];

			if (curEatable > eatable.size() - 1)
				curEatable = eatable.size() - 1;

			if (mat[x][y] <= eatable.get(curEatable) && mat[x][y] > 0) {
				possibleXY.add(new int[] { x, y, move });
				foundFish = true;
			}

			if (!foundFish) {

				for (int i = 0; i < 4; i++) {
					int newX = x + dx[i];
					int newY = y + dy[i];

					if (newX < 0 || newX > (N - 1) || newY < 0 || newY > (N - 1))
						continue;
					if (visited[newX][newY] == 0 && mat[newX][newY] <= eatable.get(curEatable) + 1) {
						visited[newX][newY] = 1;
						deq.offer(new int[] { newX, newY, curEatable, move + 1 });
					}
				}

			}

		}

		possibleXY.sort(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[2] != o2[2])
					return o1[2] - o2[2];
				else if (o1[0] != o2[0]) {
					return o1[0] - o2[0];
				} else {
					return o1[1] - o2[1];
				}
			}
		});

		if (possibleXY.size() == 0)
			return;
		else {
			int newX = possibleXY.get(0)[0];
			int newY = possibleXY.get(0)[1];
			int newMove = possibleXY.get(0)[2];
			lastMove = newMove;
			mat[newX][newY] = 0;
			visited = new int[N][N];
			possibleXY = new ArrayList<>();
			foundFish = false;
			bfs(newX, newY, curEatable + 1, newMove);
		}

	}

}
