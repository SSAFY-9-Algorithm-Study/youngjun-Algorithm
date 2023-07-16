package week19;

import java.util.*;
import java.io.*;

// 점을 넣은 함수의 y절편이 같다면 같은 선 위에 있음
public class BOJ28137_뭐라고안들려 {
	static int N;
	static double INCL;
	static long cnt;
	static Map<Double, Double> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		INCL = Double.parseDouble(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());

			addToMap((-x) * INCL + y);
		}

		for (double key : map.keySet()) {
			perm(map.get(key));
		}

		System.out.println(cnt);

	}

	private static void perm(double val) {
		cnt += (val) * (val - 1);
	}

	private static void addToMap(double yIntercept) {
		Double getCnt = map.getOrDefault(yIntercept, (double) 0);
		map.put(yIntercept, getCnt + 1);
	}

}
