package week18;

import java.io.*;
import java.security.KeyStore.Entry;
import java.util.*;

//1
//6
//I 1
//I 1
//I 1
//I 1
//D -1
//D 1

//1
//9
//I 36
//I 37
//I 38
//D 1
//D 1
//I 39
//I 40
//D -1
//D -1
public class BOJ7662_이중우선순위큐2 {

	static int T, N;
	static TreeMap<Long, Integer> map = new TreeMap<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T + 1; t++) {
			N = Integer.parseInt(br.readLine());
			map.clear();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				char ops = st.nextToken().charAt(0);
				long num = Long.parseLong(st.nextToken());
				switch (ops) {
				case 'I':
					enque(num);
					break;
				case 'D':
					deque(num);
					break;
				default:
					break;
				}
			}

			if (map.isEmpty())
				System.out.println("EMPTY");
			else
				System.out.println(map.lastKey() + " " + map.firstKey());
		}

	}

	private static void deque(long num) {
		if (map.isEmpty())
			return;
		long key = (num == -1) ? map.firstKey() : map.lastKey();
		if (map.get(key) == 1)
			map.remove(key);
		else
			map.put(key, map.get(key) - 1);
	}

	private static void enque(long num) {
		map.put(num, map.getOrDefault(num, 0) + 1);
	}

}
