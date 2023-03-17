package week4.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BFS_P15686_치킨배달 {
	
	static int n;
	static int m;
	static int mat[][];
	static int visited[];
	static List<int[]> chickens = new ArrayList<>();
	static List<int[]> house = new ArrayList<>();
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		mat = new int[n][n];
	
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int val = Integer.parseInt(st.nextToken());
				if(val==2) chickens.add(new int[] {i,j});
				if(val==1) house.add(new int[] {i,j});
				mat[i][j] = val;
			}
		}
		visited = new int[chickens.size()];
		combination(m,0, visited,0);
		System.out.println(min);
		
//		for (int i = 0; i < n; i++) {
//			System.out.println();
//			for (int j = 0; j < n; j++) {
//				System.out.print(mat[i][j]);
//			}
//			
//		}
		
		
	}
	
	public static int distSum(List<int[]> house, List<int[]> chicken, int[] visited) {
		int distSum = 0;
		
		
		for (int i = 0; i < house.size(); i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < chicken.size(); j++) {
				if(visited[j]==1) {
					int dist = (Math.abs(house.get(i)[0] - chicken.get(j)[0])+Math.abs(house.get(i)[1] - chicken.get(j)[1]));
					min = Math.min(dist, min);
				}
			}
			distSum+=min;
		}
		return distSum;
	}
	
	public static void combination(int L, int level, int[] visited, int begin) {
		if(L==level) {
//			System.out.println(Arrays.toString(visited));
			int distSum = distSum(house,chickens,visited);
			min = distSum<min ? distSum : min;
		}
		else {
			for (int i = begin; i < chickens.size(); i++) {
				if(visited[i]==0) {
					visited[i]=1;
					combination(L,level+1,visited,i+1);
					visited[i]=0;
				}
				
			}
		}
	}
	


}
