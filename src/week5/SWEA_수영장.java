package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_수영장 {

	static int T;
	static int cost[] = new int[4];
	static int month[] = new int[13];
	static int min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T + 1; t++) {

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				month[i + 1] = Integer.parseInt(st.nextToken());
			}
			min = cost[3];

			iterate(1, 0);
			System.out.println("#" + t + " " + min);

		}

	}

	public static void iterate(int cur, int curCost) {
		if (curCost >= cost[3] || cur > 12) {
			if (min > curCost)
				min = curCost;
			return;
		}

		else {
			if (month[cur] == 0) {
				iterate(cur + 1, curCost);
			} else {
				iterate(cur + 1, curCost + month[cur] * cost[0]);
				iterate(cur + 1, curCost + cost[1]);
				iterate(cur + 3, curCost + cost[2]);
			}
		}
	}

}
