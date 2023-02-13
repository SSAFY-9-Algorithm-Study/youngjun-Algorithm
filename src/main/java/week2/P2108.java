package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P2108 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		Map<Integer, Integer> map = new HashMap<>();
		int arr[] = new int[n];
		int sum = 0;

		//입력값 배열과 map에 저장
		for (int i = 0; i < n; i++) {
			
			int num = Integer.parseInt(bf.readLine());
			sum+=num;
			arr[i] = num;
			if(map.get(num)==null) {
				map.put(num, 1);
			}
			else
				map.put(num, map.get(num)+1);
			
		}

		//중앙값, 차이값 찾기 위해 오름차순 정렬
		Arrays.sort(arr);
		
		//빈도수 내림차순, 같은 빈도일시 오름차순 정렬
		List<Integer> keyList = new ArrayList<>(map.keySet());
		Collections.sort(keyList, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {

				if( map.get(o1) == map.get(o2)) {
					return o1-o2;
				}
				return map.get(o2)-map.get(o1);
			}

		});

		//평균
		System.out.println(Math.round(((double)sum/n)));
		//중앙값
		System.out.println(arr[n/2]);

		//최빈값
		if(keyList.size()==1) {
			System.out.println(keyList.get(0));
		}
		else if(map.get(keyList.get(0)) == map.get(keyList.get(1)))
			System.out.println(keyList.get(1)  );
		else {
			System.out.println(keyList.get(0) );
		}
		//최대최소차이
		System.out.println(arr[n-1]-arr[0]);
		
		
	}
	
	
}
