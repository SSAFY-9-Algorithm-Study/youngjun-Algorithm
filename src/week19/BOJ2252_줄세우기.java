package week19;

import java.util.*;
import java.io.*;

public class BOJ2252_줄세우기 {
	static int N, M;
	static Node[] nodeList;
	static boolean[] visited;
	static Queue<Node> que = new LinkedList<>();

	static class Node {
		int val;
		List<Integer> frontNode = new ArrayList<>();
		List<Integer> rearNode = new ArrayList<>();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nodeList = new Node[N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			nodeList[i] = new Node();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int front = Integer.parseInt(st.nextToken());
			int rear = Integer.parseInt(st.nextToken());
			nodeList[front].rearNode.add(rear);
			nodeList[rear].frontNode.add(front);
		}

		for (int i = 1; i <= N; i++) {
			nodeList[i].val = i;
			if (nodeList[i].frontNode.size() == 0) {
				que.add(nodeList[i]);
				visited[i] = true;
			}

		}

		while (!que.isEmpty()) {
			Node curNode = que.poll();
			sb.append(curNode.val);
			sb.append(" ");

			for (int i = 0; i < curNode.rearNode.size(); i++) {
				int nodeIdx = curNode.rearNode.get(i);
				Node rearNode = nodeList[nodeIdx];
				addFrontNodes(rearNode);
			}
		}

		System.out.println(sb.toString());

	}

	private static void addFrontNodes(Node node) {
		for (int i = 0; i < node.frontNode.size(); i++) {
			if (!visited[node.frontNode.get(i)]) {
				addFrontNodes(nodeList[node.frontNode.get(i)]);
			}
		}
		if (!visited[node.val]) {
			que.add(node);
			visited[node.val] = true;
		}

	}

}
