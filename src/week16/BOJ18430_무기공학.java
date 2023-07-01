package week16;

import java.io.*;
import java.util.*;

public class BOJ18430_무기공학 {

//5 5
//1 1 1 1 1
//1 1 1 1 1
//1 1 1 1 1
//1 1 1 1 1
//1 1 1 1 1

	static int N, M;
	static int[][] mat;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[][] realVisited;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mat = new int[N][M];

		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		

		dfs(new boolean[N][M], 0);
		System.out.println(ans);
	}

	private static void dfs(boolean[][] visited, int sum) {
		ans = Math.max(sum, ans);

		boolean isPossib = false;
		for (int i = 0; i < N; i++) {
			if (isPossib)
				break;
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) {
					checkAndCal(i, j, visited, sum);
					isPossib = true;
					break;
				}
			}
		}

	}

	private static void checkAndCal(int x, int y, boolean[][] visited, int sum) {

//		visited[x][y] = true;
		dfs(visited, sum);
//		visited[x][y] = false;
	

		for (int i = 0; i < 4; i++) {
			int newx = x + dx[i];
			int newy = y + dy[i];

			if (checkMat(newx, newy, visited))
				continue;

			for (int j = 0; j < 4; j++) {
				if (i == j)
					continue;
				int rotatex = newx + dx[j];
				int rotatey = newy + dy[j];
				if (rotatex == x && rotatey == y)
					continue;
				if (checkMat(rotatex, rotatey, visited))
					continue;
				visited[x][y] = true;
				visited[newx][newy] = true;
				visited[rotatex][rotatey] = true;

				sum += (mat[x][y] + (mat[newx][newy] * 2) + mat[rotatex][rotatey]);
				dfs(visited, sum);

				visited[x][y] = false;
				visited[newx][newy] = false;
				visited[rotatex][rotatey] = false;
				sum -= (mat[x][y] + (mat[newx][newy] * 2) + mat[rotatex][rotatey]);
			}
		}

	}

	private static boolean checkMat(int newx, int newy, boolean[][] visited) {
		return (newx < 0 || newx > N - 1 || newy < 0 || newy > M - 1 || visited[newx][newy]);
	}

}
