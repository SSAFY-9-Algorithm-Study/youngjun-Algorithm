package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ1082_방번호 {

	static int N, money;
	static Map<Integer, Integer> map = new HashMap<>();
	static List<Integer> leastMoneyList;
	static List<Integer> mostNumList;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			map.put(i, Integer.parseInt(st.nextToken()));
		}

		money = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			List<Integer> numList = new ArrayList<Integer>();
			if (money >= map.get(i)) {
				numList.add(i);
				traverse(money - map.get(i), numList);
			}
		}

		leastMoneyList = new ArrayList<>(map.keySet());
		mostNumList = new ArrayList<>(map.keySet());

		Collections.sort(mostNumList, Collections.reverseOrder());

		Collections.sort(leastMoneyList, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return map.get(o1) - map.get(o2);
			}
		});
	}

	private static void traverse(int leftMoney, List<Integer> numList) {
		int idx = 0;
		int left = leftMoney;
		while (idx < N) {
			int leastIdx = leastMoneyList.get(idx);
			if (left - map.get(leastIdx) >= map.get(0)) {
				left -= map.get(leastIdx);
				numList.add(leastIdx);
				continue;
			} else {
				idx++;
			}
		}

		for (int i = 0; i < N; i++) {

		}

	}

}
