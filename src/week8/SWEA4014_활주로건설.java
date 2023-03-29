package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA4014_활주로건설 {

	static int T;
	static int N;
	static int X;
	static int[][] mat;
	static int[] checkArr;
	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			mat = new int[N][N];
			checkArr = new int[N];
			ans = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}

			}
			// 가로 방향 체크
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					checkArr[j] = mat[i][j];
				}
				if(check(checkArr)) ans++;
			}
			// 세로 방향 체크
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					checkArr[j] = mat[j][i];
				}
				if(check(checkArr)) ans++;
			}
			System.out.println(ans);
		}
	}

	private static boolean check(int[] arr) {
		return false;
		
		
	}

}
