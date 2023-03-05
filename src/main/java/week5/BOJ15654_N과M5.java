package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15654_N과M5 {
	
	static int N;
	static int M;
	static int arr[];
	static int visited[];
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
		visited = new int[arr[N-1]+1];
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
				if(visited[arr[i]]==0) {
					visited[arr[i]] =1;
					res[level] = arr[i];
					permutation(level+1,res);
					visited[arr[i]] =0;
				}
				
				
			}
		}
	}
	
	

}
