package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

//1
//3
//0 2 0
//0 1 2
//0 1 1

public class SWEA2383_점심식사시간 {

	static int T;
	static int N;
	static int timeAns = Integer.MAX_VALUE;
	static int[][] mat;
	static int personNum;
	static Stair[] stairArr;
	static List<Person> personList = new ArrayList<>();

	static class Person {
		int x;
		int y;
		int stairDist = 0;
		int destStairIdx;
		int stairLeft = 1;
		boolean inStair = false;
		boolean finished = false;

		public Person(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Person [x=" + x + ", y=" + y + ", stairDist=" + stairDist + ", destStairIdx=" + destStairIdx
					+ ", stairLeft=" + stairLeft + ", inStair=" + inStair + ", finished=" + finished + "]";
		}

	}

	static class Stair {
		int x;
		int y;
		int stairLen;
		int personCnt = 0;

		public Stair(int x, int y, int stairLen) {
			super();
			this.x = x;
			this.y = y;
			this.stairLen = stairLen;
		}

		@Override
		public String toString() {
			return "Stair [x=" + x + ", y=" + y + ", stairLen=" + stairLen + ", personCnt=" + personCnt + "]";
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			N = Integer.parseInt(br.readLine());
			mat = new int[N][N];
			stairArr = new Stair[2];
			int stairIdx = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int val = Integer.parseInt(st.nextToken());
					mat[i][j] = val;
					if (val == 1) {
						personNum++;
						personList.add(new Person(i, j));
					}
					if (val > 1) {
						stairArr[stairIdx++] = new Stair(i, j, val);
					}
				}
			}
			int[] res = new int[personNum];

//			System.out.println("stairArr is " + Arrays.toString(stairArr));
//			System.out.println("personList is " + personList);
			subSet(res, 0);
			System.out.println("#" + t + " " + timeAns);
			timeAns = Integer.MAX_VALUE;
			personNum = 0;
			personList = new ArrayList<>();

		}

	}

	public static void subSet(int[] res, int lev) {

		if (lev == personNum) {
//			System.out.println(Arrays.toString(res));
//			System.out.println("time is " + calTime(res));
			timeAns = Math.min(calTime(res), timeAns);
			return;
		}

		else {
			res[lev] = 1;
			subSet(res, lev + 1);
			res[lev] = 0;

			subSet(res, lev + 1);
		}

	}

	public static int calTime(int[] res) {
		int time = 0;
		int finishedCnt = 0;

		for (int i = 0; i < res.length; i++) {
			Person person = personList.get(i);
			int dist = Math.abs(person.x - stairArr[res[i]].x) + Math.abs(person.y - stairArr[res[i]].y);
			person.stairDist = dist;
			person.destStairIdx = res[i];
			person.stairLeft = stairArr[res[i]].stairLen;
			person.inStair = false;
			person.finished = false;
		}
//		System.out.println(personList);

		while (true) {
			time++;
//			System.out.println(personList);

			for (int i = 0; i < personList.size(); i++) {
				Person person = personList.get(i);

				if (!person.finished) {

					if (person.inStair) {
						if (person.stairLeft > 0)
							person.stairLeft--;
						else {
							stairArr[person.destStairIdx].personCnt--;
							person.finished = true;
							finishedCnt++;
							
							
							for (int j = 0; j < i-1; j++) {
								Person newPerson = personList.get(j);
								if (newPerson.destStairIdx == person.destStairIdx && newPerson.stairDist == 0 && stairArr[newPerson.destStairIdx].personCnt < 3 && newPerson.finished==false && newPerson.inStair==false && newPerson.stairLeft>0) {
									newPerson.inStair = true;
									stairArr[newPerson.destStairIdx].personCnt++;
									newPerson.stairLeft--;
								}
							}
//							

							if (finishedCnt == personNum)
								break;
//							System.out.println("finish cnt is " + finishedCnt);
						}
					}

					else {

						if (person.stairDist > 0)
							person.stairDist--;
						else if (person.stairDist == 0 && stairArr[person.destStairIdx].personCnt < 3) {
							person.inStair = true;
							stairArr[person.destStairIdx].personCnt++;
							person.stairLeft--;
						}

					}

				}

			}
			if (finishedCnt == personNum)
				break;

		}
		return time;
	}

}
