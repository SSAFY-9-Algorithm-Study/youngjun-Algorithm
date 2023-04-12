package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ2565_전깃줄 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Map<Integer, Integer> map = new TreeMap<>();

		int n = Integer.parseInt(br.readLine());

		int maxVal = 1;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int key = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			maxVal = Math.max(maxVal, val);
			map.put(key, val);
		}

		// val 값의 최대값을 기준으로 배열 생성
		int[] arr = new int[maxVal + 1];

		// key 값을 기준으로 val 정렬
		List<Integer> keyList = new ArrayList<Integer>(map.keySet());
		List<Integer> valSorted = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			valSorted.add(map.get(keyList.get(i)));
		}

		// 자신의 배열 인덱스 이하의 값 +1 중의 최대값이 자신이 가질 수 있는 최대값
		for (int i = 0; i < n; i++) {
			int curVal = valSorted.get(i);
			arr[curVal] = 1;
			for (int j = 0; j < curVal; j++) {
				arr[curVal] = Math.max(arr[curVal], arr[j] + 1);
			}
		}

		// 전체 전기줄 수 - 배열의 가장 최대값(최대길이)
		Arrays.sort(arr);
		System.out.println(n - arr[arr.length - 1]);

	}

}
