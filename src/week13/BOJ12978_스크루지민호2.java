package week13;

import java.io.*;
import java.util.*;

public class BOJ12978_스크루지민호2 {

	static int N;
	static Node[] nodeList;
	static int[] visited;
	static int maxDist;
	static int endNode;
	static int ans;
	static class Node {
		List<Integer> link = new ArrayList<>();
		boolean checked;
	}

	static int[][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		nodeList = new Node[N + 1];
		visited = new int[N + 1];
		dp = new int[N + 1][2];
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if (nodeList[start] == null) {
				Node node = new Node();
				node.link.add(end);
				nodeList[start] = node;
			} else {
				nodeList[start].link.add(end);
			}
			if (nodeList[end] == null) {
				Node node = new Node();
				node.link.add(start);
				nodeList[end] = node;
			} else {
				nodeList[end].link.add(start);
			}
		}
		visited[1] = 1;
		dfs(1, 0);
		visited = new int[N + 1];
		startDp(endNode);
		System.out.println(Math.min(dp[endNode][0], dp[endNode][1]));

	}

	private static void startDp(int node) {
		visited[node] = 1;
		dp[node][1] = 1;
		for (int i = 0; i < nodeList[node].link.size(); i++) {
			int curNode = nodeList[node].link.get(i);
			if (visited[curNode] == 1)
				continue;
			startDp(curNode);
			// 1을 여기서 더해주면 더이상 자식이 없는 노드를 체크했을 때 값이 1이 안됨
			dp[node][1] += (Math.min(dp[curNode][0], dp[curNode][1]));
			dp[node][0] += dp[curNode][1];

		}
	}

	private static void dfs(int i, int dist) {
		if (dist > maxDist) {
			maxDist = dist;
			endNode = i;
		}
		visited[i] = 1;
		if (nodeList[i] != null) {
			for (int j = 0; j < nodeList[i].link.size(); j++) {
				if (visited[nodeList[i].link.get(j)] == 0) {
					dfs(nodeList[i].link.get(j), dist + 1);
				}

			}
		}

	}

}
