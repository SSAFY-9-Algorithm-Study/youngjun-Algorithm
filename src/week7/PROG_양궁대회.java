package week7;

import java.util.Arrays;

public class PROG_양궁대회 {

	static int ans = Integer.MIN_VALUE;
	static int[] ansArr = new int[11];

	//점수를 많이 얻기 위해서는 아예 쏘지 않거나, 어피치가 쏜 횟수 +1 만큼만 쏴야 한다.
	//그러한 모든 경우를 탐색하고, 화살이 남는 경우에는 마지막 인덱스에 몰아넣는다.
	//점수가 겹치는 경우에는 두 배열을 마지막 인덱스부터 비교하여 만약 더 작은 값이 나오면 그 배열을 답으로 바꾼다.
	public static int[] solution(int n, int[] info) {

		int[] answer = ansArr;
		dfs(n, new int[11], info, 0);
		if (ans <= 0)
			answer = new int[] { -1 };
		else
			answer = ansArr;
		return answer;

	}

	public static void dfs(int level, int[] arrowList, int[] info, int begin) {
		//모든 인덱스를 다 돌면 종료
		if (begin == 10) {
			//남아있는 화살을 마지막 인덱스에 넣기
			arrowList[10]+=level;
			int val = calculate(arrowList, info);
			
			//계산값이 더 크다면 답 배열 교
			if (val > ans) {
				ans = val;
				ansArr = arrowList.clone();
			}
			//계산값이 같다면 마지막 인덱스부터 비교 후 교체
			else if(val == ans) {
				for (int i =10; i >= 0; i--) {
					if(ansArr[i] < arrowList[i]) {
						ansArr = arrowList.clone();
						break;
					}
					else if(ansArr[i] > arrowList[i]) 
						break;
				}
			}
			//마지막 인덱스에 넣었던 화살을 다시 빼줘야 다른 dfs에 영향을 안줌
			arrowList[10]-=level;
			return;
		}

		else {
			//1개 차이로 화살을 더 쏠 수 있으면 쏘기
			if (info[begin] < level && begin!=10) {
				arrowList[begin] += (info[begin] + 1);
				dfs(level - (info[begin] + 1), arrowList, info, begin + 1);
				arrowList[begin] -= (info[begin] + 1);
			}
			
			//쏘지 않고 다음으로 넘어가기
			dfs(level, arrowList, info, begin + 1);
		}
	}

	public static int calculate(int[] arrowList, int[] info) {
		int apeachScore = 0;
		int ryanScore = 0;
		for (int i = 0; i < 11; i++) {
			if (arrowList[i] > info[i]) {
				ryanScore += (10 - i);
			} else if (arrowList[i] <= info[i] && info[i] != 0) {
				apeachScore += (10 - i);
			}
		}
		return ryanScore - apeachScore;
	}

}
