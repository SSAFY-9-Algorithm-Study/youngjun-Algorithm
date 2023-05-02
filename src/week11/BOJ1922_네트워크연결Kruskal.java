package week11;

import java.io.*;
import java.util.*;;

public class BOJ1922_네트워크연결Kruskal {

	static int N;
	static int L;
	static int[] visited;
	static int visitedCnt;
	static int ans;

	static class Link implements Comparable<Link> {
		int start;
		int end;
		int len;

		public Link(int start, int end, int len) {
			super();
			this.start = start;
			this.end = end;
			this.len = len;
		}

		@Override
		public int compareTo(Link o) {
			return this.len - o.len;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		L = Integer.parseInt(br.readLine());

		// 인접리스트 생성
		List<Link> list = new ArrayList<>();
		visited = new int[N + 1];

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int com1 = Integer.parseInt(st.nextToken());
			int com2 = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());
			list.add(new Link(com1, com2, len));
		}

		Collections.sort(list);

		// 오름차순 정렬 후 첫번째 링크를 확정함
		Link frontLink = list.get(0);
		ans += frontLink.len;
		visited[frontLink.start] = 1;
		visited[frontLink.end] = 1;
		visitedCnt = 2;
		list.remove(0);

		// 모든 노드를 다 visited 할 때까지
		while (visitedCnt < N) {

			// 인접리스트를 오름차순으로 순회
			for (int i = 0; i < list.size(); i++) {

				// 현재 링크는 항상 최소,
				// 현재 링크 중 하나가 연결되어있는 경우에만 다음 링크로 확정
				Link curLink = list.get(i);
				if ((visited[curLink.start] + visited[curLink.end] == 1)) {
					visitedCnt++;
					visited[curLink.start] = 1;
					visited[curLink.end] = 1;
					ans += curLink.len;
					list.remove(i);
					break;
				}
			}

		}

		System.out.println(ans);

	}

}
