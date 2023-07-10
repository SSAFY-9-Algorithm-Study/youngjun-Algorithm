package week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2457_공주님의정원 {
	static int N;
	static boolean impossib;
	static int cnt;
	static Queue<Date> pq = new PriorityQueue<>();
	static Date curDate = new Date(3, 1, 0, 0);

	static class Date implements Comparable<Date> {
		int startM;
		int startD;
		int endM;
		int endD;

		public Date(int startM, int startD, int endM, int endD) {
			super();
			this.startM = startM;
			this.startD = startD;
			this.endM = endM;
			this.endD = endD;
		}

		@Override
		public int compareTo(Date o) {
			if (this.startM == o.startM)
				return this.startD - o.startD;
			return this.startM - o.startM;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Date date = new Date(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			pq.add(date);
		}

		while (!pq.isEmpty()) {
			Date compDate = null;
			while (!pq.isEmpty() && pq.peek().compareTo(curDate) <= 0) {
				Date date = pq.poll();
				if (compDate == null || compDate.endM < date.endM
						|| (compDate.endM == date.endM && compDate.endD <= date.endD)) {
					compDate = date;
				}
			}

			// 중간에 끊기면 바로 break
			if (compDate == null) {
				impossib = true;
				break;
			}

			int curM = compDate.endM;
			int curD = compDate.endD;
			cnt++;
			
			// 그리디로 다음 비교할 대상 저장
			// 비교를 해야 하므로 endDate 이지만 startDate 위치에 저장해줌
			curDate = new Date(curM, curD, 0, 0);
			
			// 이미 조건을 충족했다면 break
			if (curM > 11)
				break;

		}
		// 마지막 날짜가 12월로 끝나지 않으면 안되는 것으로 처리
		if (curDate.startM < 12)
			impossib = true;
		cnt = impossib ? 0 : cnt;
		System.out.println(cnt);
	}

}
