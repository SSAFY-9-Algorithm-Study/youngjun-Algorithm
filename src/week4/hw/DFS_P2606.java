package week4.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class DFS_P2606 {
	static int n;
	static List<Integer>[] arr;
	static Set<Integer> set;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int link = Integer.parseInt(br.readLine());
		arr = new ArrayList[n + 1];
		set = new HashSet<>();

		StringTokenizer st;
		
		for (int i = 0; i < n+1; i++) {
			arr[i] = new ArrayList<>();
		}
		

		for (int i = 0; i < link; i++) {
			st = new StringTokenizer(br.readLine());
			int base = Integer.parseInt(st.nextToken());
			int des = Integer.parseInt(st.nextToken());
			
			arr[base].add(des);
			arr[des].add(base); //무방향 그래프이므로 출발지<->목적지 구분 없이 한번 더 넣어줌

		}
		search(1);
		System.out.println(set.size()-1); // 1 제외

	}

	public static void search(int base) {
		if(set.size()==n-1)
			return;
		
		for (int i = 0; i < arr[base].size(); i++) {
			int visited = arr[base].get(i);	
			if(!set.contains(visited)) {//이미 순회중인 지점은 다시 순회 불필요
				set.add(visited);
				search(visited);
			}
		}
	}

}
