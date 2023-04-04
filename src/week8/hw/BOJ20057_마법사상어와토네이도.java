package week8.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20057_마법사상어와토네이도 {

	static int N;
	static int[][] mat;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int curx;
	static int cury;
	static int leftVal;
	static int dir = 0;
	static int repeat = 1;
	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		mat = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		curx = N / 2;
		cury = N / 2;

		while (repeat < N) {
			move();
		}

		int newx = curx;
		int newy = cury;

		for (int k = 0; k < repeat - 1; k++) {
			newx += dx[dir];
			newy += dy[dir];
			spread(newx, newy, dir);
		}

		System.out.println(ans);

	}

	private static void move() {
		int newx = curx;
		int newy = cury;
		for (int i = 0; i < repeat; i++) {
			newx += dx[dir];
			newy += dy[dir];
			spread(newx, newy, dir);
		}
		dir = (dir + 1) % 4;
		for (int i = 0; i < repeat; i++) {
			newx += dx[dir];
			newy += dy[dir];
			spread(newx, newy, dir);
		}
		curx = newx;
		cury = newy;
		repeat++;
		dir = (dir + 1) % 4;

	}

	private static void spread(int x, int y, int dir) {

		int newx;
		int newy;
		int newdir;
		int val = mat[x][y];
		leftVal = val;
		mat[x][y] = 0;

		newx = x + (dx[dir] * 2);
		newy = y + (dy[dir] * 2);
		cal(newx, newy, val, 0.05);

		if ((dir - 1) < 0)
			newdir = 3;
		else
			newdir = dir - 1;
		newx = x + dx[dir] + dx[newdir];
		newy = y + dy[dir] + dy[newdir];
		cal(newx, newy, val, 0.1);

		newx = x + dx[newdir];
		newy = y + dy[newdir];
		cal(newx, newy, val, 0.07);

		newx += dx[newdir];
		newy += dy[newdir];
		cal(newx, newy, val, 0.02);

		newx = x + dx[(dir + 2) % 4] + dx[newdir];
		newy = y + dy[(dir + 2) % 4] + dy[newdir];
		cal(newx, newy, val, 0.01);

		newdir = (dir + 1) % 4;
		newx = x + dx[dir] + dx[newdir];
		newy = y + dy[dir] + dy[newdir];
		cal(newx, newy, val, 0.1);

		newx = x + dx[newdir];
		newy = y + dy[newdir];
		cal(newx, newy, val, 0.07);

		newx += dx[newdir];
		newy += dy[newdir];
		cal(newx, newy, val, 0.02);

		newx = x + dx[(dir + 2) % 4] + dx[newdir];
		newy = y + dy[(dir + 2) % 4] + dy[newdir];
		cal(newx, newy, val, 0.01);

		newx = x + dx[dir];
		newy = y + dy[dir];
		if (check(newx, newy)) {
			mat[newx][newy] += leftVal;
		} else
			ans += leftVal;
	}

	private static boolean check(int newx, int newy) {
		if (newx < 0 || newx > N - 1 || newy < 0 || newy > N - 1)
			return false;
		return true;
	}

	private static void cal(int x, int y, int val, double multi) {
		int calInt = (int) (val * multi);
		leftVal -= calInt;
		if (!check(x, y)) {
			ans += calInt;
		}

		else
			mat[x][y] += calInt;
	}

}
