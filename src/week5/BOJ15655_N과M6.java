package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15655_N과M6 {
	
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
		combination(0,new int[M],0);
		
		System.out.println(sb);
	}
	
	public static void combination(int level, int[] res, int begin) {
		if(level == M) {
			for (int i = 0; i < res.length; i++) {
				sb.append(res[i]);
				sb.append(" ");
			}
			sb.append("\n");
		}
		
		else {
			for (int i = begin; i < arr.length; i++) {
				if(visited[arr[i]]==0) {
					visited[arr[i]] =1;
					res[level] = arr[i];
					combination(level+1,res,i+1);
					visited[arr[i]] =0;
				}
				
				
			}
		}
	}
	
	

}
