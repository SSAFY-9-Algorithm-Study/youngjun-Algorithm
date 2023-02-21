package week3.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Set_P1269 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); 
		int m = Integer.parseInt(st.nextToken()); 
		Set<Integer> first = new HashSet<Integer>();
		Set<Integer> second = new HashSet<Integer>();
		
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			 first.add(Integer.parseInt(st.nextToken()));
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			second.add(Integer.parseInt(st.nextToken()));
		}
		
		Set<Integer> tmp = new HashSet<Integer>(first);
		tmp.retainAll(second);
		first.removeAll(tmp);
		second.removeAll(tmp);
		first.addAll(second);
		System.out.println(first.size());
	}

}
