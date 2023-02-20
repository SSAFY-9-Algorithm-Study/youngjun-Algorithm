package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BonusP14889 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int nums[] = new int[n];
		List<int[]> comb = new ArrayList<>();
		int[] list = new int[2];
		int [][] mat = new int[n][n];
		for (int i = 0; i < n; i++) {
			nums[i] = i;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
			
		}
		DFS(0,0,n,comb,list,nums);
		
	}
	
	public static void DFS(int L, int begin, int n, List<int[]> comb,  int[] list, int[] nums) {
		
		if(L==2) {
			System.out.println(Arrays.toString(list));
			comb.add(list);
		}
		else {
			for (int i = begin; i < n; i++) {
				list[L] = nums[i];
				DFS(L+1,i+1,n,comb,list,nums);
			}
		}
		
		
		
	}
	

}
