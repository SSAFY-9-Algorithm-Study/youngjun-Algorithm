package week12;

import java.io.*;
import java.util.*;

public class BOJ17352_여러분의다리가되어드리겠습니다 {

	static int N;
	static List<Integer>[] list;
	static int[] visited;
	static int ansEnd;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N + 1];
		visited = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < N - 2; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list[start].add(end);
			list[end].add(start);
		}

		visited[1] = 1;
		dfs(1);
		for (int i = 2; i <= N; i++) {
			if (visited[i] == 0) {
				ansEnd = i;
				break;
			}
		}
		System.out.println("1 " + ansEnd);

	}

	private static void dfs(int i) {
		for (int j = 0; j < list[i].size(); j++) {
			int endPoint = list[i].get(j);
			if (visited[endPoint] == 0) {
				visited[endPoint] = 1;
				dfs(endPoint);
			}
		}
	}

}
