package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA1219 {
	
	static int T = 10;
	static int[] way1 = new int[100];
	static int[] way2 = new int[100];
	static int res = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int i = 1; i <T+1; i++) {
			st= new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());
			st= new StringTokenizer(br.readLine());
			for (int j = 0; j < len; j++) {
				int src = Integer.parseInt(st.nextToken());
				int dest = Integer.parseInt(st.nextToken());
				if(way1[src]==0)
					way1[src] = dest;
				else
					way2[src] = dest;
			}
			go(0);
			System.out.println("#" + i + " " + res);
			res = 0;
			Arrays.fill(way1,0);
			Arrays.fill(way2,0);
			
		}
		
		
		
		
	}
	
	public static void go(int src) {
		if(src==99) {
			res = 1;
			return;
		}
		else {
			if(way1[src]!=0) {
				go(way1[src]);
				way1[src] = 0;
			}
			if(way2[src]!=0) {
				go(way2[src]);
				way2[src] = 0;
			}
		}
			
			
	}

}
