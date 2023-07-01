package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9205_맥주마시면서걸어가기 {

	static int T, N;
	static int[] parent;
	static Node[] nodeArr;

	static class Node {
		int x;
		int y;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T + 1; t++) {
			N = Integer.parseInt(br.readLine());
			nodeArr = new Node[N + 2];
			parent = new int[N + 2];
			for (int i = 0; i < N + 2; i++) {
				parent[i] = i;
			}
			for (int i = 0; i < N + 2; i++) {
				st = new StringTokenizer(br.readLine());
				Node node = new Node();
				node.x = Integer.parseInt(st.nextToken());
				node.y = Integer.parseInt(st.nextToken());
				nodeArr[i] = node;
				for (int j = 0; j < i; j++) {
					int dist = Math.abs(nodeArr[j].x - node.x) + Math.abs(nodeArr[j].y - node.y);
					if (dist <= 1000) {
						union(i, j);
					}
				}
			}

			if (find(0) != find(N + 1)) {
				System.out.println("sad");
			} else
				System.out.println("happy");

		}

	}

	private static void union(int i, int j) {
		int iParent = find(i);
		int jParent = find(j);
		if(iParent < jParent) {
			parent[iParent] = jParent;
		}
		else
			parent[jParent] = iParent;

	}

	private static int find(int i) {
		if (parent[i] == i)
			return i;
		return parent[i] = find(parent[i]);
	}
}
