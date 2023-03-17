package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15652_N과M4 {
	
	static int N;
	static int M;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		permutation(N,M,0, new int[M],1);
		System.out.println(sb);
		
	}
	
	public static void permutation(int N, int M, int level, int res[], int start) {
		if(level == M) {
			for (int i = 0; i < M; i++) {
				sb.append(res[i]);
				sb.append(" ");
			}
			sb.append("\n");
		}
		
		else {
			for (int i = start; i <= N; i++) {
				res[level] = i;
				permutation(N,M,level+1, res, i);
			}
		}
			
	}

}
