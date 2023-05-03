package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12896_스크루지민호 {

	// 블로깅...필요
	// 후후후
	// 트리의 개념은 무엇이고, 트리의 지름을 구하는 방법은 무엇인지 알자

	static int N;
	static List<Integer>[] nodeList;
	static int[] visited;
	static int maxDist;
	static int leaf;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		visited = new int[N + 1];
		nodeList = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			nodeList[i] = new ArrayList<>();
		}

		int start = 1;
		int end;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			nodeList[start].add(end);
			nodeList[end].add(start);
		}

		visited[start] = 1;
		dfs(start, 0);
		visited = new int[N + 1];
		visited[leaf] = 1;
		dfs(leaf, 0);
		System.out.println((maxDist + 1) / 2);

	}

	private static void dfs(int start, int dist) {
		if (maxDist <= dist) {
			maxDist = dist;
			leaf = start;
		}

		for (int i = 0; i < nodeList[start].size(); i++) {
			int link = nodeList[start].get(i);
			if (visited[link] == 0) {
				visited[link] = 1;
				dfs(link, dist + 1);
			}
		}

	}

}
