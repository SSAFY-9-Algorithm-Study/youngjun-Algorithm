package week12;

import java.io.*;
import java.util.*;

public class BOJ10800_컬러볼 {

	static int N;
	static int[] col;
	static int[] sizes;
	static int curSum;
	static List<Ball> ballList = new ArrayList<>();
	static class Ball {
		int col;
		int size;
		int num;
		int ans;

		public Ball(int col, int size, int num) {
			super();
			this.size = size;
			this.num = num;
			this.col = col;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		col = new int[N + 1];
		sizes = new int[2001];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			ballList.add(new Ball(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i));
		}

		Collections.sort(ballList, new Comparator<Ball>() {

			@Override
			public int compare(Ball o1, Ball o2) {
				if (o1.size == o2.size)
					return o1.col - o2.col;
				return o1.size - o2.size;
			}
		});

		for (int i = 0; i < ballList.size(); i++) {

			Ball curBall = ballList.get(i);
			curBall.ans = curSum - col[curBall.col];

			col[curBall.col] += curBall.size;

			curBall.ans -= sizes[curBall.size];
			sizes[curBall.size] += curBall.size;
			
			curSum += curBall.size;

			int idx = i - 1;
			while (idx >= 0 && ballList.get(idx).size == curBall.size && ballList.get(idx).col == curBall.col) {
				curBall.ans += curBall.size;
				idx--;
			}
		}

		Collections.sort(ballList, new Comparator<Ball>() {

			@Override
			public int compare(Ball o1, Ball o2) {
				return o1.num - o2.num;
			}
		});

		for (int i = 0; i < ballList.size(); i++) {
			sb.append(ballList.get(i).ans);
			sb.append("\n");
		}

		System.out.println(sb);

	}

}
