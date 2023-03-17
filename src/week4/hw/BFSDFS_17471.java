package week4.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BFSDFS_17471 {
	static int n;
	static int linkArr[][];
	static int visited[][];
	static int res[];
	static int bfsCase[];
	static List<int[]> resList;
	static int[] population;
	static boolean possible = true;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		population = new int[n+1];
		linkArr = new int[n+1][n+1];
		visited = new int[n+1][n+1];
		res = new int[n];
		bfsCase = new int[n+1];
		resList = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			population[i+1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int link = Integer.parseInt(st.nextToken());
			for (int j = 0; j < link; j++) {
				linkArr[i+1][Integer.parseInt(st.nextToken())]=1;
			}
		}
		
		
//		for (int i = 0; i < n+1; i++) {
//			System.out.println();
//			for (int j = 0; j < n+1; j++) {
//				System.out.print(linkArr[i][j]);
//			}
//		}
		
		for (int i = 1; i <= (n+1)/2; i++) {
			dfs(0,i,0);
		}
		
//		for (int i = 0; i < resList.size(); i++) {
//			System.out.println(Arrays.toString(resList.get(i)));  
//		}
		
		for (int i = 0; i < resList.size(); i++) {
			int[] newRes = resList.get(i);
			System.out.println("newRes is");
			System.out.println(Arrays.toString(newRes));
			int[] newResClone = new int[newRes.length];
			
			
			for (int j = 0; j < newRes.length; j++) {
				newResClone[j]=newRes[j];
			}
//			System.out.println(Arrays.toString(res));
			for (int j = 0; j < n; j++) {
				if(newResClone[j]==1) {
					newResClone[j]=0;
					newResClone = bfsForOne(j,newResClone);
					System.out.println("after bfsForOne");
					System.out.println(Arrays.toString(newResClone));
					visited = new int[n+1][n+1];
					break;
				}
			}
			for (int j = 0; j < n; j++) {
				if(newResClone[j]==1) {
//					System.out.println("impossilbe! 1");
					possible = false;
					break;
				}
					
			}

//			System.out.println("res is ");
//			System.out.println(Arrays.toString(res));
			
			for (int j = 0; j < newRes.length; j++) {
				newResClone[j]=newRes[j];
			}
			
			for (int j = 0; j < n; j++) {
				if(newResClone[j]==0) {
					newResClone[j]=1;
					visited = new int[n+1][n+1];
					newResClone = bfsForZero(j,newResClone);
					System.out.println("after bfsForZero");
					System.out.println(Arrays.toString(newResClone));
					visited = new int[n+1][n+1];
					break;
				}
			}
			
			for (int j = 0; j < n; j++) {
				if(newResClone[j]==0) {
//					System.out.println("impossilbe! 2");
					possible = false;
					break;
				}			
			}
			System.out.println(possible);
			
			if(possible) {
				int sum = 0;
				for (int j = 0; j < n; j++) {
					if(newRes[j]==1)
						sum-=population[j+1];
					else
						sum+=population[j+1];
				}
				min = Math.min(min, sum);
				
			}
			possible = true;
		}
		
		min = (min==Integer.MAX_VALUE) ? -1 : min;
		System.out.println(min);
		
		
	}
	
	
	public static void dfs(int level, int L, int begin) {
		if(level==L) {
			resList.add(res.clone());
//			System.out.println(Arrays.toString(res));
			return;
		}
		else {
			for (int i = begin; i < n; i++) {
				res[i] = 1;
				dfs(level+1,L,i+1);
				res[i] = 0;
			}
		}
	}
	
	public static int[] bfsForOne(int x, int res[]) {
		visited = new int[n+1][n+1];
		Deque<Integer> deq = new ArrayDeque<>();
		deq.offer(x);
		
		while(!deq.isEmpty()) {
			x = deq.pollFirst();
			for (int i = 1; i < n+1; i++) {
				if(linkArr[x][i]==1 && visited[x][i]==0) {
					visited[x][i]=1;
					deq.offer(i);
					res[i-1]=0;
				}
				
			}
		}
		return res;
	}
	
	public static int[] bfsForZero(int x, int[] res) {
		visited = new int[n+1][n+1];
		Deque<Integer> deq = new ArrayDeque<>();
		deq.offer(x);
		
		while(!deq.isEmpty()) {
			x = deq.pollFirst();
			for (int i = 1; i < n+1; i++) {
				if(linkArr[x][i]==1 && visited[x][i]==0) {
					visited[x][i]=1;
					deq.offer(i);
					System.out.println("i is" + i);
					res[i-1]=1;
				}
				
			}
		}
		
		return res;
	}

}
