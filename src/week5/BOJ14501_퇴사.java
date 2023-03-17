package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14501_퇴사 {
	
	static int days;
	static int[][] lenAndCost;
	static int max = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		days = Integer.parseInt(br.readLine());
		lenAndCost = new int[days+1][2];
		
		for (int i = 1; i < days+1; i++) {
			st = new StringTokenizer(br.readLine());
			lenAndCost[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		}
		
		dfs(1,0);
		System.out.println(max);
		
	}
	
	public static void dfs(int curDay, int curMoney) {
		if(curDay>days) {
			if(curMoney>max)
				max = curMoney;
		}
		else {
			if(lenAndCost[curDay][0] + curDay <= (days+1)) {
				dfs(curDay+1,curMoney);
				dfs(curDay+lenAndCost[curDay][0], curMoney+lenAndCost[curDay][1]);
			}
			else {
				dfs(curDay+1,curMoney);
			}
		}	
	}
}
