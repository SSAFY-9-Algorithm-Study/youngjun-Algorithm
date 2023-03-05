package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17471_게리맨더링 {

	static int N;
	static int[] population;
	static List<Integer>[] link;
	static List<Integer>[] caseLinkOne;
	static List<Integer>[] caseLinkTwo;
	static int visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		population = new int[N + 1];
		link = new ArrayList[N + 1];
		visited = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < N + 1; i++) {
			link[i] = new ArrayList<>();
		}

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			while (st.hasMoreTokens()) {
				link[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		System.out.println(Arrays.toString(link));

		for (int i = 1; i <= (N + 1) / 2; i++) {
			combination(i, 0, new int[N], 0);
		}

		System.out.println(Arrays.toString(visited));

	}

	public static void combination(int L, int level, int res[], int begin) {
		if (level == L) {
			System.out.println(Arrays.toString(res));
			caseLinkOne = new ArrayList[N + 1];
			caseLinkTwo = new ArrayList[N + 1];

			for (int i = 0; i < res.length; i++) {
				if (res[i] == 1) {
					caseLinkOne[i + 1] = link[i + 1];
					caseLinkTwo[i + 1] = new ArrayList<>();

				} else {
					caseLinkTwo[i + 1] = link[i + 1];
					caseLinkOne[i + 1] = new ArrayList<>();
				}
			}

			System.out.println(Arrays.toString(caseLinkOne));

//			connectionList(1);
			System.out.println("visited is ");
			System.out.println(Arrays.toString(visited));
			visited = new int[N + 1];
		} else {
			for (int i = begin; i < res.length; i++) {
				res[i] = 1;
				combination(L, level + 1, res, i + 1);
				res[i] = 0;

			}
		}
	}

	public static boolean isConnected(int[] checkArr) {// {1,3,4}와 같이 묶여있는 array
		int visited[] = new int[N + 1];

		for (int i = 0; i < checkArr.length; i++) {
			if (visited[checkArr[i]] == 0)
				return false;
		}
		return true;

	}

	public static void dfs(int n, int[] visited) {

		if (visited[n] == 0) {

			for (int i = 0; i < caseLinkOne[n].size(); i++) {

			}
		}
	}

	public static void isConnected(int[] checkArr1, int[] checkArr2) {

	}

}
