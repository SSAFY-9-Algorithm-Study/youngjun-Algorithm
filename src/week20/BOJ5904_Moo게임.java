package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5904_Moo게임 {
	static int N;
	static long[] lenArr = new long[100000];
	static int[] idxArr = new int[100000];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
//		System.out.println(getIdx(N));
		int i = 0;
		while(N<getLen(i)) i++;
//		getIdx(i);

	}

//	private static char getIdx(int i) {
//		
//		
//		
//	}

	private static long getLen(int n) {
		if (n == 0)
			return 3;
		if (lenArr[n] > 0)
			return lenArr[n];
		else {
			return lenArr[n] = getLen(n - 1) * 2 + n + 3;
		}
	}

}
