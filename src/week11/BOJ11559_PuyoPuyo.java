package week11;

import java.util.*;
import java.io.*;

public class BOJ11559_PuyoPuyo {

	static int H = 12;
	static int W = 6;
	static char[][] arr = new char[H][W];
	static boolean breakable = true;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int ans = 0;
	static int visitedSize = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < H; i++) {
			String str = br.readLine();
			for (int j = 0; j < W; j++) {
				arr[i][j] = str.charAt(j);
			}
		}

		operate(new int[H][W], arr, 0);
		System.out.println(ans);

	}

	private static void operate(int[][] visited, char[][] arr, int times) {
		// operatable : 4개 이상이 모여있는 경우가 있는지
		boolean opearatable = false;
		visited = new int[H][W];
		ans = Math.max(ans, times);
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (arr[i][j] != '.') {
					if (visited[i][j] != 1) {
						visitedSize = 1;
						// visited는 전체에서 내가 탐색한 영역
						// newVisited는 4개가 붙어있어서 '.'이 되도록 표시한 영

						int[][] newVisited = bfs(visited, i, j, arr);
						if (visitedSize >= 4) {
							// newVisited인 영역은 .으로 바꿔준다.
							// 4개 붙어있는 영역이 존재하므로 operatable = true
							visitedToDot(arr, newVisited);
							opearatable = true;
						}
					}

				}
			}
		}

		// operatable 한 경우 iterate 해주고 연쇄(times+1)하고 다음 operate 진행
		if (opearatable) {
			char[][] newArr = iterateChars(arr);
			operate(new int[H][W], newArr, times + 1);

		}

	}

	private static char[][] iterateChars(char[][] arr) {

		char[][] mat = new char[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				mat[i][j] = arr[i][j];
			}
		}

		for (int i = H - 1; i >= 0; i--) {
			for (int j = 0; j < W; j++) {
				char originChar = mat[i][j];
				int curX = i;

				while (curX + 1 <= H - 1 && mat[curX + 1][j] == '.') {
					curX++;
				}

				mat[i][j] = '.';
				mat[curX][j] = originChar;

			}
		}

		return mat;
	}

	private static void visitedToDot(char[][] arr2, int[][] visited) {

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (visited[i][j] == 1)
					arr2[i][j] = '.';
			}
		}

	}

	private static int[][] bfs(int[][] visited, int i, int j, char[][] arr) {

		Deque<int[]> deq = new LinkedList<>();
		int[][] myVisited = new int[H][W];
		myVisited[i][j] = 1;
		visited[i][j] = 1;
		char curChar = arr[i][j];
		deq.offer(new int[] { i, j });

		while (!deq.isEmpty()) {
			int[] arr2 = deq.pollFirst();
			int x = arr2[0];
			int y = arr2[1];

			for (int k = 0; k < 4; k++) {
				int newx = x + dx[k];
				int newy = y + dy[k];
				if (newx < 0 || newx > H - 1 || newy < 0 || newy > W - 1 || myVisited[newx][newy] == 1
						|| arr[newx][newy] != curChar)
					continue;
				myVisited[newx][newy] = 1;
				visited[newx][newy] = 1;
				deq.offer(new int[] { newx, newy });
				visitedSize++;

			}
		}
		return myVisited;

	}

}
