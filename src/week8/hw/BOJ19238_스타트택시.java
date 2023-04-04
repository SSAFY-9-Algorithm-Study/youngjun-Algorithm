package week8.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ19238_스타트택시 {

	static int N;
	static int M;
	static int fuel;
	static int[][] mat;
	static int[][] visited;
	static List<Point> personList = new ArrayList<>();
	static Point minPerson;
	static int curX;
	static int curY;
	static int minDist = Integer.MAX_VALUE;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	static class Point {
		int x;
		int y;
		int destX;
		int destY;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Point(int x, int y, int destX, int destY) {
			super();
			this.x = x;
			this.y = y;
			this.destX = destX;
			this.destY = destY;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		mat = new int[N + 1][N + 1];
		visited = new int[N + 1][N + 1];

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		curX = Integer.parseInt(st.nextToken());
		curY = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			personList.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		while (personList.size() > 0) {
			for (int i = 0; i < personList.size(); i++) {
				Point curPerson = personList.get(i);
				int dist = calDist(curX, curY, curPerson.x, curPerson.y);
				if (dist < 0) {
					fuel = -1;
					break;
				}
				if (minDist >= dist) {

					if (minDist > dist) {
						minPerson = curPerson;
						minDist = dist;
					}

					else {
						if (minPerson != null && curPerson.x <= minPerson.x) {
							if (curPerson.x < minPerson.x)
								minPerson = curPerson;
							else if (curPerson.y < minPerson.y)
								minPerson = curPerson;
						} else if (minPerson == null) {
							minPerson = curPerson;
						}
					}

				}
			}
			fuel -= minDist;

			// fuel이 0보다 작을 때 처리1(없으면 nullpointerException)
			if (fuel < 0) {
				fuel = -1;
				break;
			}

			int dist = calDist(minPerson.x, minPerson.y, minPerson.destX, minPerson.destY);
			if (dist < 0) {
				fuel = -1;
				break;
			}
			fuel -= dist;

			// fuel이 0보다 작을 때 처리2(없으면 nullpointerException)
			if (fuel < 0) {
				fuel = -1;
				break;
			}

			fuel += dist * 2;
			personList.remove(minPerson);
			minDist = Integer.MAX_VALUE;
			curX = minPerson.destX;
			curY = minPerson.destY;
			minPerson = null;
		}
		System.out.println(fuel);
	}

	private static int calDist(int startx, int starty, int destx, int desty) {
		Deque<int[]> deq = new ArrayDeque<>();
		visited = new int[N + 1][N + 1];

		deq.offer(new int[] { startx, starty, 0 });
		visited[startx][starty] = 1;
		int x;
		int y;
		int dist;
		int newx;
		int newy;
		while (!deq.isEmpty()) {

			int[] arr = deq.pollFirst();
			x = arr[0];
			y = arr[1];
			dist = arr[2];

			if (x == destx && y == desty) {
				return dist;
			}

			for (int i = 0; i < 4; i++) {
				newx = x + dx[i];
				newy = y + dy[i];

				if (newx < 1 || newx > N || newy < 1 || newy > N || visited[newx][newy] == 1 || mat[newx][newy] == 1)
					continue;
				visited[newx][newy] = 1;
				deq.offer(new int[] { newx, newy, dist + 1 });
			}
		}

		return -1;
	}
}