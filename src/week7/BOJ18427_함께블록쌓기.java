package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ18427_함께블록쌓기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 학생수
		int M = Integer.parseInt(st.nextToken()); // 최대블록수
		int H = Integer.parseInt(st.nextToken()); // 높이
		List<int[]> blockList = new ArrayList<int[]>(); // 블록들 담는 리스트
		int DP[][] = new int[N + 1][H + 1];

		blockList.add(null);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int[] block = new int[M];
			int idx = 0;
			
			while (st.hasMoreTokens()) {
				block[idx++] = Integer.parseInt(st.nextToken());
			}
			blockList.add(block);
		}
		
		for (int i = 0; i < N+1; i++) {
					DP[i][0]=1;
			
		}
		
		for (int i = 1; i < N + 1; i++) {
			int[] arr = blockList.get(i);
			for (int j = 1; j < H + 1; j++) {
						
				DP[i][j] = DP[i-1][j];

				for (int j2 = 0; j2 < M; j2++) {
					int curHeight = arr[j2];
					if (curHeight == 0) // 0이란것은 블록이 더이상 없다는 뜻
						continue;
					else if (curHeight <= j) {
						DP[i][j] += DP[i - 1][j - curHeight];
						DP[i][j]%=10007;
					}
				}
				
				DP[i][j]%=10007;
				
			}
		}

		System.out.println(DP[N][H]);
	}
}