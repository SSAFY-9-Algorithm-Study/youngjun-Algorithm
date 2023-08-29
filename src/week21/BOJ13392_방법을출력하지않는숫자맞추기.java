package week21;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ13392_방법을출력하지않는숫자맞추기 {

	static int T;
	static String N, M;
	static int ans = Integer.MAX_VALUE;

	static class Screw {

		String cur;
		int step; // 자릿수
		boolean isLeft;
		int turns; // 회전수

		@Override
		public String toString() {
			return "Screw [cur=" + cur + ", step=" + step + ", isLeft=" + isLeft + ", turns=" + turns + "]";
		}

	}

	static Queue<Screw> que = new LinkedList<>();
	static int[] stepLeft;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		N = br.readLine();
		M = br.readLine();
		stepLeft = new int[T];
		Arrays.fill(stepLeft, Integer.MAX_VALUE);
		if (N.equals(M))
			System.out.println(0);
		else {
			turn();
			System.out.println(ans);
		}

	}

	private static void turn() {
		// N, 0, 0

		Screw screw = new Screw();
		screw.cur = N;
//		screw.step = 0;
//		screw.turns = 0;
//		que.add(screw);
		que.add(leftScrew(screw));
		que.add(rightScrew(screw));

		int minTurn = Integer.MAX_VALUE;
		int curStep = 1;
		Screw minLeftScrew = null;
		while (!que.isEmpty()) {

			Screw curScrew = que.poll();
			
//			if (curScrew.cur.equals("447")) {
//				System.out.println(curScrew);
//				break;
//			}

//			System.out.println(curScrew);
			if(curScrew == null)
				continue;

			if (curScrew.cur.equals(M)) {
				ans = Math.min(curScrew.turns, ans);
//				continue;
			}
			
			if(curScrew.step == T) continue;

			que.add(rightScrew(curScrew));
			
			if(curScrew.step==T-2) {
				que.add(leftScrew(curScrew));
				continue;
			}

			if (curScrew.step > curStep) {
//				System.out.println("adding minleft");
				que.add(minLeftScrew);
				curStep = curScrew.step;
				minLeftScrew = null;
			}
			
			Screw leftScrew = leftScrew(curScrew);
			if (minLeftScrew == null) {
				minLeftScrew = leftScrew;
				continue;
			}
			if (minLeftScrew.turns > curScrew.turns) {
				minLeftScrew = leftScrew;
			}

		}

	}

	private static Screw leftScrew(Screw curScrew) {
		int stepLeft = M.charAt(curScrew.step) - curScrew.cur.charAt(curScrew.step);
		if (stepLeft < 0)
			stepLeft = 10 + stepLeft;
		Screw res = new Screw();
		StringBuilder sb = new StringBuilder(curScrew.cur);
		for (int i = curScrew.step; i < T; i++) {
			int newNum = (sb.charAt(i) - '0' + stepLeft) % 10;
			sb.setCharAt(i, Integer.toString(newNum).charAt(0));
		}
		res.cur = sb.toString();
		res.isLeft = true;
		res.step = curScrew.step + 1;
		res.turns = curScrew.turns + stepLeft;
		return res;
	}

	private static Screw rightScrew(Screw curScrew) {
		int stepRight = curScrew.cur.charAt(curScrew.step) - M.charAt(curScrew.step);
		if (stepRight < 0)
			stepRight = 10 + stepRight;
		StringBuilder sb = new StringBuilder(curScrew.cur);
		Screw res = new Screw();
		sb.setCharAt(curScrew.step, M.charAt(curScrew.step));
		res.cur = sb.toString();
		res.isLeft = false;
		res.step = curScrew.step + 1;
		res.turns = curScrew.turns + stepRight;
		return res;
	}

}
