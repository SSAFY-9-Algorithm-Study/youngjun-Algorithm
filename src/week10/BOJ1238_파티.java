package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1238_파티 {
	
	static int N,M,X;
	static List<Link>[] linkList;
	static int max = Integer.MIN_VALUE;
	static class Link{
		int end;
		int dist;
		
		public Link(int end, int dist) {
			super();
			this.end = end;
			this.dist = dist;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		linkList = new ArrayList[M+1];
		for (int i = 1; i <= M; i++) {
			linkList[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			linkList[Integer.parseInt(st.nextToken())].add(new Link(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))) ;
		}
		
		for (int i = 1; i <= N; i++) {
			max = Math.max( calDist(X,i) + calDist(i,X) , max);
		}
		System.out.println(max);
		
		
	}

	private static int calDist(int start, int dest) {
		int[] distArr = new int[N+1];
		int[] visited = new int[N+1];
		int visitedLen = 0;
		
		for (int i = 1; i <= N; i++) {
			distArr[i] = Integer.MAX_VALUE;
		}
		
		
		distArr[start] = 0;
		
		for (int i = 0; i < linkList[start].size(); i++) {
			Link link = linkList[start].get(i);
			int end = link.end;
			int dist = link.dist;
			distArr[end] = dist;
		}
		visited[start]=1;
		visitedLen++;
		
		
		while(visitedLen<N) {
			
			//최소거리 노드 선택
			int minDist = Integer.MAX_VALUE;
			int selectedNode = -1;
			for (int i = 1; i <= N; i++) {
				if(visited[i]==0 && distArr[i]<minDist) {
					minDist = distArr[i];
					selectedNode = i;
				}
			}
			
			for (int i = 0; i < linkList[selectedNode].size(); i++) {
				Link link = linkList[selectedNode].get(i);
				int end = link.end;
				int dist = link.dist;
				distArr[end] = Math.min(distArr[end], distArr[selectedNode]+dist);
			}
			
			visited[selectedNode]=1;
			visitedLen++;
			
			
		}
		return distArr[dest];
	}

}
