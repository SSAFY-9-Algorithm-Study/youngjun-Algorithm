package week13;

import java.io.*;
import java.util.*;

public class SOFTEER_플레이페어 {
	static String msg;
	static String key;
	static char[][] keyMat = new char[5][5];
	static StringBuilder ans = new StringBuilder();
	static Map<Character, Integer> map = new HashMap<Character, Integer>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		msg = br.readLine();
		key = br.readLine();
		for (char i = 'A'; i <= 'Z'; i++) {
			if (i == 'J')
				continue;
			map.put(i, 0);
		}

		convertKey();
		convertMsgToAns(convertMsg());
		System.out.println(ans);

	}

	private static void convertMsgToAns(String convertMsg) {
		for (int i = 0; i < convertMsg.length(); i += 2) {
			int[] first = searchAlphaInArr(convertMsg.charAt(i));
			int[] second = searchAlphaInArr(convertMsg.charAt(i + 1));

			if (first[0] == second[0]) {
				if (first[1] == 4)
					first[1] = 0;
				else
					first[1]++;

				if (second[1] == 4)
					second[1] = 0;
				else
					second[1]++;
				ans.append(keyMat[first[0]][first[1]]);
				ans.append(keyMat[second[0]][second[1]]);

			} else if (first[1] == second[1]) {
				if (first[0] == 4)
					first[0] = 0;
				else
					first[0]++;

				if (second[0] == 4)
					second[0] = 0;
				else
					second[0]++;
				ans.append(keyMat[first[0]][first[1]]);
				ans.append(keyMat[second[0]][second[1]]);

			} else {
				ans.append(keyMat[first[0]][second[1]]);
				ans.append(keyMat[second[0]][first[1]]);
			}

		}

	}

	private static int[] searchAlphaInArr(char curChar) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (keyMat[i][j] == curChar) {
					return new int[] { i, j };
				}
			}
		}
		return null;
	}

	private static String convertMsg() {
		StringBuilder sb = new StringBuilder();
		Queue<Character> q = new LinkedList<>();
		for (char curChar : msg.toCharArray()) {
			q.add(curChar);
		}

		while (!q.isEmpty()) {
			char cur = q.poll();
			sb.append(cur);
			if (q.peek() == null) {
				sb.append('X');
				break;
			}
			if (cur != q.peek()) {
				sb.append(q.poll());
			} else {
				if (q.peek() == 'X') {
					sb.append('Q');

				} else {
					sb.append('X');
				}
			}
		}
		return sb.toString();
	}

	private static void convertKey() {
		int h = 0;
		int w = 0;

		for (int i = 0; i < key.length(); i++) {
			char curChar = key.charAt(i);
			if (map.get(curChar) == 0) {
				map.put(curChar, 1);
				keyMat[h][w] = curChar;
				int[] wh = widthHeightBoundCheck(w, h);
				w = wh[0];
				h = wh[1];
			}
		}
		List<Character> alphabetList = new ArrayList<>(map.keySet());
		Collections.sort(alphabetList);
		for (char curChar : alphabetList) {
			if (map.get(curChar) == 0) {
				keyMat[h][w] = curChar;
				int[] wh = widthHeightBoundCheck(w, h);
				w = wh[0];
				h = wh[1];
			}
		}

	}

	private static int[] widthHeightBoundCheck(int w, int h) {
		if (w < 4)
			w++;
		else {
			w = 0;
			h++;
		}
		return new int[] { w, h };
	}

}
