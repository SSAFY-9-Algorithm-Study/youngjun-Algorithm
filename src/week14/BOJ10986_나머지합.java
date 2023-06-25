package week14;

import java.io.*;
import java.util.*;

public class BOJ10986_나머지합 {

	static int N, M;
	static long sum;
	static long ans;
	static int[] mod;
	static int[] modCnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		mod = new int[(int) (N + 1)];
		modCnt = new int[(int) M];
		
		//0으로 카운트를 초기화해준다
		Arrays.fill(modCnt, 0);
		
		
		st = new StringTokenizer(br.readLine());
		// 누적합 배열을 채워주고, 바로 나눠떨어지는 경우에는 답에 ++을 해준다.
		for (int i = 0; i < N; i++) {
			int val = Integer.parseInt(st.nextToken());
			sum += val;
			int sumMod = (int) (sum % M);
			if (sumMod == 0)
				ans++;

			mod[i] = sumMod;
			modCnt[sumMod] += 1;
		}
		
		// 카운트 배열의 각 값에 따라서 2개를 뽑는 조합의 경우의 수를 답에 더해준다.
		for (int i = 0; i < M; i++) {
			long curModCnt = modCnt[i];
			if (curModCnt >= 2) {
				ans += (curModCnt * (curModCnt - 1) / 2);
			}
		}

		System.out.println(ans);

	}
}
