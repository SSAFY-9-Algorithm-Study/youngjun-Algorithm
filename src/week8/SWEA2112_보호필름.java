package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA2112_보호필름 {

	static int T;
	static int D;
	static int W;
	static int K;
	static int[][] mat;
	static int[][] temp;
	static int[][] resMat;
	static boolean checked = false;
	static int usedAns = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			mat = new int[D][W];
			resMat = new int[D][W];
			temp = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			for (int i = 0; i < D; i++) {
				for (int j = 0; j < W; j++) {
					temp[i][j] = mat[i][j];
				}
			}

			for (int i = 0; i <= D; i++) {
				if(checked) break;
				dfs(0, new int[D], i, 0);
			}


			System.out.println("#" + t + " " + usedAns);
			usedAns = Integer.MAX_VALUE;
			checked = false;
		}
	}

	private static void dfs(int lev, int[] res, int destLev, int begin) {
		if (lev == destLev) {
			int level = 0;
			for (int i = 0; i < res.length; i++) {
				if (res[i] == 1)
					level++;
			}
			
			colorMat(res, level, 0, temp, 0);
			
			for (int i = 0; i < res.length; i++) {
				if (res[i] == 1)
					for (int j = 0; j < W; j++) {
						temp[i][j] = mat[i][j];
					}
			}

			return;
		} else {
			for (int i = begin; i < res.length; i++) {
				res[i] = 1;
				dfs(lev + 1, res, destLev, i + 1);
				res[i] = 0;
			}
		}

	}

	private static boolean check(int[][] res) {
		int dupCnt = 1;
		int cur = -1;
		for (int i = 0; i < W; i++) {
			cur = -1;
			dupCnt = 1;
			boolean curRowPossib = false;
			for (int j = 0; j < D; j++) {
				if (res[j][i] != cur) {
					if (dupCnt >= K) {
						curRowPossib = true;
						break;
					}
					dupCnt = 1;
					cur = res[j][i];
				} else
					dupCnt++;
			}
			if (dupCnt >= K) {
				curRowPossib = true;
			}
			if (!curRowPossib)
				return false;

		}
		return true;
	}

	private static void colorMat(int[] res, int destLev, int lev, int[][] resMat, int begin) {
		if(checked) return;
		if (lev == destLev) {
			if(check(resMat)) {
				usedAns = destLev;
				checked = true;
			}
		} else {
			for (int i = begin; i < D; i++) {
				if (res[i] == 1 ) {
					// 1로 색칠
					for (int j = 0; j < W; j++) {
						resMat[i][j] = 1;
					}
					colorMat(res, destLev, lev + 1, resMat, i+1);
					for (int j = 0; j < W; j++) {
						resMat[i][j] = mat[i][j];
					}

					// 0으로 색칠
					for (int j = 0; j < W; j++) {
						resMat[i][j] = 0;
					}
					colorMat(res, destLev, lev + 1, resMat, i+1);
					for (int j = 0; j < W; j++) {
						resMat[i][j] = mat[i][j];
					}
				}
			}
		}
	}

}
