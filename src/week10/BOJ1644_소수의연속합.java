package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ1644_소수의연속합 {

	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		List<Integer> primeList = new ArrayList<>();

		for (int i = 2; i <= N; i++) {
			if (isPrime(i))
				primeList.add(i);
		}

		int cnt = 0;
		if (N != 1) {
			int idx1 = 0;
			int idx2 = 0;
			long sum = primeList.get(0);
			while (idx1 <= idx2) {
				if (sum <= N) {
					idx2++;
					if (sum == N)
						cnt++;
					if (idx2 == primeList.size())
						break;
					sum += primeList.get(idx2);
				} else {
					sum -= primeList.get(idx1);
					idx1++;
				}
			}
		}
		System.out.println(cnt);
	}

	private static boolean isPrime(int i) {
		if (i == 1)
			return false;
		Double iSqrt = Math.sqrt(i);
		for (int j = 2; j <= iSqrt; j++) {
			if (i % j == 0)
				return false;
		}
		return true;
	}

}
