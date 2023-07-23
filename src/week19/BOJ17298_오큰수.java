package week19;

import java.io.*;
import java.util.*;

public class BOJ17298_오큰수 {

	static int N;
	static Deque<Integer> numStack = new LinkedList<>();
	static Deque<Integer> tempStack = new LinkedList<>();
	static int[] ansArr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		ansArr = new int[N];
		st = new StringTokenizer(br.readLine());

		int idx = N - 1;
		for (int i = 0; i < N; i++) {
			numStack.add(Integer.parseInt(st.nextToken()));
		}

		while (!numStack.isEmpty()) {
			int val = numStack.pollLast();
			ansArr[idx--] = checkTempStack(val);
		}

		for (int val : ansArr) {
			sb.append(val);
			sb.append(" ");
		}

		System.out.println(sb);

	}

	private static int checkTempStack(int val) {
		int res = -1;
		while (!tempStack.isEmpty()) {
			int curTop = tempStack.peekLast();
			if (curTop > val) {
				res = curTop;
				break;
			} else {
				tempStack.pollLast();
			}
		}
		tempStack.add(val);
		return res;
	}

}
