package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1949_등산로조성 {

	static int T;
	static int N;
	static int K;
	static int mat[][];
	static int temp[][];
	static int visited[][];
	static int top = Integer.MIN_VALUE;
	static int maxLen = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int i = 1; i < T + 1; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			mat = new int[N][N];
			visited = new int[N][N];
			temp = new int[N][N];

			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int j2 = 0; j2 < N; j2++) {
					int val = Integer.parseInt(st.nextToken());
					mat[j][j2] = val;
					top = top > val ? top : val;
				}
			}

			for (int j = 0; j < N; j++) {
				for (int j2 = 0; j2 < N; j2++) {

					if (mat[j][j2] == top) {

						for (int k = 0; k < N; k++) {
							for (int k2 = 0; k2 < N; k2++) {
								temp[k][k2] = mat[k][k2];
							}
						}

						dfs(j, j2, 1, K, false);
						visited = new int[N][N];
					}

				}
			}
			System.out.println("#" + i + " " + maxLen);
			maxLen = 0;
			top = Integer.MIN_VALUE;
		}
	}

	public static void dfs(int x, int y, int level, int k, boolean isCut) {
		visited[x][y] = 1;
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		if (k < 0 || temp[x][y] < 0) {
			return;
		}

		else {
			for (int i = 0; i < 4; i++) {
				if (x + dx[i] < 0 || x + dx[i] > (N - 1) || y + dy[i] < 0 || y + dy[i] > (N - 1))
					continue;
				
				
				if (temp[x + dx[i]][y + dy[i]] < temp[x][y] && visited[x + dx[i]][y + dy[i]] == 0) {
					visited[x + dx[i]][y + dy[i]]=1;
					dfs(x + dx[i], y + dy[i], level + 1, k, isCut);
					visited[x + dx[i]][y + dy[i]]=0;
					
				} 
				
				else if (visited[x + dx[i]][y + dy[i]] == 0 && isCut == false) {
					

					int sub = temp[x + dx[i]][y + dy[i]] - temp[x][y];
					k-=(sub+1);
					temp[x + dx[i]][y + dy[i]]-=(sub+1);
					visited[x + dx[i]][y + dy[i]]=1;
					isCut = true;
					
					dfs(x + dx[i], y + dy[i], level + 1, k, isCut);
					
					isCut = false;
					visited[x + dx[i]][y + dy[i]]=0;
					temp[x + dx[i]][y + dy[i]]+=(sub+1);
					k+=(sub+1);
					

				}
				
				if(maxLen < (level))
					maxLen = level;


			}
		}
	}
}
