package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P14501 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int num = Integer.parseInt(br.readLine());
		int DP[] = new int[num+1];
		int days[] = new int[num];
		int money[] = new int[num];
		
		for (int i = 0; i < num; i++) {
			st = new StringTokenizer(br.readLine());
			days[i] = Integer.parseInt(st.nextToken());
			money[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = num-1; i >= 0 ; i--) {
			if((days[i]+i)>num) {
				DP[i] = DP[i+1];
			}
		}
		

		
	}

}
