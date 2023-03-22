package week7;

import java.util.Arrays;

public class PROG_양궁대회 {
	
	static int ans = Integer.MIN_VALUE;
	static int[] ansArr = new int[11];
	

	public static void main(String[] args) {
//		System.out.println(Arrays.toString(solution(1, new int[] {1,0,0,0,0,0,0,0,0,0,0 })));
//		System.out.println(Arrays.toString(solution(5, new int[] {2,1,1,1,0,0,0,0,0,0,0 })));
		System.out.println(Arrays.toString(solution(10, new int[] {0,0,0,0,0,0,0,0,3,4,3 })));
	}

	public static int[] solution(int n, int[] info) {

		int[] answer = ansArr;
		
		dfs(n,new int[11], info);
//		System.out.println(ans);
		if(ans<=0)
			answer = new int[] {-1};
		
		else answer = ansArr;
		return answer;
		

	}

	public static void dfs(int level, int[] arrowList, int[] info) {
		if (level == 0) {
//			System.out.println(Arrays.toString(arrowList));
			int val = calculate(arrowList,info);
			if(val >ans) {
				ans = val;
				ansArr = arrowList.clone();
			}
			else if(val==ans) {
				ans = val;
				for (int i = 10; i >= 0; i--) {
					if(ansArr[i]>arrowList[i]) 
						break;
					else if(ansArr[i]<arrowList[i]) {
						ansArr = arrowList.clone();
						break;
					}
				}
				
			}
		}

		else {
			for (int i = 0; i < 11; i++) {
				if(arrowList[i]<=info[i] && level<) {
					arrowList[i]++;
					dfs(level - 1, arrowList, info);
					arrowList[i]--;
				}
				
				
			}
		}
	}
	
	public static int calculate(int[] arrowList, int[] info) {
		int apeachScore = 0;
		int ryanScore = 0;
		for (int i = 0; i < 11; i++) {
			if(arrowList[i]>info[i]) {
				ryanScore+=(10-i);
			}
			else if(arrowList[i]<=info[i] && info[i]!=0) {
				apeachScore+=(10-i);
			}
		}
		return ryanScore - apeachScore;
	}

}
