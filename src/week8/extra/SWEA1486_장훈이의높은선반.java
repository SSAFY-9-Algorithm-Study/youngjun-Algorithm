package week8.extra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA1486_장훈이의높은선반 {
	static int T;
	static int N;
	static int B;
	static int[] height;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			height = new int[N];
			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(height);

			for (int i = 1; i <= N; i++) {
				comb(i, 0, 0, new int[N]);
			}
			System.out.println("#" + t + " " + (ans-B));
			ans = Integer.MAX_VALUE;

		}
	}

	private static void comb(int dest, int lev, int begin, int[] res) {
		if(lev==dest) {
//			System.out.println(Arrays.toString(res));
			int val = cal(res);
			if(val>=B) {
				ans = val;
			}
				
		}
		else {
			for (int i = begin; i < N; i++) {
				res[i] = 1;
				comb(dest,lev+1,i+1,res);
				res[i] = 0;
			}
		}

	}

	private static int cal(int[] res) {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			if(res[i]==1) {
				sum+=height[i];
				if(sum>ans) {
					return -1;
				}
			}
				
		}
		return sum;
		
	}

}
