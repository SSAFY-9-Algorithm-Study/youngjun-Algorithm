package week12;

import java.io.*;
import java.util.*;

//5 5
//00000
//01110
//01110
//01111
//00000


//5 5
//00000
//00100
//00100
//00000
//00000

public class BOJ1915_가장큰정사각형 {

	static int H;
	static int W;
	static int[][] mat;
	static int maxSize;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		mat = new int[H][W];

		for (int i = 0; i < H; i++) {
			String str = br.readLine();
			for (int j = 0; j < W; j++) {
				mat[i][j] = str.charAt(j) - '0';
			}
		}

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (mat[i][j] == 1) {
					checkSquare(i, j);
				}
			}
		}
		System.out.println(maxSize * maxSize);

	}

	private static void checkSquare(int i, int j) {
		
		if(H-i<=maxSize || W-j<=maxSize) return;
		
		int width = 1;
		int height = 1;
		int curH = i;
		int curW = j;
		while (curH < H-1 && mat[curH+1][j] == 1) {
			width++;
			curH++;
		}
		while (curW < W-1 && mat[i][curW+1] == 1) {
			height++;
			curW++;
		}

		int size = Math.min(width, height);
		if(size<maxSize) return;

		for (int k = size; k > maxSize; k--) {
//			System.out.println("size is " + size);
			boolean possible = true;
			for (int l = i; l < i + k; l++) {
				boolean isSquare = true;
				for (int l2 = j; l2 < j + k; l2++) {
//					System.out.println("l is "+ l + "l2 is " + l2);
					if (mat[l][l2] == 0) {
						isSquare = false;
						break;
					}
				}
				if (!isSquare) {
					possible = false;
					break;
				}
			}
			if(possible) {
//				System.out.println("i is " + i + " j is " + j + " k is " + k);
				maxSize = k;
				break;
			}
		}

	}

}
