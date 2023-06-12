package week14;

import java.util.*;
import java.io.*;

//1
//7 6
//4 5
//6 7
//1 2
//3 2
//1 3
//7 3

public class BOJ1707_이분그래프 {

	static int T, V, E;
	static List<Integer>[] nodeList;
	static int[] group;
	static boolean[] visited;
	static Queue<Integer> que;
	static boolean ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			nodeList = new ArrayList[20001];
			group = new int[20001];
			visited = new boolean[20001];
			que = new LinkedList<>();
			ans = true;

			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());

				if (nodeList[first] == null) {
					nodeList[first] = new ArrayList<Integer>();
				}
				nodeList[first].add(second);
				if (nodeList[second] == null) {
					nodeList[second] = new ArrayList<Integer>();
				}
				nodeList[second].add(first);
			}

			for (int i = 1; i <= V; i++) {
				if (!visited[i] && nodeList[i] != null) {
					visited[i] = true;
					if (!bfs(i)) {
						ans = false;
						break;
					}
				}
			}
			if (ans)
				System.out.println("YES");
			else
				System.out.println("NO");

		}

	}

	private static boolean bfs(int i) {
		que.clear();
		que.add(i);

		while (!que.isEmpty()) {
			int node = que.poll();
			visited[node] = true;
			for (int j = 0; j < nodeList[node].size(); j++) {
				int link = nodeList[node].get(j);
				if (visited[link] && group[link] == group[node])
					return false;
				else if (!visited[link]) {
					visited[link] = true;
					group[link] = 1 - group[node];
					que.add(link);
				}
			}
		}
		return true;
	}

}
