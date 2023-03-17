package week3.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Map_P10815v2 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int arr1[] = new int[n];
		for (int i = 0; i < n; i++) {
			arr1[i] = Integer.parseInt(st.nextToken());
		}
		
		
		Arrays.sort(arr1);
		
		
		int n2 = Integer.parseInt(bf.readLine());
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < n2; i++) {
			if (binarySearch(Integer.parseInt(st.nextToken()),arr1)>-1)
				System.out.print("1 ");
			else
				System.out.print("0 ");
		}
		
		
	}
	
	public static int binarySearch(int key, int[] list) {
		
		int start = 0;
		int end = list.length-1;
		
		//Arrays.sort(list); sorted 되었다고 가정
		
		while(end>=start) {
			int mid = (start+end)/2;
			if(list[mid] == key)
				return mid;
			else if(list[mid] > key) {
				end = mid-1;
			}
			else {
				start = mid+1;
			}
		}
		return -1;
	}

}
