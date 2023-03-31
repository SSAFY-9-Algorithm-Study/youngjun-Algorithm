package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA1767_프로세서연결하기 {

	static int T;
	static int N;
	static int mat[][];
	static int min = Integer.MAX_VALUE;
	static int usedCnt = 0;

	static class Processor {

		int x;
		int y;
		int visited;

		public Processor(int x, int y, int visited) {
			super();
			this.x = x;
			this.y = y;
			this.visited = visited;
		}
	}

	static List<Processor> processorList;
	static List<Processor> processorListWithoutWall;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {

			N = Integer.parseInt(br.readLine());
			processorList = new ArrayList<>();
			processorListWithoutWall = new ArrayList<>();
			mat = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int isProcessor = Integer.parseInt(st.nextToken());
					mat[i][j] = isProcessor;
					if (isProcessor == 1) {
						processorList.add(new Processor(i, j, 0));
						if (i > 0 && j > 0 && i < N - 1 && j < N - 1)
							processorListWithoutWall.add(new Processor(i, j, 0));
					}
				}

			}
			dfs(0, 0, 0);
			System.out.println("#" + t + " " + min);
			min = Integer.MAX_VALUE;
			usedCnt = 0;

		}

	}

	static void dfs(int cnt, int level, int used) {

		if (level == processorListWithoutWall.size()) {
			if (usedCnt < used) {
				min = cnt;
				usedCnt = used;
			}

			else if (usedCnt == used) {
				min = min > cnt ? cnt : min;
			}
			return;
		}

		else {

			Processor curProcessor = processorListWithoutWall.get(level);

			int[][] temp = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int k = 0; k < N; k++) {
					temp[i][k] = mat[i][k];
				}
			}

			for (int j = 0; j < 4; j++) {
				if (check(curProcessor.x, curProcessor.y, j)) {

					int fillCnt = fill(curProcessor.x, curProcessor.y, j, 2);

					dfs(cnt + fillCnt, level + 1, used + 1);

					fill(curProcessor.x, curProcessor.y, j, 0);
				}
			}
			dfs(cnt, level + 1, used);

		}

	}

	static boolean check(int x, int y, int dir) {// dir = 0,1,2,3 = 상,우,하,좌

		int[] dx = { -1, 0, 1, 0 };
		int[] dy = { 0, 1, 0, -1 };

		while (x + dx[dir] >= 0 && x + dx[dir] <= (N - 1) && y + dy[dir] >= 0 && y + dy[dir] <= (N - 1)) {
			x = x + dx[dir];
			y = y + dy[dir];

			if (mat[x][y] != 0)
				return false;

		}
		return true;
	}

	static int fill(int x, int y, int dir, int fillValue) {
		int cnt = 0;

		int[] dx = { -1, 0, 1, 0 };
		int[] dy = { 0, 1, 0, -1 };

		while (x + dx[dir] >= 0 && x + dx[dir] <= (N - 1) && y + dy[dir] >= 0 && y + dy[dir] <= (N - 1)) {
			x = x + dx[dir];
			y = y + dy[dir];
			mat[x][y] = fillValue;
			cnt++;
		}

		return cnt;
	}

}