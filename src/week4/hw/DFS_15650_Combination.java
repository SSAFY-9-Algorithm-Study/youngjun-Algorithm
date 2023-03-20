package week4.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DFS_15650_Combination {
	
	static int res[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		res = new int[N];
		dfs(M,N,0,0);
		
		
		
	}
	
	public static void dfs(int M, int N, int depth, int begin) {
		if(depth==N) {
			for(int val : res) {
				System.out.print(val + " ");
			}
			System.out.println();
		}
		
		else {
			for (int i = begin; i < M; i++) {
				res[depth] = i+1;
				dfs(M,N,depth+1,i+1);
			}
		}
	}

}
