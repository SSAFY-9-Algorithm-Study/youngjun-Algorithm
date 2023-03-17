package week6.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BOJ17779_게리맨더링2 {

	static int N;
	static int mat[][];
	static int visited[][];
	static int population[];
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		mat = new int[N + 1][N + 1];
		visited = new int[N + 1][N + 1];
		population = new int[5];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// N에 대한 모든 x,y,d1,d2의 경우의 수를 구하자

		for (int x = 1; x <= N - 2; x++) {
			for (int d1 = 1; d1 <= N - x - 1; d1++) {
				for (int d2 = 1; d2 <= N - x - d1; d2++) {
					for (int y = 1 + d1; y <= N - d2; y++) {

						for (int row = x; row <= x + d1; row++) {
							visited[row][x + y - row] = 5;
							for (int i = 1; i < x + y - row; i++) {
								visited[row][i] = 1;
							}
						}

						for (int row = x + d1; row <= x + d1 + d2; row++) {
							visited[row][row - x + y - 2 * d1] = 5;
							for (int i = 1; i < row - x + y - 2 * d1; i++) {
								visited[row][i] = 3;
							}
						}

						for (int row = x + d2; row <= x + d2 + d1; row++) {
							visited[row][2 * d2 + x + y - row] = 5;
							for (int i = N; i > 2 * d2 + x + y - row; i--) {
								visited[row][i] = 4;
							}
						}

						for (int row = x; row <= x + d2; row++) {
							visited[row][row + y - x] = 5;
							for (int i = N; i > row + y - x; i--) {
								visited[row][i] = 2;
							}
						}

						for (int i = 1; i <= x - 1; i++) {
							for (int j = 1; j <= y; j++) {
								visited[i][j] = 1;
							}
						}

						for (int i = 1; i <= x - 1; i++) {
							for (int j = N; j > y; j--) {
								visited[i][j] = 2;
							}
						}

						for (int i = x + d1 + d2 + 1; i <= N; i++) {
							for (int j = 1; j < y + d2 - d1; j++) {
								visited[i][j] = 3;
							}
						}

						for (int i = x + d1 + d2 + 1; i <= N; i++) {
							for (int j = N; j >= y + d2 - d1; j--) {
								visited[i][j] = 4;
							}
						}

						for (int i = 1; i <= N; i++) {
							for (int j = 1; j <= N; j++) {
								int val = visited[i][j] % 5;
								population[val] += mat[i][j];

							}
						}

						Arrays.sort(population);
						min = Math.min(min, population[4] - population[0]);

						visited = new int[N + 1][N + 1];
						population = new int[5];
					}
				}
			}
		}
		System.out.println(min);
	}

}
