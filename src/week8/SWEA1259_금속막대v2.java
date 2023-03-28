package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA1259_금속막대v2 {

	static int T;
	static int N;
	static int[] front;
	static int[] rear;
	static List<Integer> ansList;
	static List<Integer> finalAnsList;
	static int ansListSize = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T + 1; t++) {
			N = Integer.parseInt(br.readLine());
			ansListSize = 0;
			front = new int[N + 1];
			rear = new int[N + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				front[i] = Integer.parseInt(st.nextToken());
				rear[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= N; i++) {
				ansList = new ArrayList<>();
				ansList.add(front[i]);
				ansList.add(rear[i]);
				dfs(rear[i]);
				if (ansListSize < ansList.size()) {
					ansListSize = ansList.size();
					finalAnsList = ansList;
				}

			}
			System.out.print("#" + t);
			for (int i = 0; i < finalAnsList.size(); i++) {
				System.out.print(" " + finalAnsList.get(i));
			}
			System.out.println();
		}
	}

	private static void dfs(int rearItem) {
		for (int i = 0; i < front.length; i++) {
			if (front[i] == rearItem) {
				ansList.add(front[i]);
				ansList.add(rear[i]);
				dfs(rear[i]);
				break;
			}
		}
	}
}
