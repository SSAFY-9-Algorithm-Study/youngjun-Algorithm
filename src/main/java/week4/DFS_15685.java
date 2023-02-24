package week4;

import java.io.BufferedReader;

/*
 * 해당 세대는 이전세대의 90도 돌린지점을 갖다붙인것
 */


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class DFS_15685 {
	
	static int x;
	static int y;
	static int vec;
	static int gen;
	static int[][] mat;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		mat = new int[100][100];
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			vec = Integer.parseInt(st.nextToken());
			gen = Integer.parseInt(st.nextToken());
			
			
			dragon(x,y,vec,gen);
		}
			
			
//			for (int j = 0; j < 10; j++) {
//				System.out.println();
//				for (int j2 = 0; j2 < 10; j2++) {
//					System.out.print(mat[j][j2]);
//				}
//				
//			}
			
			for (int j = 0; j < 99; j++) {
				for (int j2 = 0; j2 < 99; j2++) {
					if (mat[j][j2]==1 && mat[j+1][j2]==1 && mat[j][j2+1]==1 && mat[j+1][j2+1]==1) {
						cnt++;
					}
					
				}
			}
			
			

		System.out.println(cnt);
	}
	
	public static int[]  dragon(int x, int y, int vec, int gen) {
		vec = vec%4;
		if(gen==0) {
			mat[x][y]=1;
			int newX = x;
			int newY = y;
			switch (vec) {
			case 0:
				newX++;
				break;
			case 1:
				newY--;
				break;
			case 2:
				newX--;
				break;
			case 3:
				newY++;
				break;
			default:
				break;
			}
			mat[newX][newY]=1;
			int[] res = {newX,newY};
			return res;
		}
		else {
			int[] res = dragon(x,y,vec,gen-1);
			return dragon(res[0],res[1],vec+1,gen-1);
		}
	
	}

}
