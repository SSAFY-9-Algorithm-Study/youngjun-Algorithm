package week16;

import java.util.*;
import java.io.*;

public class BOJ16954_움직이는미로탈출 {

	static char[][] mat = new char[8][8];
	static boolean visited[][][] = new boolean[8][8][9];
	static boolean possible;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1, 0 };
	static int curLev;

	static class Node {
		int x;
		int y;
		int lev;

		public Node(int x, int y, int lev) {
			super();
			this.x = x;
			this.y = y;
			this.lev = lev;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 8; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				mat[i][j] = str.charAt(j);
			}
		}

		bfs();
		System.out.println(possible ? 1 : 0);
	}

	private static void bfs() {
		Queue<Node> que = new LinkedList<>();
		que.add(new Node(7, 0, 0));
		while (!que.isEmpty()) {
			Node node = que.poll();
			
			if ((node.x == 0 && node.y == 7) || node.lev == 8) {
				possible = true;
				break;
			}

			if (curLev != node.lev) {
				curLev = node.lev;
				shift();
			}

			if (mat[node.x][node.y] == '#')
				continue;

			for (int i = 0; i < 9; i++) {
				int newx = node.x + dx[i];
				int newy = node.y + dy[i];

				if (newx < 0 || newx > 7 || newy < 0 || newy > 7 || visited[newx][newy][node.lev + 1]
						|| mat[newx][newy] == '#')
					continue;
				visited[newx][newy][node.lev + 1] = true;
				que.add(new Node(newx, newy, node.lev + 1));
			}

		}

	}

	static void shift() {
		for (int i = 7; i >= 1; i--) {
			for (int j = 0; j < 8; j++) {
				mat[i][j] = mat[i - 1][j];
			}
		}

		for (int i = 0; i < 8; i++) {
			mat[0][i] = '.';
		}
	}

	static void print() {
		for (int i = 0; i < 8; i++) {
			System.out.println();
			for (int j = 0; j < 8; j++) {
				System.out.print(mat[i][j]);
			}
		}
	}

}
