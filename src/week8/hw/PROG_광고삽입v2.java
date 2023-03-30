package week8.hw;

import java.util.Arrays;

public class PROG_광고삽입v2 {

	public String solution(String play_time, String adv_time, String[] logs) {
		String answer = "";

		int[] timeArr = new int[timeConverter(play_time) + 1];
		int start = 0;
		int end = 0;
		long initSum = 0;
		long maxSum = 0;
		int timeAns = 0;
		int advTimeLen = timeConverter(adv_time);

		for (int i = 0; i < logs.length; i++) {
			start = timeConverter(logs[i].substring(0, 8));
			end = timeConverter(logs[i].substring(9));
			for (int j = start; j < end; j++) {
				timeArr[j]++;
			}
		}

		for (int i = 0; i < timeConverter(adv_time); i++) {
			initSum += timeArr[i];
		}
		maxSum = initSum;
		for (int i = 1; i <= timeArr.length - advTimeLen - 1; i++) {
			initSum = initSum - timeArr[i - 1] + timeArr[i + advTimeLen - 1];
			if (initSum > maxSum) {
				maxSum = initSum;
				timeAns = i;
			}
		}

		answer = timeToStr(timeAns);
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
}
