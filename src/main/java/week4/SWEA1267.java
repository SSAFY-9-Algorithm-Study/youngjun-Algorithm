package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA1267 {

	static int T = 10;
	static LinkedList<ArrayList<Integer>> arr;
	static int n;
	static int[] visited;
	static ArrayList<Integer> res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 1; i < T + 1; i++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int routes = Integer.parseInt(st.nextToken());
			arr = new LinkedList<ArrayList<Integer>>();
			visited = new int[n + 1];
			res = new ArrayList<>();

			for (int j = 0; j < n + 1; j++) {
				arr.add(new ArrayList<>());
			}

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < routes; j++) {

				int base = Integer.parseInt(st.nextToken());
				int des = Integer.parseInt(st.nextToken());

				arr.get(des).add(base);

			}
			

			search(0);
			System.out.print("#" + i);
			for(int val : res) {
				System.out.print(" " + val);
			}
			System.out.println();


		}
	}

	public static void search(int L) {
		if (L == n)
			return;
		else {
			for (int i = 1; i < arr.size(); i++) {
				if (arr.get(i).size() == 0 && visited[i]==0) {
					res.add(i);
					visited[i]=1;
					for (int j = 1; j < arr.size(); j++) {
						if(arr.get(j).contains(i))
							arr.get(j).remove(Integer.valueOf(i));
					}
					search(L+1);
				}
			}

		}
	}

}
