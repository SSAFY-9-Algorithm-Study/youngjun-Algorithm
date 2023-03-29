package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ16938_캠프준비 {

	static int N;
	static int L;
	static int R;
	static int X;
	static int[] problems;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		problems = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			problems[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(problems);
		subset(0, new int[N]);
		System.out.println(ans);

	}

	private static void subset(int lev, int[] res) {
		if (lev == N) {
			int ones = 0;
			for (int i = 0; i < res.length; i++) {
				if (res[i] == 1)
					ones++;
			}
			if (ones > 1)
				if (checkPossible(res))
					ans++;
		} else {
			res[lev] = 1;
			subset(lev + 1, res);
			res[lev] = 0;
			subset(lev + 1, res);
		}
	}

	private static boolean checkPossible(int[] res) {
		int sum = 0;
		int min = 0;
		int max = 0;
		boolean isMinChecked = false;

		for (int i = 0; i < N; i++) {
			if (res[i] == 1) {
				sum += problems[i];
				if (!isMinChecked) {
					min = problems[i];
					isMinChecked = true;
				}
				max = problems[i];
			}

		}
		if (sum < L || sum > R)
			return false;

		if (max - min < X)
			return false;

		return true;
	}

}
