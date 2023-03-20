package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_숫자만들기 {
	static int cnt;
	static int startNum;
	static int[] numArr;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {

			int n = Integer.parseInt(br.readLine());
			int opArr[] = new int[4];
			numArr = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				opArr[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				numArr[i] = Integer.parseInt(st.nextToken());
			}
			startNum = numArr[0];

			int[] b = opArr.clone();

			dfs(n - 1, 0, b, new int[n-1]);
			System.out.println("#" + t + " " + (max-min));
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;

		}

	}

	public static void dfs(int L, int level, int[] b, int[] res) {

		if (level == L) {
			for (int i = 0; i < L; i++) {
				startNum = operate(startNum,res[i],numArr[i+1]);
			}
			if(max<startNum) {
				max = startNum;
			}
			if(min>startNum) {
				min = startNum;
			}
			startNum = numArr[0];
			return;
		}

		else {
			for (int i = 0; i < b.length; i++) {
				if (b[i] > 0) {
					b[i] -= 1;
					res[level] = i;
					dfs(L, level + 1, b, res);
					b[i] += 1;
				}
			}
		}
	}
	
	public static int operate(int startNum, int op, int num) {
		switch(op) {
		case 0:
			num =  startNum + num;
			break;
		case 1:
			num = startNum - num;
			break;
		case 2:
			num = startNum * num;
			break;
		case 3:
			num = startNum / num;
			break;
		
		}
		return num;
	}

}
