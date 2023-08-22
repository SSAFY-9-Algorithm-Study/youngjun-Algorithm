package week21;

import java.io.*;
import java.util.*;

public class BOJ20955_민서의응급수술 {

	static int N, M, groups, ans;
	// 부모 노드
	static int[] parent;
	// 내가 포함된 링크 수 (두 노드 중 하나에만 표시)
	static int[] linkCnt;
	// 하나의 부모에 대해 같은 부모를 가지는 노드들의 링크의 합
	static int[] nodeCnt;
	// 하나의 부모에 대해 같은 부모를 가지는 노드 수
	static int[] sameParentCnt;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		linkCnt = new int[N + 1];
		nodeCnt = new int[N + 1];
		sameParentCnt = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		// union을 하면서 연결된 노드 둘 중 하나의 linkCnt를 ++ 해준다
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			linkCnt[start]++;
			union(start, end);

		}

		for (int i = 1; i <= N; i++) {
			int parent = getParent(i);
			// 순회하다가 새로운 부모가 보이면 groups ++
			if (sameParentCnt[parent]++ == 0)
				groups++;
			// 같은 집합의 링크 수 ++
			nodeCnt[parent] += linkCnt[i];
		}

		
		for (int i = 1; i <= N; i++) {
			// 특정 그룹의 경우
			if (sameParentCnt[i] > 0)
				// 링크수 = 노드 수 - 1이어야 하므로 더 연결된 만큼을 끊어줘야 함
				ans += nodeCnt[i] - (sameParentCnt[i] - 1);
		}
		
		// 그룹의 수만큼 연결을 더 시켜야 함
		ans += (groups - 1);
		System.out.println(ans);

	}

	private static void union(int start, int end) {
		int parentStart = getParent(start);
		int parentEnd = getParent(end);

		parent[parentStart] = parent[parentEnd] = Math.min(parentStart, parentEnd);

	}

	private static int getParent(int start) {
		if (parent[start] == start)
			return start;
		return parent[start] = getParent(parent[start]);
	}

}
