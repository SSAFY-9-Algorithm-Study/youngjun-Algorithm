package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ8972_미친아두이노 {

	static int H, W;
	static char[][] mat;
	
	static List<Arduino> ardList = new ArrayList<>();
	static List<Arduino> tempArdList = new ArrayList<>();
	static List<Integer> moves = new ArrayList<>();
	
	static int[][] curArd;
	
	static Me me = new Me();
	
	static boolean end = false;
	static int ans;

	static int[] dx = { 0, 1, 1, 1, 0, 0, 0, -1, -1, -1 };
	static int[] dy = { 0, -1, 0, 1, -1, 0, 1, -1, 0, 1 };

	static class Arduino {
		int x;
		int y;
		boolean isValid;

		public Arduino(int x, int y, boolean isValid) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static class Me {
		int x;
		int y;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		mat = new char[H][W];
		curArd = new int[H][W];

		for (int i = 0; i < H; i++) {
			String str = br.readLine();
			for (int j = 0; j < W; j++) {
				char val = str.charAt(j);
				if (val == 'R') {
					ardList.add(new Arduino(i, j, true));
					curArd[i][j] = 1;
				}
				if (val == 'I') {
					me.x = i;
					me.y = j;
				}

				mat[i][j] = val;
			}

		}
		String str = br.readLine();
		for (int i = 0; i < str.length(); i++) {
			moves.add(str.charAt(i) - '0');
		}

		// 동작 (움직임의 횟수만큼 반복)
		for (int i = 0; i < moves.size(); i++) {
			
			// flag가 설정되면(아두이노와 만나면) break
			if (end) {
				ans = i;
				break;
			}

			int curMove = moves.get(i);
			int curDx = dx[curMove];
			int curDy = dy[curMove];

			mat[me.x][me.y] = '.';
			mat[me.x + curDx][me.y + curDy] = 'I';
			me.x += curDx;
			me.y += curDy;
			

			for (int j = 0; j < ardList.size(); j++) {
				Arduino curArr = ardList.get(j);
				move(curArr);
			}

			tempArdList.clear();

			for (int j = 0; j < ardList.size(); j++) {
				Arduino curArr = ardList.get(j);
				if (curArd[curArr.x][curArr.y] == 1) {
					tempArdList.add(curArr);
				} else {
					curArd[curArr.x][curArr.y] = 0;
				}
			}

			ardList.clear();

			for (int j = 0; j < tempArdList.size(); j++) {
				ardList.add(tempArdList.get(j));
			}

		}

		// 출력

		if (end) {
			System.out.println("kraj " + ans);
		} 
		
		else {
			char[][] ansMat = new char[H][W];
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					ansMat[i][j] = '.';
				}
			}

			ansMat[me.x][me.y] = 'I';
			for (int i = 0; i < ardList.size(); i++) {
				Arduino curArd = ardList.get(i);
				ansMat[curArd.x][curArd.y] = 'R';
			}

			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(ansMat[i][j]);
				}
				System.out.println();
			}
		}
	}

	private static void move(Arduino curArr) {
		int x = curArr.x;
		int y = curArr.y;
		int xAns = 0;
		int yAns = 0;
		int minDis = Integer.MAX_VALUE;

		for (int i = 1; i <= 9; i++) {
			int newx = x + dx[i];
			int newy = y + dy[i];
			int curDis = (Math.abs(newx - me.x) + Math.abs(newy - me.y));

			if (curDis < minDis) {
				minDis = curDis;
				xAns = newx;
				yAns = newy;
			}
		}
		curArd[xAns][yAns] += 1;
		curArd[x][y] -= 1;
		curArr.x = xAns;
		curArr.y = yAns;

		if (me.x == xAns && me.y == yAns) {
			end = true;
		}
	}

}
