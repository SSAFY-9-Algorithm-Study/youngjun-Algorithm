package week4.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DFS_P2667 {
	
	static int n;
	static int[][] arr;
	static int checkNum = 2;
	static int cnt = 1;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		List<Integer> ans = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				arr[i][j] = str.charAt(j)-'0';
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arr[i][j] == 1) {
					arr[i][j]=2;
					dfs(i,j);
					if(cnt>0) {
						ans.add(cnt);
						cnt=1;
					}
				}

				
			}
		}
		
		
		Collections.sort(ans);
		System.out.println(ans.size());
		for(int answer : ans) {
			System.out.println(answer);
		}
	}
	
	public static void dfs(int x, int y) {
		int[] dx = {0,0,-1,1};
		int[] dy = {-1,1,0,0};
		
		if(y+dy[0]>=0 && arr[x+dx[0]][y+dy[0]]==1) {
			arr[x+dx[0]][y+dy[0]] = checkNum;
			cnt++;
			dfs(x+dx[0],y+dy[0]);
		}
		
		if(y+dy[1]<n && arr[x+dx[1]][y+dy[1]]==1) {
			arr[x+dx[1]][y+dy[1]] = checkNum;
			cnt++;
			dfs(x+dx[1],y+dy[1]);
		}
		
		if(x+dx[2]>=0 && arr[x+dx[2]][y+dy[2]]==1) {
			arr[x+dx[2]][y+dy[2]] = checkNum;
			cnt++;
			dfs(x+dx[2],y+dy[2]);
			
		}
		
		if(x+dx[3]<n && arr[x+dx[3]][y+dy[3]]==1) {
			arr[x+dx[3]][y+dy[3]] = checkNum;
			cnt++;
			dfs(x+dx[3],y+dy[3]);
		}

	}
	

}
