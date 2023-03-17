package week3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA1213 {
	static int T = 10;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t < T + 1; t++) {

			int test = Integer.parseInt(br.readLine());
			int cnt = 0;
			char[] wordArr = br.readLine().toCharArray();
			char[] lineArr = br.readLine().toCharArray();

			for (int i = 0; i < lineArr.length - wordArr.length + 1; i++) {

				if (lineArr[i] == wordArr[0]) {
					int curLine = i;
					int curWord = 0;
					while ((curLine - i + 1) < wordArr.length) {
						if (lineArr[curLine+1] != wordArr[curWord+1])
							break;
						else {
							curLine++;
							curWord++;
						}
					}

					if (curLine - i + 1 == wordArr.length) {
						cnt++;
					}
					
				}
			}

			System.out.println("#" + test + " " + cnt);

		}
	}

}
