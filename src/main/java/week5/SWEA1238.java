package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA1238 {

	static int T = 10;
	static Map<Integer, List<Integer>> map;
	static Set<Integer> unvisited;
	static Deque<int[]> deq;
	static Deque<int[]> newDeq;
	static List<Integer> ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new HashMap<Integer, List<Integer>>();
		deq = new LinkedList<int[]>();
		newDeq = new LinkedList<int[]>();
		ans = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		int path = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < path / 2; i++) {
			int key = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			if (map.get(key) == null) {
				List<Integer> val1 = new ArrayList<>();
				val1.add(val);
				map.put(key, val1);
			}

			else {
				List<Integer> val1 = map.get(key);
				val1.add(val);
				map.put(key, val1);
			}
		}

		unvisited = map.keySet();

		System.out.println(map);
		

		bfs(start, 1);

		int max = 1;
		
//		System.out.println(newDeq);
		for (int[] a : newDeq) {
			if (a[1] > max) {
				max = a[1];
			}
		}
		for (int[] a : newDeq) {
			System.out.println(Arrays.toString(a));
			if (a[1] == max) {
				ans.add(a[0]);
			}
		}

		Collections.sort(ans, Collections.reverseOrder());
		System.out.println(ans.get(0));

	}

	public static void bfs(int start, int call) {
//		unvisited.remove(Integer.valueOf(start));
		int[] arr = { start, call };
		deq.add(arr);

		while (!deq.isEmpty()) {
			int first = deq.pollFirst()[0];
//			System.out.println("first is " + first);
//			System.out.println(map);
			if (map.containsKey(Integer.valueOf(first))) {
//				System.out.println("contains!");
				for (int val : map.get(first)) {
					if (unvisited.contains(Integer.valueOf(val))) {
//						unvisited.remove(Integer.valueOf(val));
						int[] newArr = { val, call+1 };
						System.out.println("val is" + val);
						System.out.println("call is" + (call+1));
						deq.add(newArr);
						newDeq.add(newArr);
					}
					
				}
				
				
			}
			unvisited.remove(Integer.valueOf(first));
		}

	}
}
