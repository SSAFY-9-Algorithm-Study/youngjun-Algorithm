package week18;

import java.io.*;
import java.util.*;

public class BOJ1461_도서관 {
	static int N, M;
	static int[] arr;
	static int maxAbs;
	static int ans;
	static Queue<Integer> plusQue = new PriorityQueue<>(new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}
	});
	static Queue<Integer> minusQue = new PriorityQueue<>(new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}
	});

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int val = Integer.parseInt(st.nextToken());
			if (val < 0) {
				minusQue.add(-val);
			} else {
				plusQue.add(val);
			}
		}

		int plusMax = plusQue.peek() == null ? 0 : plusQue.peek();
		int minusMax = minusQue.peek() == null ? 0 : minusQue.peek();
		if (plusMax > minusMax) {
			ans += plusQue.poll();
			for (int i = 0; i < M-1; i++) {
				plusQue.poll();
			}
			
		} else {
			ans += minusQue.poll();
			for (int i = 0; i < M-1; i++) {
				minusQue.poll();
			}
		}
		cal(plusQue);
		cal(minusQue);
		System.out.println(ans);
			

	}

	private static void cal(Queue<Integer> que) {
		while(!que.isEmpty()) {
			ans += que.poll()*2;
			for (int i = 0; i < M-1; i++) {
				que.poll();
			}
		}
	}

}
