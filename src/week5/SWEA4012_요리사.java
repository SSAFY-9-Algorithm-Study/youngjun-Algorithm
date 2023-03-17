package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA4012_요리사 {

	static int T;
	static int N;
	static int[][] mat;
	static int[] foodCase;
	static List<List> chosenFoodIdxList;
	static int visited[];
	static int synergySum;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			N = Integer.parseInt(br.readLine());
			mat = new int[N][N];
			foodCase = new int[N];
			chosenFoodIdxList = new ArrayList<>();
			visited = new int[N/2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			makeFoodCase(foodCase, N / 2, 0, 0);
			System.out.println("#" + t + " " + min);
			min = Integer.MAX_VALUE;
			
		}

	}

	public static void makeFoodCase(int[] foodCase, int L, int level, int begin) {
		if (level == L) {
			List<Integer> chosenFoodIdx = new ArrayList<>();
			List<Integer> unchosenFoodIdx = new ArrayList<>();
			for (int i = 0; i < foodCase.length; i++) {
				if(foodCase[i]==1)
					chosenFoodIdx.add(i);
				else {
					unchosenFoodIdx.add(i);
				}
			}
			synergySum = 0;
			foodPermutation(chosenFoodIdx, 0, new int[2]);
			int chosenSynergySum = synergySum;
			synergySum = 0;
			foodPermutation(unchosenFoodIdx, 0, new int[2]);
			int unchosenSynergySum = synergySum;
			int sub = Math.abs(chosenSynergySum-unchosenSynergySum);
			if(min>sub) min = sub;
		}

		else {
			for (int i = begin; i < foodCase.length; i++) {
				foodCase[i] = 1;
				makeFoodCase(foodCase, L, level + 1, i + 1);
				foodCase[i] = 0;
			}
		}
	}
	

	
	
	public static void foodPermutation(List<Integer> chosenFoodIdx, int level, int[] res) {
		if(level == 2) {
			synergySum+=mat[res[0]][res[1]];
		}
		else {
			for (int i = 0; i < chosenFoodIdx.size(); i++) {
				
				if(visited[i]==0) {
					visited[i] = 1;
					res[level] = chosenFoodIdx.get(i);
					foodPermutation(chosenFoodIdx, level+1, res);
					visited[i] = 0;
				}
				
			}
		}
	}

}
