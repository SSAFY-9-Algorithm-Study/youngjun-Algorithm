package week4.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class Back_9663_NQueen {

	static int n;
	static int[][] arr;
	static int[][] visited;
	static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
//		for (int i = 0; i < n; i++) {
//			visited = new int[n][n];
//			System.out.println("dfs with " + i);
//			dfs(0, i, 1, new int[n][n]);
//		}
		
		dfs(0,0,0,new int[n][n]);

		System.out.println(cnt);

	}

	public static void dfs(int x, int y, int depth, int possible[][]) {

		visited[x][y] = 1;

		if (depth == n) {
			cnt++;
			return;
		}

		else {
			int[][] newPossible = new int[n][n];
			for (int i = 0; i < newPossible.length; i++) {
				for (int j = 0; j < newPossible.length; j++) {
					newPossible[i][j] = possible[i][j];
				}
			}
			
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i == x || j == y || (i - j) == (x - y) || (i+j) == (x+y))
						newPossible[i][j] = 1;// 불가능을 1로 표시
				}
			}

			for (int i = 0; i < newPossible.length; i++) {
				System.out.println();
				for (int j = 0; j < newPossible.length; j++) {
					System.out.print(newPossible[i][j]);
				}
			}
			
			System.out.println();
			
			for (int i = 0; i < n; i++) {
				if (visited[depth][i] == 0 && newPossible[depth][i] == 0) {
					System.out.println("depth is " + depth);
					dfs(depth, i, depth + 1, newPossible);
				}
			}
		}
	}

}
