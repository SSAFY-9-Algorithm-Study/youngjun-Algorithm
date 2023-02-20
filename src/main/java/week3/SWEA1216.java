package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class SWEA1216 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = 10;
		int w = 100;
		char[][] mat = new char[w][w];
		for (int t = 1; t < T + 1; t++) {
			String test = br.readLine();
			int max = 1;

			for (int i = 0; i < w; i++) {
				String string = br.readLine();
				for (int j = 0; j < w; j++) {
					mat[i][j] = string.charAt(j);
				}
			}

			for (int i = 0; i < w; i++) {
				for (int s = 0; s < w; s++) {
					int curS = s;
//					System.out.println("s is" + s);
					
					int e = w - 1;
					
					if(e-s+1<max)
						break;
					
					
					while (curS < e) {
						if (mat[i][s] == mat[i][e]) {
//							System.out.println("s is" + s);
//							System.out.println("e is " + e);
							int curE = e;
							while (curS < curE) {
								if (mat[i][curS+1] != mat[i][curE-1]) {
									break;
								}
								curS++;
								curE--;
							}
							if (curS >= curE) {
								System.out.println("palindome");
								System.out.println("i is" + i);
								System.out.println("s is" + s);
								System.out.println("e is " + e);
								if ((e - s + 1) > max) {
									max = e - s+1;
									System.out.println("max is" + max);
									break;
								}

							}
						}
						e--;
					}

				}
			}
			
			
			for (int i = 0; i < w; i++) {
				for (int s = 0; s < w; s++) {
					int curS = s;
//					System.out.println("s is" + s);
					
					int e = w - 1;
					
					if(e-s+1<max)
						break;
					
					
					while (curS < e) {
						if (mat[s][i] == mat[e][i]) {
							int curE = e;
							while (curS < curE) {
								if (mat[curS+1][i] != mat[curE-1][i]) {
									break;
								}
								curS++;
								curE--;

							}
							if (curS >= curE) {
								if ((e - s + 1) > max) {
									max = e - s+1;
									break;
								}

							}
						}
						e--;
					}

				}
			}
			System.out.println(max);

		}

	}

}
