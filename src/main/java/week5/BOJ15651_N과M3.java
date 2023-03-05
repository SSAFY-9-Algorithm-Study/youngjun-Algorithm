package week5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15651_N과M3 {
	
	static int N;
	static int M;
	static int visited[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new int[N];
		
		permutation(N, M, 0, new int[M]);
		System.out.println(sb);
		
	}
	
	public static void permutation(int n, int m, int level, int[] res) {
		if(level == m) {
			for (int i = 0; i < res.length; i++) {
				sb.append(res[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		else {
			for (int i = 0; i < n; i++) {
				if(visited[i]==0) {
//					visited[i]=1;
					res[level] = i+1;
					permutation(n, m, level+1, res);
//					visited[i]=0;
				}
				
			}
		}
	}

}
