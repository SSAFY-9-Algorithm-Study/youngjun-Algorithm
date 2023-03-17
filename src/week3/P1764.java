package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class P1764 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int first = Integer.parseInt(st.nextToken());
		int second = Integer.parseInt(st.nextToken());
		Map<String, Integer> m = new HashMap<>();
		List<String> list = new ArrayList<>();
		
		for (int i = 0; i < first; i++) {
			String key = bf.readLine();
			m.put(key, m.getOrDefault(key, 0) + 1);
		}
		
		for (int i = 0; i < second; i++) {
			String key = bf.readLine();
			m.put(key, m.getOrDefault(key, 0) + 1);
			if (m.get(key) == 2) 
				list.add(key);
		}
		
		Collections.sort(list);
		System.out.println(list.size());
		for (String string : list) {
			System.out.print(string + " ");
		}
		
		
		
	}

}
