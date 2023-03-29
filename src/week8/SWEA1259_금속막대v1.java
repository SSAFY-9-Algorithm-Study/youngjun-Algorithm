package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA1259_금속막대v1 {

	static int T;
	static int N;
	static List<Integer>[][] DP;
	static int[] front;
	static int[] rear;
	static int maxRear;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T + 1; t++) {
			N = Integer.parseInt(br.readLine());
			front = new int[N + 1];
			rear = new int[N + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				front[i] = Integer.parseInt(st.nextToken());
				rear[i] = Integer.parseInt(st.nextToken());
				maxRear = Math.max(front[i], Math.max(rear[i], maxRear));
			}
			DP = new ArrayList[N + 1][maxRear + 1];

			for (int i = 0; i <= N; i++) {
				for (int j = 0; j <= maxRear; j++) {
					DP[i][j] = new ArrayList<Integer>();
				}
			}

			System.out.println("N is " + N);
			System.out.println("Maxrear is " + maxRear);

			for (int i = 1; i <= N; i++) {
				for (int j = 0; j <= maxRear; j++) {
					if (j == rear[i]) {
						System.out.println("cur i is " + i);
//						String concat = DP[i - 1][front[i]] + front[i] + " " + rear[i] + " ";
						ArrayList<Integer> list = (ArrayList<Integer>) DP[i - 1][front[i]];
						ArrayList<Integer> newList = (ArrayList<Integer>) list.clone();
						newList.add(front[i]);
						newList.add(rear[i]);
//						System.out.println("concat is " + concat);
						if (DP[i - 1][j].size() == 0 || newList.size() > DP[i - 1][j].size())
							DP[i][j] = newList;
						else
							DP[i][j] = DP[i - 1][j];
					} else {
						if (!(DP[i - 1][j].size() == 0) && (DP[i - 1][j].get(0) == rear[i])) {
							System.out.println("�տ� ���� �� ����");
							ArrayList<Integer> list = new ArrayList<>();
							list.add(front[i]);
							list.add(rear[i]);
							list.addAll(DP[i - 1][j]);
							DP[i][j] = list;
							System.out.println(i + " " + j + " " + DP[i][j]);
						} else {
							DP[i][j] = DP[i - 1][j];
						}
					}
				}
			}

			for (int i = 1; i <= N; i++) {
				System.out.println();
				for (int j = 0; j <= maxRear; j++) {
					System.out.print("|" + DP[i][j] + "|");
				}
			}
		}

	}

}
