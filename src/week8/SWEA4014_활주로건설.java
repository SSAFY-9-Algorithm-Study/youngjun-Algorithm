package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA4014_활주로건설 {

	static int T;
	static int N;
	static int X;
	static int[][] mat;
	static int[] checkArr;
	static int ans;

	static class Road {
		int height;
		int leastDup = 0;

		public Road(int height) {
			super();
			this.height = height;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			mat = new int[N][N];
			checkArr = new int[N];
			ans = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}

			}
			// 가로 방향 체크
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					checkArr[j] = mat[i][j];
				}
				if (check(checkArr))
					ans++;
			}
			// 세로 방향 체크
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					checkArr[j] = mat[j][i];
				}
				if (check(checkArr))
					ans++;
			}
			System.out.println("#" + t + " " + ans);
		}
	}

	private static boolean check(int[] arr) {
		List<Road> shrinkedArr =  getShrinkedArr(arr);
		if(shrinkedArr==null) return false;
		int shrinkedArrIdx = -1;
		int cur = -1;
		int dup = 1;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]!=cur) {
				if(cur!=-1 && dup<shrinkedArr.get(shrinkedArrIdx).leastDup)
					return false;
				shrinkedArrIdx++;
				cur = arr[i];
				dup = 1;
			}
			else
				dup++;
		}
		if(dup<shrinkedArr.get(shrinkedArr.size()-1).leastDup)
			return false;
		return true;

	}

	private static List<Road> getShrinkedArr(int[] arr) {
		List<Road> shrinkedArr = new ArrayList<>();
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] != arr[i + 1])
				shrinkedArr.add(new Road(arr[i]));
		}
		if (shrinkedArr.size() == 0 || arr[arr.length - 1] != shrinkedArr.get(shrinkedArr.size() - 1).height)
			shrinkedArr.add(new Road(arr[arr.length - 1]));
		

		for (int i = 0; i < shrinkedArr.size() - 1; i++) {
			if(Math.abs(shrinkedArr.get(i).height- shrinkedArr.get(i + 1).height)>1 )
				return null;
			if (shrinkedArr.get(i).height - shrinkedArr.get(i + 1).height == -1) {
				shrinkedArr.get(i).leastDup+=X;
			}
		}
		
		for (int i = shrinkedArr.size() - 1; i > 0; i--) {
			if (shrinkedArr.get(i).height - shrinkedArr.get(i - 1).height == -1) {
				shrinkedArr.get(i).leastDup+=X;
			}
		}
		return shrinkedArr;

	}

}
