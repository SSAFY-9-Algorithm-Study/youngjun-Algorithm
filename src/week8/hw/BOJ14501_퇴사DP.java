package week8.hw;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14501_퇴사DP {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int days[] = new int[n];
		int price[] = new int[n];
		int dp[] = new int[n+1];
		StringTokenizer st;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			days[i] = Integer.parseInt(st.nextToken());
			price[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = n-1; i >= 0; i--) {
			if(days[i]+i > n)
				dp[i] = dp[i+1];
			else {
				dp[i] = Math.max(dp[i+1], dp[i+days[i]] + price[i]);
			}
//			System.out.println(i + " is " + dp[i]);
		}
		System.out.println(Arrays.toString(dp));
		
		System.out.println(dp[0]);
		
		
		
	}

}
