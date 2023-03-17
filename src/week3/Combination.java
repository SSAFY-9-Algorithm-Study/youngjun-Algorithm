package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Combination {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] list = new int[n];
		for (int i = 1; i < n + 1; i++) {
			list[i - 1] = i;
		}
		int r = Integer.parseInt(br.readLine());
		int[] res = new int[r];
		DFS(0, 0, n, r, list, res);

	}

	public static void DFS(int L, int Begin, int n, int r, int[] list, int[] res) {
		if (L == r) { // 깊이가 r값과 같아지면 종료
			System.out.println(Arrays.toString(res));
		} else {
			for (int i = Begin; i < n; i++) {
				res[L] = list[i];
				DFS(L + 1, i + 1, n, r, list, res);
			}

		}
	}

}
