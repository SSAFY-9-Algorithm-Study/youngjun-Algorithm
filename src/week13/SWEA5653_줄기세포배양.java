package week13;

import java.io.*;
import java.util.*;

public class SWEA5653_줄기세포배양 {

	static int T, N, M, K;
	static int ans;
	static Cell[][] mat;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	// 맨 처음은 비활성화 상태이므로 k가 300이어도 최대 350임, 651일 필요 없을듯
	static int maxSize = 351;
	static int minX = maxSize, minY = maxSize, maxX = 0, maxY = 0;

	static class Cell {
		int cond; // 현재 상태 0: 죽, 1: 활성, 2:비활성
		int life; // 생명력
		int inactTime;
		int actTime;
		boolean isNew;

		public Cell(int cond, int life, int inactTime, int actTime) {
			super();
			this.cond = cond;
			this.life = life;
			this.inactTime = inactTime;
			this.actTime = actTime;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T + 1; t++) {
			ans = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			mat = new Cell[maxSize][maxSize];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int val = Integer.parseInt(st.nextToken());
					if (val > 0) {
						updateMinMax(i + 150, j + 150);
						mat[i + 150][j + 150] = new Cell(2, val, val, val);
					}
				}
			}

			for (int i = 0; i < K; i++) {

				for (int l = minX; l <= maxX; l++) {
					for (int j = minY; j <= maxY; j++) {
						if (mat[l][j] != null && mat[l][j].isNew) {
							mat[l][j].isNew = false;
						}
					}
				}
				spread();
			}

			for (int i = minX; i <= maxX; i++) {
				for (int j = minY; j <= maxY; j++) {
					if (mat[i][j] != null && mat[i][j].cond > 0) {
						ans++;
					}
				}
			}
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void spread() {
		for (int i = minX; i <= maxX; i++) {
			for (int j = minY; j <= maxY; j++) {
				Cell cell = mat[i][j];
				if (cell == null)
					continue;
				if (cell.isNew)
					continue;
				if (cell.cond == 0)
					continue;

				// 활성인 경우
				if (cell.cond == 1) {
					if (cell.actTime == cell.life)
						bfs(i, j);
					if (--cell.actTime == 0)
						cell.cond--;

				}

				// 비활성인 경우
				if (cell.cond == 2) {
					if (--cell.inactTime == 0)
						cell.cond--;
				}

			}
		}

	}

	private static void bfs(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int life = mat[x][y].life;
			int newx = x + dx[i];
			int newy = y + dy[i];
			// 처음 진입한 세포가 있는 경우에만 비교 가능
			if ((mat[newx][newy] != null && mat[newx][newy].isNew)) {
				// 비교
				if (mat[newx][newy].life < life) {
					Cell newCell = new Cell(2, life, life, life);
					newCell.isNew = true;
					mat[newx][newy] = newCell;
					updateMinMax(newx, newy);
				}
				// 빈 공간인 경우
			} else if (mat[newx][newy] == null) {
				Cell newCell = new Cell(2, life, life, life);
				newCell.isNew = true;
				mat[newx][newy] = newCell;
				updateMinMax(newx, newy);
			}
		}
	}

	private static void updateMinMax(int x, int y) {
		minX = Math.min(x, minX);
		minY = Math.min(y, minY);
		maxX = Math.max(x, maxX);
		maxY = Math.max(y, maxY);
	}

}
