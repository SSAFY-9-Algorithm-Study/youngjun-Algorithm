package week19;

import java.util.*;
import java.io.*;

// 점을 넣은 함수의 y절편이 같다면 같은 선 위에 있음
public class BOJ28137_뭐라고안들려 {
	static int N;
	static long INCL;
	static long cnt;
	static Map<Long, Long> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		INCL = Long.parseLong(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());

			addToMap((-x) * INCL + y);
		}

		for (long key : map.keySet()) {
			perm(map.get(key));
		}

		System.out.println(cnt);

	}

	private static void perm(long val) {
		cnt += (val) * (val - 1);
	}

	private static void addToMap(long yIntercept) {
		Long getCnt = map.getOrDefault(yIntercept, (long) 0);
		map.put(yIntercept, getCnt + 1);
	}

}
