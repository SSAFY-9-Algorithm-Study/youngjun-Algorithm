package week8.hw;


import java.util.Arrays;
import java.util.Collection;

public class PROG_광고삽입 {
	
	static int[] startTime;
	static Time[] edgeTime;
	static int ans;
	static int startAns;
	static int ansIdx;

	static class Time implements Comparable<Time> {
		int start;
		int end;

		public Time(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Time o) {
			return this.start - o.start;
		}

		@Override
		public String toString() {
			return "Time [start=" + start + ", end=" + end + "]";
		}

	}
	
	public String solution(String play_time, String adv_time, String[] logs) {
		String answer = "";
		startTime = new int[logs.length * 2 + 1];
		edgeTime = new Time[logs.length];

		startTime[0] = 0;


		for (int i = 0; i < logs.length; i++) {
			edgeTime[i] = new Time(timeConverter(logs[i].substring(0, 8)), timeConverter(logs[i].substring(9)));
			startTime[2 * i + 1] = timeConverter(logs[i].substring(0, 8));
			int val = timeConverter(logs[i].substring(9)) - timeConverter(adv_time);
			if (val<0) val = 0;
			startTime[2 * i + 2] = val;
		}

		Arrays.sort(startTime);
		Arrays.sort(edgeTime);
		Arrays.sort(logs);

		for (int i = 0; i < startTime.length; i++) {
			int dupTime = 0;
			for (int j = 0; j < logs.length; j++) {
				int start = startTime[i];
				int end = startTime[i] + timeConverter(adv_time);
				int start2 = edgeTime[j].start;
				int end2 = edgeTime[j].end;
				
				if(end<=start2 || end2<=start)
					continue;

				dupTime += dupTimeCal(start, end, start2, end2);

			}
			if (dupTime > ans) {
				ans = dupTime;
				startAns = startTime[i];
				ansIdx = i;
			}
		}

		if (ansIdx == 0)
			answer = "00:00:00";
		else {
			answer = timeToStr(startAns);
		}

		return answer;
	}



	public static int timeConverter(String str) {
		int hour = Integer.parseInt(str.substring(0, 2)) * 3600;
		int min = Integer.parseInt(str.substring(3, 5)) * 60;
		int sec = Integer.parseInt(str.substring(6, 8));
		return hour + min + sec;
	}

	public static String timeToStr(int time) {
		int hour = time / 3600;
		int min = (time - hour * 3600) / 60;
		int sec = time - hour * 3600 - min * 60;
		String hourString = Integer.toString(hour);
		String minString = Integer.toString(min);
		String secString = Integer.toString(sec);
		if (Integer.toString(hour).length() == 1)
			hourString = "0" + hourString;
		if (Integer.toString(min).length() == 1)
			minString = "0" + minString;
		if (Integer.toString(sec).length() == 1)
			secString = "0" + secString;

		return hourString + ":" + minString + ":" + secString;

	}

	public static int dupTimeCal(int start, int end, int start2, int end2) {

		int maxStart = Math.max(start, start2);
		int minEnd = Math.min(end, end2);
		if (maxStart > minEnd)
			return 0;
		else
			return minEnd - maxStart;

	}
}

