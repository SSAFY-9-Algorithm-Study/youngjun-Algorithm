package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class P10815 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			map.put(Integer.parseInt(st.nextToken()), 1);
		}
		
		int n2 = Integer.parseInt(bf.readLine());
		int arr[] = new int[n2];
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < n2; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int val : arr) {
			if (map.containsKey(val))
				System.out.print("1 ");
			else
				System.out.print("0 ");
		}
		
		
	}

}
