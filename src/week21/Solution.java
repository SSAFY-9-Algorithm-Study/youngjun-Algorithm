package week21;

class Solution {
	static int[] divisor = new int[5_000_001];
	static int[] maxDivisorIdx = new int[5_000_001];
	static int curIdx;
	static int minStart = Integer.MAX_VALUE;
	static int max;

	public int[] solution(int e, int[] starts) {
		int[] answer = new int[starts.length];
		for (int i = 2; i <= e; i++) {
			int multi = 1;
			while (true) {
				curIdx = i * multi++;
				if (curIdx > e)
					break;
				divisor[curIdx]++;
			}
		}

		for (int start : starts) {
			minStart = Math.min(minStart, start);
		}
		maxDivisorIdx[e] = e;

		for (int i = e - 1; i >= minStart; i--) {
			if (divisor[maxDivisorIdx[i + 1]] > divisor[i]) {
				maxDivisorIdx[i] = maxDivisorIdx[i + 1];
			} else {
				maxDivisorIdx[i] = i;
			}
		}

		for (int i = 0; i < starts.length; i++) {
			answer[i] = maxDivisorIdx[starts[i]];
		}

		return answer;
	}
}
