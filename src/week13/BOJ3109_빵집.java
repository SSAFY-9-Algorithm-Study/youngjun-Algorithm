package week13;

import java.io.*;
import java.util.*;

//5 6
//.xx...
//..x...
//......
//...x..
//...x..

public class BOJ3109_빵집 {

	static int H;
	static int W;
	static char[][] mat;
	static int[][] visited;
	static int[][] bfsVisited;
	static boolean flag;
	static Node endNode;
	static int ans;
	static boolean possible;

	static int[] dx = { -1, 0, 1 };
	static int[] dy = { 1, 1, 1 };

	static class Node {
		int x;
		int y;
		Node lastNode;
	}

	static Deque<Node> deq = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		mat = new char[H][W];
		visited = new int[H][W];
		bfsVisited = new int[H][W];
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
			deq.clear();
			endNode = null;

			for (int j = 0; j < H; j++) {
				for (int j2 = 0; j2 < W; j2++) {
					bfsVisited[j][j2] = 0;
				}
			}
			possible = false;

			bfs(node);

//				System.out.println("res is not null");
			if (possible)
				ans++;
			
			checkWay(endNode);
			System.out.println();
			System.out.println("ans is " + ans);
			System.out.println();
			
			for (int j = 0; j < H; j++) {
				System.out.println();
				for (int j2 = 0; j2 < W; j2++) {
					System.out.print(visited[j][j2]);
				}
			}

		}
		System.out.println(ans);

	}

	private static void bfs(Node node) {
		deq.add(node);

		while (!deq.isEmpty()) {
			Node curNode = deq.pollFirst();
			int x = curNode.x;
			int y = curNode.y;
			endNode = curNode;

			if (curNode.y == W - 1) {
				possible = true;
				endNode = curNode;
				break;
			}

			for (int i = 0; i < 3; i++) {
				int newx = x + dx[i];
				int newy = y + dy[i];

				if (newx < 0 || newx > H - 1 || newy < 0 || newy > W - 1 || mat[newx][newy] == 'x'
						|| visited[newx][newy] == 1 || bfsVisited[newx][newy] == 1)
					continue;
				bfsVisited[newx][newy] = 1;
				Node newNode = new Node();
				newNode.x = newx;
				newNode.y = newy;
				newNode.lastNode = curNode;
				deq.offer(newNode);
			}
		}
	}

	private static void checkWay(Node node) {
		if (node.lastNode == null) {
			visited[node.x][node.y] = 1;
			return;
		}
		visited[node.x][node.y] = 1;
		checkWay(node.lastNode);
	}

}
