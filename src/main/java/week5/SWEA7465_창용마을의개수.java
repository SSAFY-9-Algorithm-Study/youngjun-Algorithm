package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA7465_창용마을의개수 {

	static int T;
	static int N;
	static int M;
	static List<Integer>[] relation;
	static List<Integer> res = new ArrayList<>();
	static int visited[];
	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int i = 1; i < T + 1; i++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			relation = new ArrayList[N + 1];
			visited = new int[N+1];

			for (int j = 1; j < N + 1; j++) {
				relation[j] = new ArrayList<>();

			}
			for (int j = 0; j < M; j++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int dest = Integer.parseInt(st.nextToken());
				relation[start].add(dest);

			}
			
//			System.out.println(Arrays.toString(relation));
			
			for (int j = 1; j < N+1; j++) {
				if(visited[j]==0) {
					res.add(j);
					searchRel(j);
				}
				if(res.size()>0)
					ans++;
//				System.out.println(res);
				res = new ArrayList<>();
			}
			
			System.out.println("#" + i + " " + ans);
			ans = 0;

		}
	}
	
	public static void searchRel(int n) {
		
		if(relation[n].size()==0) {
			res.add(n);
			return;
		}
		
		
		for (int i = 0; i < relation[n].size(); i++) {
			if(visited[relation[n].get(i)] == 0) {
				visited[relation[n].get(i)]=1;
				res.add(relation[n].get(i));
				searchRel(relation[n].get(i));
//				res.remove(res.size()-1);
//				visited[relation[n].get(i)]=0;
				
			}
		}
	}

}
