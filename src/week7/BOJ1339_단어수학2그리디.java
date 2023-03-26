package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class BOJ1339_단어수학2그리디 {
	
	static int N;
	static String[] wordArr;
	static Set<Character> letters = new HashSet<>();
	static Map<Character, Integer> letterMap = new HashMap<>();
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < str.length(); j++) {
				int val = letterMap.getOrDefault(str.charAt(j), 0);
				val += Math.pow(10, str.length()-1-j);
				letterMap.put(str.charAt(j), val);
			}
		}
		List<Entry<Character, Integer>> letterMapList = new LinkedList<>(letterMap.entrySet());
		letterMapList.sort(Entry.comparingByValue());
		int start = 9;
		for (int i = letterMapList.size()-1; i >= 0; i--) {
			char key = letterMapList.get(i).getKey();
			ans+=letterMap.get(key) * start--;
		}
		System.out.println(ans);
	}
}
