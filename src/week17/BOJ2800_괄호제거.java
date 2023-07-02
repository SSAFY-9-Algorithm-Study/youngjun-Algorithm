package week17;

import java.util.*;
import java.io.*;

public class BOJ2800_괄호제거 {

	static String str;
	static int[] bracketNum;
	static BufferedWriter bw;
	static int ansCnt;
	static List<String> stringList = new ArrayList<>();
	static String curString = "";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		str = br.readLine();
		bracketNum = new int[str.length()];
		int brackCnt = 0;
		Deque<Integer> q = new LinkedList<>();
		for (int i = 0; i < str.length(); i++) {
			char curChar = str.charAt(i);

			// brackNum 배열에서 열린괄호 닫힌괄호를 같은 번호로 매겨줌
			if (curChar == '(') {
				bracketNum[i] = ++brackCnt;
				q.add(brackCnt);
			} else if (curChar == ')') {
				bracketNum[i] = q.pollLast();
			}
		}

		dfs(0, new StringBuilder(), new boolean[brackCnt + 1]); // 현재index, 현재 string, bracket 담겨있는지 알려주는 배
		Collections.sort(stringList);

		// ((a)) 와 같이 중복 괄호로 묶여있으면 중복 값 존재 가능
		for (String str : stringList) {
			if (!curString.equals(str)) {
				bw.append(str);
				bw.append("\n");
				curString = str;
			}
		}
		bw.flush();
		bw.close();
	}

	// sb가 모든 dfs에서 같이 공유되고 있으므로 sb.append를 항상 반환해줘야함
	private static void dfs(int idx, StringBuilder sb, boolean[] brackOpened) throws IOException {
		if (idx == str.length()) {
			if (ansCnt++ == 0)
				return;
			stringList.add(sb.toString());
			return;
		}

		char curChar = str.charAt(idx);

		// 여는 괄호일 경우 (를 넣거나 넣지 않거
		if (curChar == '(') {
			sb.append(curChar);
			// bracketOpened 배열의 해당 bracket idx의 값을 true로 바꿔줌
			brackOpened[bracketNum[idx]] = true;
			dfs(idx + 1, sb, brackOpened);
			brackOpened[bracketNum[idx]] = false;
			sb.setLength(sb.length() - 1);

			dfs(idx + 1, sb, brackOpened);
		}

		// 닫는 괄호일 경우 자신의 짝이 열려있으면 닫고 아니면 안닫음
		else if (curChar == ')') {
			if (brackOpened[bracketNum[idx]]) {
				sb.append(curChar);
				dfs(idx + 1, sb, brackOpened);
				sb.setLength(sb.length() - 1);
			} else {
				dfs(idx + 1, sb, brackOpened);
			}
		}

		// 괄호가 아니면 무조건 append 해줌
		else {
			sb.append(curChar);
			dfs(idx + 1, sb, brackOpened);
			sb.setLength(sb.length() - 1);
		}

	}
}
