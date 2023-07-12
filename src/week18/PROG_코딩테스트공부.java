package week18;
import java.util.*;

class PROG_코딩테스트공부 {
	static int N, curAlp, curCop, maxAlp, maxCop;
	static int ans = Integer.MAX_VALUE;
	static int[][] problemArr;
	static int size = 4000;
	static int DP[][] = new int[size][size];

	public static int solution(int alp, int cop, int[][] problems) {

		curAlp = alp;
		curCop = cop;
		N = problems.length;
		problemArr = new int[N][5];
		for (int i = 0; i < N; i++) {
			maxAlp = Math.max(maxAlp, problems[i][0]);
			maxCop = Math.max(maxCop, problems[i][1]);
			problemArr[i] = problems[i].clone();
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				DP[i][j] = 301;
			}
		}
		dfs(curAlp, curCop, new boolean[N], 0);

		return ans;
	}

	private static void dfs(int alp, int cop, boolean[] visited, int time) {
		if (DP[alp][cop] <= time)
			return;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (visited[i] == true)
				cnt++;
		}
		if (cnt == N) {
			ans = Math.min(ans, time);
			return;
		}

		DP[alp][cop] = time;
		if (alp < maxAlp)
			dfs(alp + 1, cop, checkVisited(alp + 1, cop, visited), time + 1);
		if (cop < maxCop)
			dfs(alp, cop + 1, checkVisited(alp, cop + 1, visited), time + 1);
		for (int i = 0; i < N; i++) {
			if (visited[i] && (alp < maxAlp || cop < maxCop)) {
				dfs(alp + problemArr[i][2], cop + problemArr[i][3],
						checkVisited(alp + problemArr[i][2], cop + problemArr[i][3], visited), time + problemArr[i][4]);
			}
		}

	}

	private static boolean[] checkVisited(int alp, int cop, boolean[] visited) {
		boolean[] res = visited.clone();
		for (int i = 0; i < N; i++) {
			if (!res[i] && alp >= problemArr[i][0] && cop >= problemArr[i][1])
				res[i] = true;
		}
		return res;
	}

}
