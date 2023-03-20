package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import java.util.Scanner;

public class SWEA1231 {
	static char c[];
	static int n;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = 10;
		for(int tc=1;tc<=10;tc++) {
			n = sc.nextInt();
			c = new char[n+1];
			
			sc.nextLine();
			
			for(int i=1;i<=n;i++) {
				if(sc.hasNextInt()) {
					sc.nextInt();
					i--;
				}
				else
					c[i] = sc.next().charAt(0);
			}
//			System.out.println(Arrays.toString(c));
			
			System.out.print("#"+tc+" ");
			inorder(1);
			System.out.println();
		}
	}
	
	public static void inorder(int cnt) {
		if(cnt>n) return;
		inorder(2*cnt);
		System.out.print(c[cnt]);
		inorder(2*cnt+1);
	}

}