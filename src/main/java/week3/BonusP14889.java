package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BonusP14889 {
	static int n;
	static int nums[];
	static boolean[] visit;
	static int mat[][];
	static int min = Integer.MAX_VALUE;;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		nums = new int[n];
		visit = new boolean[n];
		mat = new int[n][n];
		for (int i = 0; i < n; i++) {
			nums[i] = i;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		DFS(0, 0);

		System.out.println(min);

	}

	public static void DFS(int L, int begin) {
		if (L == n / 2) {
			diff();
			return;
		}
		for (int i = begin; i < n; i++) {
			visit[i] = true;
			DFS(L + 1, i + 1);
			visit[i] = false;
		}
	}

	public static void diff() {
		int start = 0;
		int link = 0;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (visit[i] == true && visit[j] == true) {
					start += mat[i][j];
					start += mat[j][i];
				} else if (visit[i] == false && visit[j] == false) {
					link += mat[i][j];
					link += mat[j][i];
				}

			}
		}

		int val = Math.abs(start - link);

		if (val == 0) {
			System.out.println(val);
			System.exit(0);
		}

		min = Math.min(min, val);

	}

}
