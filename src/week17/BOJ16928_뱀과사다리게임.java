package week17;

import java.io.*;
import java.util.*;

public class BOJ16928_뱀과사다리게임 {

	static int LADDER, SNAKE;

	// 주사위를 굴렸을 때 100을 넘어갈 수 있는 경우 고려
	static int[] mat = new int[106]; // 도착지점
	static int[] roll = new int[106]; // 굴린횟수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 1; i <= 100; i++) {
			mat[i] = i;
		}
		Arrays.fill(roll, Integer.MAX_VALUE);

		st = new StringTokenizer(br.readLine());
		LADDER = Integer.parseInt(st.nextToken());
		SNAKE = Integer.parseInt(st.nextToken());

		for (int i = 0; i < LADDER + SNAKE; i++) {
			st = new StringTokenizer(br.readLine());
			mat[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}

		dfs(1, 0);
		System.out.println(roll[100]);

	}

	private static void dfs(int curPos, int rollCnt) {
		if (curPos >= 100)
			return;
		for (int i = 1; i <= 6; i++) {
			int nextCnt = rollCnt + 1;
			int nextPos = curPos + i;
			if (nextCnt > roll[nextPos])
				continue;
			roll[nextPos] = nextCnt;
			dfs(mat[nextPos], nextCnt);
		}

	}

}
