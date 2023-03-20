package week6.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ21610_마법사상어와비바라기 {

	static int[] dx = { 9, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { 9, -1, -1, 0, 1, 1, 1, 0, -1 };

	static int[] crossdx = { -1, -1, 1, 1 };
	static int[] crossdy = { -1, 1, 1, -1 };

	static int mat[][];
	static int curCloud[][]; // 이동 후의 구름이 있는 좌표를 체크하기 위한 배열
	static int N;
	static int M;
	static int sum;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mat = new int[N + 1][N + 1];
		curCloud = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}

		}
		List<int[]> cloudList = new ArrayList<>(); //현재의 구름 좌표를 저장하는 리스트
		cloudList.add(new int[] { N, 1 });
		cloudList.add(new int[] { N, 2 });
		cloudList.add(new int[] { N - 1, 1 });
		cloudList.add(new int[] { N - 1, 2 });

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int di = Integer.parseInt(st.nextToken());
			int si = Integer.parseInt(st.nextToken());

			// 구름 이동
			for (int j = 0; j < cloudList.size(); j++) {
				int x = cloudList.get(j)[0];
				int y = cloudList.get(j)[1];
				for (int j2 = 1; j2 <= si; j2++) {
					x += dx[di];
					if (x == N + 1)
						x = 1;
					if (x == 0)
						x = N;
					y += dy[di];
					if (y == N + 1)
						y = 1;
					if (y == 0)
						y = N;
				}
				cloudList.set(j, new int[] { x, y });
				curCloud[x][y] = 1;

			}

			for (int j = 0; j < cloudList.size(); j++) {
				mat[cloudList.get(j)[0]][cloudList.get(j)[1]]++;
			}



			for (int j = 0; j < cloudList.size(); j++) {
				for (int j2 = 0; j2 < 4; j2++) {
					int x = cloudList.get(j)[0] + crossdx[j2];
					int y = cloudList.get(j)[1] + crossdy[j2];

					if (x < 1 || x > N || y < 1 || y > N)
						continue;
					if (mat[x][y] > 0)
						mat[cloudList.get(j)[0]][cloudList.get(j)[1]]++;
				}
			}

			cloudList.clear();


			for (int j = 1; j <= N; j++) {
				for (int j2 = 1; j2 <= N; j2++) {
					if (curCloud[j][j2] != 1 && mat[j][j2] >= 2) {
						mat[j][j2] -= 2;
						curCloud[j][j2] = 1;
						cloudList.add(new int[] { j, j2 });
					}
					if (curCloud[j][j2] == 1)
						curCloud[j][j2] = 0;
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sum += mat[i][j];
			}
		}
		
		
		System.out.println(sum);
	}

}
