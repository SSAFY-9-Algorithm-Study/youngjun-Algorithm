package week7.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

//중복순열??
public class BOJ17825_주사위윷놀이 {

	static int[] dice = new int[10];
	static int ans = 0;
	static int roads[][] = { { 0, 2, 4, 6, 8, 10 }, { 10, 13, 16, 19, 25 }, { 10, 12, 14, 16, 18, 20 },
			{ 20, 22, 24, 25 }, { 20, 22, 24, 26, 28, 30 }, { 30, 28, 27, 26, 25 }, { 30, 32, 34, 36, 38, 40 },
			{ 25, 30, 35, 40 } };

	static class Horse {
		int roadIdx;
		int curIdx;
		boolean available;

		public Horse(int roadIdx, int curIdx, boolean available) {
			super();
			this.roadIdx = roadIdx;
			this.curIdx = curIdx;
			this.available = available;
		}
	}

//	static int[][] horse = {{0,0,},{0,0},{0,0},{0,0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}

		move(0, new Horse[] { new Horse(0, 0, true), new Horse(0, 0, true), new Horse(0, 0, true),
				new Horse(0, 0, true) }, 0);
		System.out.println(ans);
	}

	private static void move(int num, Horse[] horseList, int score) {

		if (num == 10) {
			if (score > ans)
				ans = score;
			return;
		}

		else {
			for (int i = 0; i < 4; i++) {
				Horse curHorse = horseList[i];
				if (curHorse.available) {
					int roadIdx = curHorse.roadIdx;
					int curIdx = curHorse.curIdx;
					boolean available = curHorse.available;

					int tempRoadIdx = roadIdx;
					int tempCurIdx = curIdx;
					boolean tempAvailable = available;

					int curScore = 0;

					curIdx += dice[num];

					// 이미 파란길로 가고 있다면
					if (roadIdx % 2 == 1 && roadIdx <= 5) {
						if (curIdx >= roads[roadIdx].length - 1) {
							curIdx -= (roads[roadIdx].length - 1);
							roadIdx = 7;
							if (curIdx > 3)
								available = false;
							else {
								curScore = roads[roadIdx][curIdx];
							}

						}

						else {
							curScore = roads[roadIdx][curIdx];
						}
					}

					else if (roadIdx == 7 || roadIdx == 6) {
						if (curIdx > roads[roadIdx].length - 1)
							available = false;
						else {
							curScore = roads[roadIdx][curIdx];
						}
					}

					// 빨간길로 가다 파란색 원에 도착하면
					else if (curIdx == roads[roadIdx].length - 1) {
						System.out.println("blue!!");
						curIdx = 0;
						roadIdx++;
						curScore = roads[roadIdx][curIdx];
					}

					// 빨간길로 가다 파란 원을 지나치면
					else if (curIdx > roads[roadIdx].length - 1) {
						curIdx -= (roads[roadIdx].length - 1);
						roadIdx += 2;
						curScore = roads[roadIdx][curIdx];
					}

					// 빨간길로 가다가 빨간길 위에 있다면
					else {
						curScore = roads[roadIdx][curIdx];
					}

					if (canGo(curIdx, roadIdx, horseList)) {

						curHorse.roadIdx = roadIdx;
						curHorse.curIdx = curIdx;
						curHorse.available = available;

						score += curScore;

						move(num + 1, horseList, score);
						System.out.println("cur num is" + (num + 1));
						System.out.println("cur score is " + score);

						score -= curScore;

						curHorse.roadIdx = tempRoadIdx;
						curHorse.curIdx = tempCurIdx;
						curHorse.available = tempAvailable;

					}
				}
			}
		}

	}

	public static boolean canGo(int x, int y, Horse[] horseList) {
		for (int i = 0; i < horseList.length; i++) {
			if (horseList[i].available && horseList[i].curIdx == x && horseList[i].roadIdx == y)
				return false;
		}
		return true;
	}

}
