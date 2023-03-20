package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ23288_주사위 {

	static class Dice {
		int top = 1;
		int down = 6;
		int front = 5;
		int back = 2;
		int left = 4;
		int right = 3;

		public Dice() {
		}
	}

	static int H;
	static int W;
	static int moves;
	static int mat[][];
	static int visited[][];
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	static int curx;
	static int cury;
	static int curdir = 1; // 1: 동, 2: 서, 3: 남, 4: 북
	static int movable = 1;
	static Deque<int[]> deq = new ArrayDeque<>();
	static Dice dice = new Dice();
	static int score = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		moves = Integer.parseInt(st.nextToken());
		mat = new int[H][W];
		visited = new int[H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (moves > 0) {
			moveAndCal();
			moves--;
		}

		System.out.println(score);
	}

	public static void moveAndCal() {
		// 구를 수 있는 지 보고 방향 확정
		if ((curdir == 1 && cury == W - 1) || (curdir == 2 && cury == 0) || (curdir == 3 && curx == H - 1)
				|| (curdir == 4 && curx == 0)) {
			if (curdir % 2 == 0)
				curdir--;
			else
				curdir++;
		}

		roll();

		visited[curx][cury] = 1;
		deq.offer(new int[] { curx, cury, 1 });
		levelCal();
		visited = new int[H][W];
		score += (movable * mat[curx][cury]);
		movable = 1;

		dirCal();

	}

	public static void roll() {
		int temp;
		if (curdir == 1) {
			temp = dice.top;
			dice.top = dice.left;
			dice.left = dice.down;
			dice.down = dice.right;
			dice.right = temp;
			cury++;
		}
		if (curdir == 2) {
			temp = dice.top;
			dice.top = dice.right;
			dice.right = dice.down;
			dice.down = dice.left;
			dice.left = temp;
			cury--;
		}
		if (curdir == 3) {
			temp = dice.top;
			dice.top = dice.back;
			dice.back = dice.down;
			dice.down = dice.front;
			dice.front = temp;
			curx++;
		}
		if (curdir == 4) {
			temp = dice.top;
			dice.top = dice.front;
			dice.front = dice.down;
			dice.down = dice.back;
			dice.back = temp;
			curx--;
		}

	}

	public static void levelCal() {

		while (!deq.isEmpty()) {
			int arr[] = deq.pollFirst();
			int x = arr[0];
			int y = arr[1];

			for (int i = 0; i < 4; i++) {
				int newx = x + dx[i];
				int newy = y + dy[i];

				if (newx < 0 || newx > H - 1 || newy < 0 || newy > W - 1)
					continue;
				if (mat[curx][cury] == mat[newx][newy] && visited[newx][newy] == 0) {
					visited[newx][newy] = 1;
					movable++;
					deq.offer(new int[] { newx, newy });
				}

			}
		}

	}

	public static void dirCal() {

		int A = dice.down;
		int B = mat[curx][cury];
		int newdir = curdir;
		if (A > B) {
			if (curdir == 1)
				newdir = 3;
			if (curdir == 2)
				newdir = 4;
			if (curdir == 3)
				newdir = 2;
			if (curdir == 4)
				newdir = 1;
		} else if (A < B) {
			if (curdir == 1)
				newdir = 4;
			if (curdir == 2)
				newdir = 3;
			if (curdir == 3)
				newdir = 1;
			if (curdir == 4)
				newdir = 2;
		}
		curdir = newdir;
	}

}
