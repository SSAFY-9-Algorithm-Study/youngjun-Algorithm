package week4.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DFS_15649 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		dfs(n,r, 0, new ArrayList<>());
		
	}
	
	public static void dfs(int n, int r, int level, List<Integer> res) {
		if(r==level) {
			for (int i = 0; i < res.size(); i++) {
				System.out.print(res.get(i) + " ");
			}
			System.out.println();
		}
		else {
			for (int i = 0; i < n; i++) {
				if(!res.contains(i+1)) {
					res.add(i+1);
					dfs(n,r,level+1,res);
					res.remove(Integer.valueOf(i+1));
				}
				
			}
		}
	}

}
