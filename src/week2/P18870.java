package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class P18870 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(bf.readLine());
		int arr[] = new int[n];
		int orderedArr[] = new int[n];
		Map<Integer, Integer> map = new HashMap<>();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
		}
		
		System.arraycopy(arr, 0, orderedArr, 0, n);
		Arrays.sort(orderedArr);
		int rank = 0;
		
		for (int key : orderedArr) {
			if(!map.containsKey(key)) {
				map.put(key, rank++);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		 for (int key : arr) {
			 sb.append(map.get(key)).append(' ');
		}
		 System.out.println(sb);
		 
		 bf.close();

	}

}



//for (int i = 0; i < n-1; i++) {
//	if(orderedArr[i]!=orderedArr[i+1])
//		undupArr.add(orderedArr[i]);
//}
//undupArr.add(orderedArr[n-1]);
//
//for (int i = 0; i < n; i++) {
//	for (int j = 0; j < undupArr.size(); j++) {
//		if(arr[i] == undupArr.get(j)) {
//			System.out.print(j + " ");
//			break;
//		}
//	}
//}

