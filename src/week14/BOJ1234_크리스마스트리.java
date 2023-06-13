package week14;

import java.io.*;
import java.util.*;

public class BOJ1234_크리스마스트리 {
	
	static int N;
	static int[] colArr = new int[3];
	static long ans;
	static int combiVal;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < 3; i++) {
			colArr[i] = Integer.parseInt(st.nextToken());
		}
		
		if(N==0) ans =1;
		else
		dfs(1,0);
		System.out.println(ans);
		
	}

	private static void dfs(int lev, long cases) {
//		System.out.println("lev : " + lev + "cases " + cases);
		
		if(lev>N) {
			ans+=cases;
			return;
		}
		
		else {
			threeDivPossible(lev,cases);
			twoDivPossible(lev,cases);
			singleColPossible(lev,cases);
		}
		
	}

	private static void singleColPossible(int lev, long cases) {
		int caseCnt = 1;
		singleColDfs(lev, cases, caseCnt, 0);
		singleColDfs(lev, cases, caseCnt, 1);
		singleColDfs(lev, cases, caseCnt, 2);
		
	}

	private static void twoDivPossible(int lev, long cases) {
		if(lev%2!=0) return;
		int caseCnt = 1;
		for (int i = 0; i < lev/2; i++) {
			caseCnt*=(lev-i);
			caseCnt/=(i+1);
		}
		divideTwoDfs(lev, cases, caseCnt, 0, 1);
		divideTwoDfs(lev, cases, caseCnt, 1, 2);
		divideTwoDfs(lev, cases, caseCnt, 0, 2);
		
	}

	

	private static void threeDivPossible(int lev, long cases) {
		if(lev%3!=0 || lev==0)return;
		int caseCnt=1; 
		for (int i = 0; i < lev/3; i++) {
			caseCnt*=(lev-i);
			caseCnt/=(i+1);
		}
		for (int i = 0; i < lev/3; i++) {
			caseCnt*=(lev-i-lev/3);
			caseCnt/=(i+1);
		}
		divideThreeDfs(lev, cases, caseCnt);
	}
	
	static void divideThreeDfs(int lev, long cases, int caseCnt) {
		if(colArr[0]>=lev/3 && colArr[1]>=lev/3 && colArr[2]>=lev/3) {
			colArr[0]-=lev/3;
			colArr[1]-=lev/3;
			colArr[2]-=lev/3;
			dfs(lev+1,cases*caseCnt);
			colArr[0]+=lev/3;
			colArr[1]+=lev/3;
			colArr[2]+=lev/3;
		}
	}
	
	private static void divideTwoDfs(int lev, long cases, int caseCnt, int idx1, int idx2) {
		if(colArr[idx1]>=lev/2 && colArr[idx2]>=lev/2) {
			colArr[idx1]-=lev/2;
			colArr[idx2]-=lev/2;
			dfs(lev+1,cases*caseCnt);
			colArr[idx1]+=lev/2;
			colArr[idx2]+=lev/2;
		}
		
	}
	
	private static void singleColDfs(int lev, long cases, int caseCnt, int idx1) {
		if(colArr[idx1]>=lev) {
			colArr[idx1]-=lev;
			if(cases==0) cases = 1;
			dfs(lev+1,cases*caseCnt);
			colArr[idx1]+=lev;
		}
		
	}
	
	
	

}