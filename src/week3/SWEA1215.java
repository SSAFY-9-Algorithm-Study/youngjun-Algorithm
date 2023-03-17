package week3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class SWEA1215 {
	
	static int T = 10;
	static int w = 8;
	static int cnt = 0;
	static int len;
	static char[][] mat;
	static char[][] imat;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		mat = new char[w][w];
		imat = new char[w][w];
		for (int t = 1; t < T + 1; t++) {
			len = Integer.parseInt( br.readLine());
			cnt = 0;

			for (int i = 0; i < w; i++) {
				String string = br.readLine();
				for (int j = 0; j < w; j++) {
					mat[i][j] = string.charAt(j);
					imat[j][i] = string.charAt(j);
				}
			}
			
			palindrome(mat);
			palindrome(imat);
			System.out.println("#" + t + " " + cnt);

		}

	}
	
	public static void palindrome(char[][] mat2) {
		for (int i = 0; i < w; i++) {
			for (int s = 0; s < w; s++) {
				int e = w - 1;
				
				
				while (s < e) {
					if (mat2[i][s] == mat2[i][e]) {
						int curE = e;
						int curS = s;
						while (curS < curE) {
							if (mat2[i][curS] != mat2[i][curE]) {
								break;
							}
							else {
								curS++;
								curE--;
							}
							
						}

						if (curS >= curE) {
							if((e-s+1)==len) {
								cnt++;
							}
								
						}
					}
					e--;
				}

			}
		}
	}

}
