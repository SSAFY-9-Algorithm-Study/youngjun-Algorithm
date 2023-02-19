package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class P2910 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int maxNum = Integer.parseInt(st.nextToken());
		Map<Integer, Integer> m = new LinkedHashMap<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			m.put(num, m.getOrDefault(num,0)+1);
			
		}
		List<Integer> keyList = new ArrayList<>(m.keySet());
		keyList.sort((o1,o2)->m.get(o2)-m.get(o1));
		
		for(int key : keyList) {
			for (int i = 0; i < m.get(key); i++) {
				System.out.print(key + " ");
			}
		}
		
	}

}
