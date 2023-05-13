package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1595_북쪽나라의도로 {
	static int[] visited = new int[10_001];
	static int maxLen;
	static int endNode;
	static List<Node>[] arr = new ArrayList[10_001];

	static class Node {
		int end;
		int len;

		public Node(int end, int len) {
			super();
			this.end = end;
			this.len = len;
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < arr.length; i++) {
			arr[i] = new ArrayList<Node>();
		}

		String input = "";
		try {
			while ((input = br.readLine()).length() > 0) {
				st = new StringTokenizer(input);
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int len = Integer.parseInt(st.nextToken());
				arr[start].add(new Node(end, len));
				arr[end].add(new Node(start, len));
			}
		} catch (Exception e) {
		}

		visited[1] = 1;
		dfs(1, 0);
		visited = new int[10_001];
		visited[endNode] = 1;
		dfs(endNode, 0);
		System.out.println(maxLen);

	}

	private static void dfs(int i, int dist) {
		if (maxLen <= dist) {
			endNode = i;
			maxLen = dist;
		}
		for (int j = 0; j < arr[i].size(); j++) {
			int end = arr[i].get(j).end;
			if (visited[end] == 0) {
				visited[end] = 1;
				dfs(end, dist + arr[i].get(j).len);
			}
		}

	}

}