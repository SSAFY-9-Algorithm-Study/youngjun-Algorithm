package week14;

import java.util.*;
import java.io.*;

public class BOJ5430_AC {

	static int T, N;
	static char[] fun;
	static boolean isPossib, isFront = true;
	static Deque<Integer> deq, tempDeq = new LinkedList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {

			fun = br.readLine().toCharArray();
			N = Integer.parseInt(br.readLine());
			String arrString = br.readLine();
			deq = new LinkedList<>();
			tempDeq = new LinkedList<>();
			isPossib = true;
			isFront = true;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < arrString.length(); i++) {
				try {
					int val = Integer.parseInt(String.valueOf(arrString.charAt(i)));
					sb.append(val);
				} catch (Exception e) {
					if (sb.length() > 0) {
						deq.offer(Integer.parseInt(sb.toString()));
						sb = new StringBuilder();
					}
				}
			}
			runFun(fun);
		}

	}

	private static void runFun(char[] fun2) {

		Queue<Character> que = new LinkedList<>();
		for (char funChar : fun2) {
			que.add(funChar);
		}

		while (!que.isEmpty()) {
			char funChar = que.poll();
			if (!isPossib)
				break;
			if (funChar == 'R') {
				isFront = !isFront;
			} else {
				firstOmit();
			}
		}

		if (isPossib) {
			if (isFront) {
				System.out.println(deq.toString().replaceAll("\\s", ""));
			} else {
				while (!deq.isEmpty())
					tempDeq.add(deq.pollLast());

				System.out.println(tempDeq.toString().replaceAll("\\s", ""));
			}
		} else
			System.out.println("error");

	}

	private static void firstOmit() {
		if (deq.isEmpty()) {
			isPossib = false;
			return;
		}
		if (isFront)
			deq.pollFirst();
		else
			deq.pollLast();
	}

}
