package week7.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17822_원판돌리기 {

	static int N;
	static int M;
	static int T;
	static int xi;
	static int di;
	static int ki;
	static int[] dx = { -1, 1 };

	static class Point {
		int val;
		Boolean visited;
		Boolean erased;

		public Point(int val, Boolean visited, Boolean erased) {
			super();
			this.val = val;
			this.visited = visited;
			this.erased = erased;
		}
	}
	//원판의 개수는 고정적인데 왜 리스트? 배열로 선언하자
	static List<Point[]> circleList = new ArrayList<Point[]>();
	static int[][] visited;
	static int sum = 0;
	static Boolean isNearNum = false;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		visited = new int[N][M];
		cnt = N * M;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Point[] arr = new Point[M];
			for (int j = 0; j < M; j++) {
				int val = Integer.parseInt(st.nextToken());
				arr[j] = new Point(val, false, false);
				sum += val;
			}
			circleList.add(arr);
		}

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			xi = Integer.parseInt(st.nextToken());
			di = Integer.parseInt(st.nextToken());
			ki = Integer.parseInt(st.nextToken());

			if (sum == 0)
				break;
			isNearNum = false;
			turn(xi, di, ki);
			erase();

			if (!isNearNum) {
				double avg = (double) sum / (double) cnt;
				int temp = 0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						if (circleList.get(i)[j].val > avg && !circleList.get(i)[j].erased) {
							circleList.get(i)[j].val -= 1;
							temp--;
						} else if (circleList.get(i)[j].val < avg && !circleList.get(i)[j].erased) {
							circleList.get(i)[j].val += 1;
							temp++;
						}

					}
				}
				sum += temp;
			}
		}
		System.out.println(sum);

	}

	public static void erase() {
		for (int i2 = 0; i2 < N; i2++) {
			for (int i = 0; i < M; i++) {
				Point[] arr = circleList.get(i2);
				if (arr[i].erased)
					continue;
				Boolean erase = false;
				for (int j = 0; j < 2; j++) {
					
					//원형이므로 인덱스 벗어나면 끝값으로 적용
					int newx = i + dx[j];
					if (newx < 0)
						newx = M - 1;
					else if (newx > M - 1)
						newx = 0;
					
					//같은 원 내에서 지우기
					if (arr[i].val == arr[newx].val && !arr[newx].erased) {
						erase = true;
						arr[newx].visited = true;
					}
				}

				for (int j = 0; j < 2; j++) {
					int newx = i2 + dx[j];
					
					//다른 원끼리는 인덱스 벗어나면 안됨
					if (newx < 0 || newx > N - 1)
						continue;
					Point[] arr2 = circleList.get(newx);

					//원 간에 지우기
					if ((arr[i].val == arr2[i].val) && !arr2[i].erased) {
						erase = true;
						arr2[i].visited = true;
					}
				}

				//지울 수 있는지를 저장 : 지울수 없다면 isNearNum이 false임
				if (erase) {
					arr[i].visited = true;
					isNearNum = true;
				}
			}
		}
		
		
		//visited=true면 지울 수 있다고 판단하고 지움, erased에 적용
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (circleList.get(i)[j].visited) {
					circleList.get(i)[j].visited = false;
					circleList.get(i)[j].erased = true;
					sum -= circleList.get(i)[j].val;
					cnt--;
				}
			}
		}
	}

	public static void turn(int xi, int di, int ki) {
		//4번 이상 돌면 나머지를 구해서 돌자
		//인덱스 햇갈리니까 새로운 배열 만들어 대입하는 방법도 생각하자
		for (int turns = 0; turns < ki; turns++) {
			if (di == 1) {
				for (int i = 0; i < N; i++) {
					if ((i + 1) % xi == 0) {
						Point[] arr = circleList.get(i);
						Point temp = arr[0];
						for (int j = 0; j < M - 1; j++) {
							arr[j] = arr[j + 1];
						}
						arr[M - 1] = temp;
						circleList.set(i, arr);
					}
				}
			} else {
				for (int i = 0; i < N; i++) {
					if ((i + 1) % xi == 0) {
						Point[] arr = circleList.get(i);
						Point temp = arr[M - 1];
						for (int j = M - 1; j > 0; j--) {
							arr[j] = arr[j - 1];
						}
						arr[0] = temp;
						circleList.set(i, arr);
					}
				}
			}
		}
	}
}
