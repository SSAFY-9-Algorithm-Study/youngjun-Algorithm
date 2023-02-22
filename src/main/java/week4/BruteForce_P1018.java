package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BruteForce_P1018 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int arr[][] = new int[h][w];
		int min = 64;
		
		for (int i = 0; i < h; i++) {
			char[] row = br.readLine().toCharArray();
			for (int j = 0; j < w; j++) {
				arr[i][j] = row[j];
			}
		}
		
		for (int i = 0; i <= h-8; i++) {
			for (int j = 0; j <= w-8; j++) {
				int newArr[][] = new int[8][8];
				for (int k = 0; k < 8; k++) {
					for (int k2 = 0; k2 < 8; k2++) {
						newArr[k][k2] = arr[i+k][j+k2];
					}
				}
				int curMin = minCheck(newArr);
				if(curMin<min)
					min = curMin;
				
			}
		}
		
		System.out.println(min);
		
		
		
	}
	
	public static int minCheck(int arr[][]) {
		int cnt1 = 0;
		int cnt2 = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(((i+j)%2 == 0 && arr[i][j] == 'W') || ((i+j)%2 == 1 && arr[i][j] == 'B'))
					cnt1+=1;
			}
		}
		
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(((i+j)%2 == 0 && arr[i][j] == 'B') || ((i+j)%2 == 1 && arr[i][j] == 'W'))
					cnt2+=1;
			}
		}
		
		return Math.min(cnt1, cnt2);
	}

}
