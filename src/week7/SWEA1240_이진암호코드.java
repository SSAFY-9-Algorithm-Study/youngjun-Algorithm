package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class SWEA1240_이진암호코드 {

	static int T;
	static int H;
	static int W;
	static List<Integer> code = new ArrayList<Integer>(); //0,1의 반복된 횟수를 저장
	static List<Integer> realCode = new ArrayList<>(); //반복된 횟수를 토대로 실제 번호 8자리 저장
	static boolean possible = true; //계산 전에 유효한 코드인지를 저장

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int i = 1; i < T + 1; i++) {

			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			char cur = '0';
			int sum = 1;

			for (int j = 0; j < H; j++) {
				String str = br.readLine();
				for (int j2 = 0; j2 < W; j2++) {
					if (str.charAt(j2) != cur) {
						cur = str.charAt(j2);
						code.add(sum);
						sum = 1;
						continue;
					}
					sum++;
				}
			}

			for (int j = 1; j <= 3; j++) {
				int firstFourCode = check(j, code.get(1), code.get(2), code.get(3));
				if (firstFourCode > -1) {
					realCode.add(firstFourCode);
					break;
				}
			}

			int idx = 4;
			for (int j = idx; j < idx + 4; j += 4) {
				int check = check(code.get(j), code.get(j + 1), code.get(j + 2), code.get(j + 3));
				if (check == -1) {
					possible = false;
					break;
				}
				realCode.add(check);
				idx += 4;
				if (realCode.size() == 8)
					break;
			}


			if (possible) {
				System.out.println("#" + i + " " + calculate(realCode));
			} else {
				System.out.println("#" + i + " " + 0);
			}

			code = new ArrayList<Integer>();
			realCode = new ArrayList<>();
			possible = true;
		}
	}

	public static int check(int i1, int i2, int i3, int i4) {
		if (i1 == 3 && i2 == 2 && i3 == 1 && i4 == 1)
			return 0;
		if (i1 == 2 && i2 == 2 && i3 == 2 && i4 == 1)
			return 1;
		if (i1 == 2 && i2 == 1 && i3 == 2 && i4 == 2)
			return 2;
		if (i1 == 1 && i2 == 4 && i3 == 1 && i4 == 1)
			return 3;
		if (i1 == 1 && i2 == 1 && i3 == 3 && i4 == 2)
			return 4;
		if (i1 == 1 && i2 == 2 && i3 == 3 && i4 == 1)
			return 5;
		if (i1 == 1 && i2 == 1 && i3 == 1 && i4 == 4)
			return 6;
		if (i1 == 1 && i2 == 3 && i3 == 1 && i4 == 2)
			return 7;
		if (i1 == 1 && i2 == 2 && i3 == 1 && i4 == 3)
			return 8;
		if (i1 == 3 && i2 == 1 && i3 == 1 && i4 == 2)
			return 9;
		return -1;
	}

	public static int calculate(List<Integer> realCode) {
		int sum = 0;
		int oddSum = 0;
		int evenSum = 0;
		for (int i = 0; i < 8; i++) {
			int val = realCode.get(i);
			if (i % 2 == 0) {
				evenSum += val;
				sum += val;
			} else {
				oddSum += val;
				sum += val;
			}

		}
		if ((evenSum * 3 + oddSum) % 10 == 0)
			return sum;
		return 0;

	}

}
