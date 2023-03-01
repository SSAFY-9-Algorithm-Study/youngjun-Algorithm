package week4.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DFS_15649v2_Permutation {
	
	public static int[] visited;
	public static int[] res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		visited = new int[n];
		res = new int[m];
		dfs(n,m, 0);
		
	}
	
	public static void dfs(int n, int m, int depth) {
		if(depth==m) {
			for(int val : res) {
				System.out.print(val + " ");
			}
			System.out.println();
			return;
		}
		
		else {
			for (int i = 0; i < n; i++) {
				if(visited[i]==0) {
					visited[i]=1;
					res[depth]=i+1;
					dfs(n,m,depth+1);
					visited[i]=0;
				}
				
				
				
			}
		}
	}
	


}
