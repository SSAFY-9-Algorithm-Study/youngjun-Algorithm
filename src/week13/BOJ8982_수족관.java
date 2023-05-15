package week13;

import java.io.*;
import java.util.*;

public class BOJ8982_수족관 {

	static int[] waterLev = new int[40_001];
	static int[] visited;
	static List<Hole> holeList;
	static int points;
	static int holes;
	static int width;
	static int ans;

	static class Hole {
		int h;
		int startW;
		int endW;

		@Override
		public String toString() {
			return "Hole [h=" + h + ", startW=" + startW + ", endW=" + endW + "]";
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		points = Integer.parseInt(br.readLine());
		for (int i = 0; i < points - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			waterLev[w] = h;
		}
		st = new StringTokenizer(br.readLine());
		width = Integer.parseInt(st.nextToken());
		st.nextToken();

		holes = Integer.parseInt(br.readLine());

		holeList = new ArrayList<>();
		for (int i = 0; i < holes; i++) {
			st = new StringTokenizer(br.readLine());
			Hole hole = new Hole();
			hole.startW = Integer.parseInt(st.nextToken());
			hole.h = Integer.parseInt(st.nextToken());
			hole.endW = Integer.parseInt(st.nextToken());
			st.nextToken();
			holeList.add(hole);

		}

		// 높이 정의 안된 w 좌표들의 높이를 바로 앞 값에 맞춰 지정
		for (int i = 0; i < width; i++) {
			if (waterLev[i] == 0)
				waterLev[i] = waterLev[i - 1];
		}
		waterLev[width] = 0;
		visited = new int[width];

//		System.out.println(Arrays.toString(waterLev));

		// hole들을 높이 깊은 순으로 정렬
		holeList.sort(new Comparator<Hole>() {

			@Override
			public int compare(Hole o1, Hole o2) {
				return o2.h - o1.h;
			}
		});

		for (Hole hole : holeList) {
//			System.out.println(hole);
			int startIdx = hole.startW - 1;
			visited[hole.startW] = 1;
			int endIdx = hole.endW;

			for (int i = hole.startW; i < endIdx; i++) {
				waterLev[i] -= hole.h;
			}

			while (startIdx >= 0 && waterLev[startIdx] > hole.h && visited[startIdx] == 0) {
				waterLev[startIdx] -= hole.h;
				visited[startIdx] = 1;
//				System.out.println("erased " + startIdx + " left is " + waterLev[startIdx]);
				startIdx--;
			}
			while (endIdx < width && waterLev[endIdx] > hole.h && visited[endIdx] == 0) {
				waterLev[endIdx] -= hole.h;
				visited[endIdx] = 1;
//				System.out.println("erased " + endIdx + " left is " + waterLev[endIdx]);
				endIdx++;
			}
		}

//		System.out.println(Arrays.toString(waterLev));

		// 남은 높이 합계 계산
		for (int i = 0; i < width; i++) {
			ans += waterLev[i];
		}
		System.out.println(ans);
	}

}
