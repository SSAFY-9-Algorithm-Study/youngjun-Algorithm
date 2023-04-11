package week8.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ19237_어른상어 {

	static int N;
	static int M;
	static int K;
	static MatInfo[][] mat;
	static Shark[] sharkArr;
	static int ans = -1;

	static int[] dx = { 0, -1, 1, 0, 0 }; // 0번쨰는 쓰지않음
	static int[] dy = { 0, 0, 0, -1, 1 };

	public static class MatInfo {
		int x;
		int y;
		int smell;
		int visitedShark;
		List<Shark> curSharks = new ArrayList<>();

		public MatInfo(int x, int y, int smell, int visitedShark) {
			super();
			this.x = x;
			this.y = y;
			this.smell = smell;
			this.visitedShark = visitedShark;
		}
	}

	public static class Shark implements Comparable<Shark> {
		int num;
		int x;
		int y;
		int dir;
		int[][] priority = new int[5][5];

		@Override
		public String toString() {
			return "Shark [x=" + x + ", y=" + y + ", dir=" + dir + ", priority=" + Arrays.toString(priority) + "]";
		}

		@Override
		public int compareTo(Shark o) {
			return this.num - o.num;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		mat = new MatInfo[N][N];
		sharkArr = new Shark[M + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int val = Integer.parseInt(st.nextToken());
				if (val > 0) {
					Shark shark = new Shark();
					shark.x = i;
					shark.y = j;
					shark.num = val;
					sharkArr[val] = shark;
					mat[i][j] = new MatInfo(i, j, K, val);
					mat[i][j].curSharks.add(shark);

				} else
					mat[i][j] = new MatInfo(i, j, 0, 0);
			}

		}

		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= M; i++) {
			sharkArr[i].dir = Integer.parseInt(st.nextToken());
		}

		for (

				int i = 1; i <= M; i++) {
			for (int j = 1; j <= 4; j++) {
				st = new StringTokenizer(br.readLine());

				sharkArr[i].priority[j] = new int[] { 0, Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()) };

			}
		}

		System.out.println(Arrays.toString(sharkArr));

		System.out.println();
		for (int j = 0; j < N; j++) {
			System.out.println();
			for (int j2 = 0; j2 < N; j2++) {
				if (mat[j][j2].curSharks.size() > 0)
					System.out.print(mat[j][j2].curSharks.get(0).num);
				else
					System.out.print(0);
			}

		}

		for (int i = 0; i < 3; i++) {
			if (sharkArr[1] == null) {
				ans = i;
				break;
			}

			move();

			System.out.println();
			for (int j = 0; j < N; j++) {
				System.out.println();
				for (int j2 = 0; j2 < N; j2++) {
					if (mat[j][j2].curSharks.size() > 0)
						System.out.print(mat[j][j2].curSharks.get(0).num);
					else
						System.out.print(0);
				}

			}
		}
		System.out.println(ans);

	}

	private static void move() {
		MatInfo[][] temp = new MatInfo[N][N];
		
		for (int i = 1; i <= M; i++) {
			
			
			
			temp = new MatInfo[N][N];
			temp = mat;
			System.out.println(i);
			System.out.println("shark is " + sharkArr[i].num + " " + sharkArr[i]);
			System.out.println("shark" + i + " priority " + Arrays.toString(sharkArr[i].priority[sharkArr[i].dir]));
			if (sharkArr[i] != null) {
				Shark shark = sharkArr[i];
				int[] priority = shark.priority[shark.dir];
				int x = shark.x;
				int y = shark.y;
				int newx= 0;
				int newy = 0;
				int priorityIdx = 0;
				boolean moved = false;
				for (int j = 1; j <= 4; j++) {
					newx = x + dx[priority[j]];
					newy = y + dy[priority[j]];
					
					System.out.println("smell mat");
					for (int k = 0; k < N; k++) {
						System.out.println();
						for (int j3 = 0; j3 < N; j3++) {
							System.out.print(mat[k][j].smell);
						}
					}

					if (newx < 0 || newx > N - 1 || newy < 0 || newy > N - 1)
						continue;
					if (mat[newx][newy].smell == 0) {
						System.out.println("smell 0");
						priorityIdx = j;
						moved = true;
						break;

					}
				}
				if (!moved) {
					for (int j = 1; j <= 4; j++) {
						newx = x + dx[priority[j]];
						newy = y + dy[priority[j]];

						if (newx < 0 || newx > N - 1 || newy < 0 || newy > N - 1)
							continue;

						if (mat[newx][newy].visitedShark == shark.num) {
							break;
						}
					}
				}
				
				shark.dir = priority[priorityIdx];
				System.out.println("new dir for shark " + shark.num + " is " + shark.dir);
				shark.x = newx;
				shark.y = newy;
				temp[newx][newy].curSharks.add(shark);
				temp[newx][newy].visitedShark = shark.num;
				temp[newx][newy].smell = K;
				if(temp[x][y].smell>0)temp[x][y].smell -= 1;
				temp[x][y].curSharks.clear();
			}
		}
		
		mat = temp;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				while (mat[i][j].curSharks.size() > 1) {
					Collections.sort(mat[i][j].curSharks);
					Shark shark = mat[i][j].curSharks.get(0);
					sharkArr[shark.num] = null;
					mat[i][j].curSharks.remove(0);
				}
			}
		}

	}

}
