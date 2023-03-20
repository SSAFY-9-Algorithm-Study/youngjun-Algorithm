package week7.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17144_미세먼지안녕 {

	static int H;
	static int W;
	static int T;
	static int mat[][];
	static int temp[][];
	static int sum = 0;
	static int cleanBottomX;
	static int cleanBottomY;

	static int[] dx = { -1, 1, 0, 0 }; //상,하,좌,우
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		mat = new int[H][W];
		temp = new int[H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				int val = Integer.parseInt(st.nextToken());
				mat[i][j] = val;
				if (val > 0)
					sum += val;
				if (val == -1) {//공청의 아랫부분 좌표
					cleanBottomX = i;
					cleanBottomY = j;
				}
			}
		}

		for (int i = 0; i < T; i++) {
			temp = new int[H][W];
			for (int j = 0; j < H; j++) {
				for (int j2 = 0; j2 < W; j2++) {
					if (mat[j][j2] > 0)
						spread(j, j2);
				}
			}
			
			//원래 배열에 temp 대입
			for (int i2 = 0; i2 < H; i2++) {
				for (int j = 0; j < W; j++) {
					mat[i2][j] = temp[i2][j];
				}
			}
			clean();
		}
		System.out.println(sum);

	}

	private static void spread(int x, int y) {
		int spreadCnt = 4;
		int newx;
		int newy;
		for (int i = 0; i < 4; i++) {
			newx = x + dx[i];
			newy = y + dy[i];
			if (newx < 0 || newx > H - 1 || newy < 0 || newy > W - 1 || mat[newx][newy] == -1)
				spreadCnt--;
		}

		for (int i = 0; i < 4; i++) {
			newx = x + dx[i];
			newy = y + dy[i];
			if (newx < 0 || newx > H - 1 || newy < 0 || newy > W - 1 || mat[newx][newy] == -1)
				continue;
			temp[newx][newy] += mat[x][y] / 5;
		}
		temp[x][y] += mat[x][y] - mat[x][y] / 5 * spreadCnt;
	}

	private static void clean() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				//좌
				if((i==0 || i==H-1) && j+dy[2]>=0 && j+dy[2]<=W-1 ) {
					mat[i+dx[2]][j+dy[2]] = temp[i][j];
				}
				//우
				if((i==cleanBottomX || i==cleanBottomX-1) && j+dy[3]<=W-1) {
					mat[i+dx[3]][j+dy[3]] = temp[i][j];
				}
				
				//상
				if(j==0 && i+dx[0]>=cleanBottomX) {
					mat[i+dx[0]][j+dy[0]] = temp[i][j];
				}
				if(j==W-1 && i+dx[0]>=0 && i<=cleanBottomX-1) {
					mat[i+dx[0]][j+dy[0]] = temp[i][j];
				}
				
				//하
				if(j==0 && i+dx[1]<=cleanBottomX-1) {
					mat[i+dx[1]][j+dy[1]] = temp[i][j];
				}
				
				if(j==W-1 && i+dx[1]<=H-1 && i>=cleanBottomX) {
					mat[i+dx[1]][j+dy[1]] = temp[i][j];
				}
			}
		}
		sum-= mat[cleanBottomX][0];
		sum-=mat[cleanBottomX-1][0];
		mat[cleanBottomX][0] = -1;
		mat[cleanBottomX-1][0] = -1;
	}

}
