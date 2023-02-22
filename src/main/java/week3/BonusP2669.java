package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BonusP2669 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Map<String, Integer> m = new HashMap<>();
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			int endX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());
			for (int j = startX; j < endX; j++) {
				for (int j2 = startY; j2 < endY; j2++) {
					int arr[] = { j, j2 };
					m.put(Arrays.toString(arr), m.getOrDefault(arr, 0) + 1);
				}
			}
		}
		List<String> list = new ArrayList<>(m.keySet());

		System.out.println(m.keySet().size());

	}

}
