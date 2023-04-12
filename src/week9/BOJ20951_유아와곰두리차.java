package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ20951_유아와곰두리차 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int sum = 0;
		List<Integer>[] arr = new List[N + 1];
		for (int i = 1; i < N + 1; i++) {
			arr[i] = new ArrayList<>();
		}
		long[][] dp = new long[8][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int linkStart = Integer.parseInt(st.nextToken());
			int linkEnd = Integer.parseInt(st.nextToken());
			arr[linkStart].add(linkEnd);
			arr[linkEnd].add(linkStart);
		}

		// 첫 행 초기화
		for (int i = 1; i < N + 1; i++) {
			dp[1][i] = arr[i].size();
		}

		for (int i = 2; i < 8; i++) {
			for (int j = 1; j < N + 1; j++) {
				for (int j2 = 0; j2 < arr[j].size(); j2++) {
					int linkVal = arr[j].get(j2);
					dp[i][j]+= (dp[i-1][linkVal]%=(Math.pow(10, 9) + 7));
				}
				dp[i][j]%=(Math.pow(10, 9) + 7);
			}
			
		}
		for (int i = 1; i < 8 ; i++) {
			System.out.println();
			for (int j = 1; j < N+1; j++) {
				System.out.print(dp[i][j] + " ");
			}
		}
		
		for (int i = 1; i < N+1; i++) {
			sum+=(dp[7][i]%=(Math.pow(10, 9) + 7));
		}
		System.out.println(sum);
	}

}
