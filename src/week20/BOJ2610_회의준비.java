package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


public class BOJ2610_회의준비 {
	static int N, M;
	static int[] parent;
	static boolean[] visited;
	static int[][] minDist;
	static List<Integer> ans = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		parent = new int[N + 1];
		visited = new boolean[N + 1];
		minDist = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			parent[i] = i;
			for (int j = 1; j <= N; j++) {
				if (i != j)
					minDist[i][j] = 10000;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			minDist[start][end] = minDist[end][start] = 1;
			union(start, end);

		}

		for (int i = 1; i <= N; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			int curParent = getParent(i);
			List<Integer> links = new ArrayList<>();
			for (int j = 1; j <= N; j++) {
				if (curParent == getParent(j)) {
					links.add(j);
					visited[j] = true;
				}

			}
			findRep(links);
			findAns(links);
		}

		Collections.sort(ans);
		System.out.println(ans.size());
		for (int i = 0; i < ans.size(); i++) {
			System.out.println(ans.get(i));
		}

	}

	private static void findAns(List<Integer> links) {
		int max = 0;
		int minOfMax = 10000;
		int rep = 0;
		for (int i = 0; i < links.size(); i++) {
			int start = links.get(i);
			max = 0;
			for (int j = 0; j < links.size(); j++) {
				int end = links.get(j);
				max = Math.max(max, minDist[start][end]);
			}
			if (minOfMax > max) {
				minOfMax = max;
				rep = links.get(i);
			}
		}
		ans.add(rep);
	}

	// 플로이드 워셜
	// 같은 집단에 속한 노드끼리 모든 경로의 모든 최단거리를 구한다 n^3
	private static void findRep(List<Integer> links) {
		int size = links.size();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int j2 = 0; j2 < size; j2++) {
					int mid = links.get(i);
					int start = links.get(j);
					int end = links.get(j2);

					minDist[start][end] = Math.min(minDist[start][mid] + minDist[mid][end], minDist[start][end]);
					minDist[end][start] = Math.min(minDist[end][mid] + minDist[mid][start], minDist[end][start]);
				}
			}
		}
	}

	// union 
	private static void union(int start, int end) {
		int parentStart = getParent(start);
		int parentEnd = getParent(end);
		int minParent = Math.min(parentStart, parentEnd);
		parent[start] = parent[end] = parent[parentStart] = parent[parentEnd] = minParent;
	}

	// union
	private static int getParent(int i) {
		if (parent[i] == i)
			return i;
		return getParent(parent[i]);
	}

}
