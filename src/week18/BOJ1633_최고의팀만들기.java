package week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1633_최고의팀만들기 {
	static int ans;
	static class Node implements Comparable<Node> {
		@Override
		public String toString() {
			return "Node [white=" + white + ", black=" + black + "]";
		}

		int white;
		int black;

		@Override
		public int compareTo(Node o) {
			if (this.black-this.white == o.black-o.white)
				return o.white - this.white;
			return (o.black-o.white) - (this.black-this.white);
		}
	}

	static Node[] nodeArr = new Node[30];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 30; i++) {
			st = new StringTokenizer(br.readLine());
			int white = Integer.parseInt(st.nextToken());
			int black = Integer.parseInt(st.nextToken());
//			if (white < black) {
//				black -= white;
//				white = 0;
//			} else {
//				white -= black;
//				black = 0;
//			}
			Node node = new Node();
			node.white = white;
			node.black = black;
			nodeArr[i] = node;
		}
		Arrays.sort(nodeArr);
		System.out.println(Arrays.toString(nodeArr));
		for (int i = 0; i < 15; i++) {
			ans+=nodeArr[i].black;
		}
		for (int i = 15; i < 30; i++) {
			ans+=nodeArr[i].white;
		}
		System.out.println(ans);
	}

}
