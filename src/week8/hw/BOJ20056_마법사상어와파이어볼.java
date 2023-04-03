package week8.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ20056_마법사상어와파이어볼 {

	static int N;
	static int M;
	static int K;
	static int[][] mat;
	//파이어볼 리스트
	static List<Ball> ballList = new ArrayList<>();
	//이동마다 갱신할 파이어볼 리스트
	static List<Ball> newBallList = new ArrayList<>();
	//ballList 삭제위한 temp
	static List<Ball> tempBallList = new ArrayList<>();
	
	//0-7방향 dx,dy
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int ans;

	static class Ball {
		int x;
		int y;
		int weight;
		int speed;
		int dir;

		public Ball(int x, int y, int weight, int speed, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.weight = weight;
			this.speed = speed;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		mat = new int[N][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			ballList.add(new Ball(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}

		for (int i = 0; i < K; i++) {
			iterateBall();
			sumAndDivide();
			
			//이동마다 ballList갱신 및 newBallList 초기
			ballList = new ArrayList<>(newBallList);
			newBallList.clear();
		}

		for (int i = 0; i < ballList.size(); i++) {
			ans += ballList.get(i).weight;
		}
		System.out.println(ans);
	}

	// 2이상의 파이어볼 명령
	private static void sumAndDivide() {
		int x;
		int y;
		int dir;
		int ballCnt = 1;
		int speed;
		int weight;
		int oddCnt = 0;
		int evenCnt = 0;
		
		
		while (!ballList.isEmpty()) {
			// 첫번째 파이어볼 가져오고 정보 저장
			Ball curBall = ballList.get(0);
			x = curBall.x;
			y = curBall.y;
			dir = curBall.dir;
			speed = curBall.speed;
			weight = curBall.weight;
			if (dir % 2 == 0)
				evenCnt++;
			else
				oddCnt++;
			ballList.remove(0);
			tempBallList = new ArrayList<>(ballList);
			
			// ballList들에 대해 첫번째 파이어볼과 같은 위치 값가져와 질량, 속도 더해줌
			for (int i = 0; i < tempBallList.size(); i++) {
				curBall = tempBallList.get(i);
				if (curBall.x == x && curBall.y == y) {
					ballCnt++;
					weight += curBall.weight;
					speed += curBall.speed;
					if (curBall.dir % 2 == 0)
						evenCnt++;
					else
						oddCnt++;
					ballList.remove(curBall);
				}

			}
			
			
			// 두 개이상의 파이어볼 있을 때
			if (ballCnt > 1) {
				weight /= 5;
				speed = speed / ballCnt;
				if (weight > 0) {
					if (oddCnt == 0 || evenCnt == 0) {
						for (int i = 0; i < 4; i++) {
							newBallList.add(new Ball(x, y, weight, speed, 2 * i));
						}

					} else {
						for (int i = 0; i < 4; i++) {
							newBallList.add(new Ball(x, y, weight, speed, 2 * i + 1));
						}
					}
				}

			}
			
			
			//해당 위치에 하나의 파이어볼만 있을 때
			else {
				if (weight != 0)
					newBallList.add(new Ball(x, y, weight, speed, dir));
			}

			ballCnt = 1;
			oddCnt = 0;
			evenCnt = 0;

		}
	}

	
	//이동
	private static void iterateBall() {
		int dir = 0;
		int speed = 0;
		int x = 0;
		int y = 0;
		for (int i = 0; i < ballList.size(); i++) {
			Ball curBall = ballList.get(i);
			dir = curBall.dir;
			speed = curBall.speed;
			x = curBall.x;
			y = curBall.y;
			for (int j = 0; j < speed; j++) {
				x += dx[dir];
				y += dy[dir];
				if (x == 0)
					x = N;
				else if (x == N + 1)
					x = 1;
				if (y == 0)
					y = N;
				else if (y == N + 1)
					y = 1;
			}
			curBall.x = x;
			curBall.y = y;
		}
	}
}