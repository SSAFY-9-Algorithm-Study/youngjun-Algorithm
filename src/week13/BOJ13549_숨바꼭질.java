package week13;

import java.io.*;
import java.util.*;

public class BOJ13549_숨바꼭질 {

	static int ans;
	static int[] visited;
	static int N;
	static int K;
	static int maxLen = 100_001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new int[maxLen];
		bfs();
		System.out.println(ans);

	}

	private static void bfs() {
		Deque<int[]> deq = new LinkedList<>();
		deq.offer(new int[] { N, 0 });

		while (!deq.isEmpty()) {
			int[] arr = deq.pollFirst();
			int node = arr[0];
			int dist = arr[1];

			if (node == K) {
				ans = dist;
				return;
			}

			if (node * 2 < maxLen && visited[node * 2] == 0) {
				deq.offerFirst(new int[] { node * 2, dist });
				visited[node * 2] = 1;
			}

			if (node + 1 < maxLen && visited[node + 1] == 0) {
				deq.offer(new int[] { node + 1, dist + 1 });
				visited[node + 1] = 1;
			}

			if (node - 1 >= 0 && visited[node - 1] == 0) {
				deq.offer(new int[] { node - 1, dist + 1 });
				visited[node - 1] = 1;
			}

		}
	}

}
