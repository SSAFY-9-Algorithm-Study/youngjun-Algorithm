package week18;

import java.io.*;
import java.util.*;

public class BOJ1633_최고의팀만들기 {

	static int N;
	static boolean isEnd = true;
	static List<Integer> whiteList = new ArrayList<>();
	static List<Integer> blackList = new ArrayList<>();
	static int[][][] dp;
	static boolean[][][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String input = "";

		while (isEnd) {
			try {
				st = new StringTokenizer(br.readLine());
				whiteList.add(Integer.parseInt(st.nextToken()));
				blackList.add(Integer.parseInt(st.nextToken()));
				N++;
			} catch (Exception e) {
				isEnd = false;
			}
		}

		dp = new int[N][16][16];
		visited = new boolean[N][16][16];
		// 0번째 인덱스부터 마지막까지, 백 15개, 흑 15개를 고르는 dp함수
		System.out.println(getDp(0, 15, 15));
	}

	private static int getDp(int startIdx, int whiteCnt, int blackCnt) {
		if (whiteCnt == 0 && blackCnt == 0 || (startIdx == N))
			return 0;
		if (visited[startIdx][whiteCnt][blackCnt])
			return dp[startIdx][whiteCnt][blackCnt];
		
		//현재 인덱스의 값을 흰으로 함
		int getWhite = whiteCnt == 0 ? 0 : getDp(startIdx + 1, whiteCnt - 1, blackCnt)+whiteList.get(startIdx);
		//현재 인덱스의 값을 흑으로 함
		int getBlack = blackCnt == 0 ? 0 : getDp(startIdx + 1, whiteCnt, blackCnt - 1)+blackList.get(startIdx);

		visited[startIdx][whiteCnt][blackCnt] = true;
		//현재 인덱스를 사용안하는 때까지 해서 3가지 경우의 max 값 비교
		return dp[startIdx][whiteCnt][blackCnt] = Math.max(getDp(startIdx + 1, whiteCnt, blackCnt),
				Math.max(getWhite, getBlack));
	}

}
