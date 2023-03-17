package week4.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class DFSBFS_17471 {

	static int n;
	static List<List<Integer>> arr;
	static int[] population;
	static int[] visited;
	static List<int[]> resList;
	static int[] res;
	static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		arr = new ArrayList<>();

		n = Integer.parseInt(br.readLine());
		population = new int[n + 1];
		visited = new int[n];
		resList = new ArrayList<>();
		res = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < n + 1; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}

		arr.add(null);
		for (int i = 1; i < n + 1; i++) {
			st = new StringTokenizer(br.readLine());
			List<Integer> list = new ArrayList<>();
			int len = Integer.parseInt(st.nextToken());
			for (int j = 0; j < len; j++) {
				while (st.hasMoreTokens()) {
					list.add(Integer.parseInt(st.nextToken()));
				}
			}
			arr.add(list);
		}

		System.out.println(Arrays.toString(population));
		System.out.println(arr);

		for (int i = 0; i <= (n + 1) / 2; i++) {
			dfs(i, 0, 0);
			res = new int[n];
		}

		for (int i = 0; i < resList.size(); i++) {
			int res[] = resList.get(i);
			List<Integer> listZero = new ArrayList<>();
			List<Integer> listOne = new ArrayList<>();
			for (int j = 0; j < res.length; j++) {
				if (res[j] == 0)
					listZero.add(j + 1);
				else
					listOne.add(j + 1);
			}

		}

	}

	public static void dfs(int L, int level, int start) {
		if (level == L) {
			resList.add(res);
			cnt++;
			return;
		} else {
			for (int i = start; i < res.length; i++) {
				res[i] = 1;
				dfs(L, level + 1, i + 1);
				res[i] = 0;
			}
		}
	}

	public static void bfs(int x) {

	}

}
