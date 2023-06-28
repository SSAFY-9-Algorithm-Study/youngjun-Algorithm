package week16;

class PROG_사칙연산 {

	static int[][] maxDP;
	static int[][] minDP;
	static boolean[][] visitedMax;
	static boolean[][] visitedMin;
	static String[] stringArr;
	static int len;

	public static int solution(String arr[]) {
		int answer = Integer.MIN_VALUE;
		stringArr = arr;
		len = arr.length;

		maxDP = new int[len][len];
		minDP = new int[len][len];
		visitedMax = new boolean[len][len];
		visitedMin = new boolean[len][len];

		for (int i = 0; i < len; i += 2) {
			maxDP[i][i] = Integer.parseInt(arr[i]);
			minDP[i][i] = Integer.parseInt(arr[i]);
			visitedMax[i][i] = true;
			visitedMin[i][i] = true;
		}

		for (int i = 0; i < len - 2; i += 2) {
			int val = 0;
			if (arr[i + 1].equals("-")) {
				val = Integer.parseInt(arr[i]) - Integer.parseInt(arr[i + 2]);
			} else {
				val = Integer.parseInt(arr[i]) + Integer.parseInt(arr[i + 2]);
			}
			maxDP[i][i + 2] = val;
			minDP[i][i + 2] = val;
			visitedMax[i][i + 2] = visitedMin[i][i + 2] = true;
		}

		return answer = maxDp(0, len - 1);
	}

	private static int maxDp(int startIdx, int endIdx) {
		if (visitedMax[startIdx][endIdx])
			return maxDP[startIdx][endIdx];
		int res = Integer.MIN_VALUE;

		for (int i = startIdx; i < endIdx - 1; i += 2) {
			if (stringArr[i + 1].equals("-")) {
				res = Math.max(res, maxDp(startIdx, i) - minDp(i + 2, endIdx));
			} else {
				res = Math.max(res, maxDp(startIdx, i) + maxDp(i + 2, endIdx));
			}
		}
		visitedMax[startIdx][endIdx] = true;
		maxDP[startIdx][endIdx] = res;
		return res;
	}

	private static int minDp(int startIdx, int endIdx) {
		if (visitedMin[startIdx][endIdx]) {
			return minDP[startIdx][endIdx];
		}
		int res = Integer.MAX_VALUE;
		for (int i = startIdx; i < endIdx - 1; i += 2) {
			if (stringArr[i + 1].equals("-")) {
				res = Math.min(res, minDp(startIdx, i) - maxDp(i + 2, endIdx));
			} else {
				res = Math.min(res, minDp(startIdx, i) + minDp(i + 2, endIdx));
			}
		}
		visitedMin[startIdx][endIdx] = true;
		minDP[startIdx][endIdx] = res;
		return res;
	}
}