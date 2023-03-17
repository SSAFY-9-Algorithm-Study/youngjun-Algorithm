package week6.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ7569_토마토 {

	static int H;
	static int W;
	static int Depth;
	static int[][][] mat;
	static int[][][] temp;
	static int[][][] visited;
	static int unripeTomatoes;
	static int days;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		Depth = Integer.parseInt(st.nextToken());

		mat = new int[Depth][H][W];
		temp = new int[Depth][H][W];
		visited = new int[Depth][H][W]; // 초기화 해줘야됨!!

		for (int i = 0; i < Depth; i++) {
			for (int j = 0; j < H; j++) {
				st = new StringTokenizer(br.readLine());
				for (int j2 = 0; j2 < W; j2++) {
					int val = Integer.parseInt(st.nextToken());
					mat[i][j][j2] = val;
					if (val == 0)
						unripeTomatoes++;
				}

			}
		}

//		System.out.println(mat[1][1][2]);

		if (unripeTomatoes == 0)
			System.out.println(0);
		else {

			for (int k = 0; k < Depth; k++) {
				for (int k2 = 0; k2 < H; k2++) {
					for (int l = 0; l < W; l++) {
						temp[k][k2][l] = mat[k][k2][l];
					}
				}
			}
			
			
			for (int i = 0; i < Depth; i++) {
				for (int j = 0; j < H; j++) {
					for (int j2 = 0; j2 < W; j2++) {
						if (mat[i][j][j2] == 1) {
							bfs(i, j, j2, 0);
						}
					}
				}
			}
			

			for (int k = 0; k < Depth; k++) {
				for (int k2 = 0; k2 < H; k2++) {
					for (int l = 0; l < W; l++) {
						mat[k][k2][l] = temp[k][k2][l];
					}
				}
			}

			if (unripeTomatoes!=0) {
				days = -1;
			}
			System.out.println(days);
		}

		

	}

	public static void bfs(int z, int x, int y, int days1) {
		Deque<int[]> deq = new ArrayDeque<int[]>();
		deq.offer(new int[] { z, x, y, days1 });
		int[] dx = { 0, 0, -1, 1, 0, 0 };
		int[] dy = { -1, 1, 0, 0, 0, 0 };
		int[] dz = { 0, 0, 0, 0, -1, 1 };

		while (!deq.isEmpty()) {
			
			for (int k = 0; k < Depth; k++) {
				System.out.println();
				for (int k2 = 0; k2 < H; k2++) {
					System.out.println();
					for (int l = 0; l < W; l++) {
						System.out.print(temp[k][k2][l]);
					}
				}
			}

			int[] arr = deq.pollFirst();
			z = arr[0];
			x = arr[1];
			y = arr[2];
			days1 = arr[3];
			if(days1> days)
				days = days1;
//			System.out.println("days is " + days);
			

			for (int i = 0; i < 6; i++) {
				int newz = z + dz[i];
				int newx = x + dx[i];
				int newy = y + dy[i];

				if (newz < 0 || newz > (Depth - 1) || newx < 0 || newx > (H - 1) || newy < 0 || newy > (W - 1))
					continue;

				if (visited[newz][newx][newy] == 0 && mat[newz][newx][newy] == 0) {
					visited[newz][newx][newy] = 1;
					temp[newz][newx][newy] = 1;
					unripeTomatoes--;
					deq.offer(new int[] { newz, newx, newy, days1 + 1 });
					
				}
			}

		}

	}

}
