package week3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//3P2
public class Permutation {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] list = new int[n];
		for (int i = 1; i < n+1; i++) {
			list[i-1] = i;
		}
		int r = Integer.parseInt(br.readLine());
		int[] res = new int[r];
		int[] checkList= new int[n];
		DFS(0,n,r,list,checkList,res);
		
		
	}
	
	public static void DFS(int L, int n, int r, int[] list, int[] checkList, int[] res) {
		if(L == r) { //깊이가 r값과 같아지면 종료
			System.out.println(Arrays.toString(res));
		}
		else {
			for (int i = 0; i < n; i++) {
				if(checkList[i]==0) {
					checkList[i] = 1;
					res[L] = list[i];
					DFS(L+1,n,r,list,checkList,res);
					checkList[i] = 0;
				}
			}
			
		}
	}

}
