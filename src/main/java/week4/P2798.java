package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2798 {
	static int N;
	static int M;
	static int[] nums;
	static int[] res;
	static int ans;
	static int absVal;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		absVal= M;
		nums = new int[N];
		res = new int[3];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		DFS(0,0);
		System.out.println(ans);
		
	

	}
	
	public static void DFS(int begin, int L) {
		if(L==3) {
			if(M - sum(res)>=0 && Math.abs(M - sum(res))<absVal){
				absVal = Math.abs(M - sum(res));
				ans = sum(res);
			}
		}
		else {
			for (int i = begin; i < N; i++) {
				res[L] = nums[i];
				DFS(i+1,L+1);
			}
		}
	}
	
	public static int sum(int[] res) {
		int sum = 0;
		for(int val : res) {
			sum+=val;
		}
		return sum;
	}

}
