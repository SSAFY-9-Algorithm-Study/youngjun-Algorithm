package week13;

import java.io.*;
import java.util.*;

public class BOJ3109_빵집2 {

	static int H;
	static int W;
	static char[][] mat;
	static int[][] visited;
	static boolean flag;
	static Node endNode;
	static int ans;

	static int[] dx = { -1, 0, 1 };
	static int[] dy = { 1, 1, 1 };

	static class Node {
		int x;
		int y;
		Node lastNode;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		mat = new char[H][W];
		visited = new int[H][W];
		for (int i = 0; i < H; i++) {
			String str = br.readLine();
			for (int j = 0; j < W; j++) {
				mat[i][j] = str.charAt(j);
			}
		}

		for (int i = 0; i < H; i++) {
			Node node = new Node();
			node.x = i;
			node.y = 0;
			endNode = null;
			flag = false;
			dfs(node);

			if (flag) {
				ans++;
			}
		}
		System.out.println(ans);

	}

	// 그리디하게 깊이 우선으로 탐색 후 (오른 위가 제일 우선이어야 )
	// 안되는 경우에는 가운데, 그다음 밑 순서가 되어야 하고, 안된다는 것은 다른 파이프에서도 안된다는 의미
	private static void dfs(Node node) {
		if (flag)
			return;
		visited[node.x][node.y] = 1;
		if (node.y == W - 1) {
			flag = true;
			endNode = node;
			return;
		}
		int x = node.x;
		int y = node.y;
		for (int i = 0; i < 3; i++) {
			int newx = x + dx[i];
			int newy = y + dy[i];

			if (newx < 0 || newx > H - 1 || newy < 0 || newy > W - 1 || mat[newx][newy] == 'x'
					|| visited[newx][newy] == 1)
				continue;
			// 여기서 visited 처리를 하면 이미 마지막 칸에 도달했는데도 flag를 감지하기 전이라 visited
			// 를 표시하는 경우가 생
			Node newNode = new Node();
			newNode.x = newx;
			newNode.y = newy;
			newNode.lastNode = node;
			dfs(newNode);
		}
	}
}