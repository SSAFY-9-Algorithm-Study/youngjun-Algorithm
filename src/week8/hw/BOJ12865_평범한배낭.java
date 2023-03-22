package week8.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12865_평범한배낭 {
	
	static int N;
	static int W;
	static int[] weightList;
	static int[] priceList;
	static int[][] DP;
	static int ans;
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		weightList = new int[N+1];
		priceList = new int[N+1];
		DP = new int[N+1][W+1];
		//결국 구하려는 것은 무게가 W인 경우 가치의 최댓값, 즉 DP[N][W]
		
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			weightList[i] = Integer.parseInt(st.nextToken());//weight
			priceList[i] = Integer.parseInt(st.nextToken());//가치
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= W; j++) {
				
				int curWeight = weightList[i];
				int curPrice = priceList[i];
				
				if(curWeight>j) {  //원래부터 가방을 넣을 수 없으므로 이전까지의 값을 그대로 가져옴
					DP[i][j] = DP[i-1][j];
				}
				
				
				//가방을 넣을 수 있으므로 가방을 넣음(bags[i][1])
				// 가방을 넣었을 때 남아있는 무게를 토대로 그 무게가 가지는 가치의 최대값을 가져옴(DP[i-1][j-bags[i][0])
				else { 
					DP[i][j] = Math.max(DP[i-1][j], (curPrice + DP[i-1][j-curWeight]));
				}
					
			}
		}
		
		System.out.println(DP[N][W]);

	}
}
