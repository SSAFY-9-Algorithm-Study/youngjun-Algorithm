package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15656_N과M7 {
	
	static int N;
	static int M;
	static int arr[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		permutation(0,new int[M]);
		
		System.out.println(sb);
	}
	
	public static void permutation(int level, int[] res) {
		if(level == M) {
			for (int i = 0; i < res.length; i++) {
				sb.append(res[i]);
				sb.append(" ");
			}
			sb.append("\n");
		}
		
		else {
			for (int i = 0; i < arr.length; i++) {
					res[level] = arr[i];
					permutation(level+1,res);
				
			}
		}
	}
	
	

}
