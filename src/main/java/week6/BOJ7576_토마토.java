package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ7576_토마토 {

	static int H;
	static int W;
	static int mat[][];
	static int temp[][];
	static int dayArr[][];
	static int unripeCnt = 0;
	static int days = 0;
	static Deque<int[]> deq = new ArrayDeque<int[]>();
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		mat = new int[H][W];
		temp = new int[H][W];
		dayArr = new int[H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				int val = Integer.parseInt(st.nextToken());
				if (val == 0)
					unripeCnt++;
				mat[i][j] = val;
				dayArr[i][j] = Integer.MAX_VALUE;
			}
		}
		
		if(unripeCnt == 0) {
			System.out.println(0);
			System.exit(0);
		}

			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (mat[i][j] == 1)
						deq.add(new int[] {i,j,1}); //deq에 미리 넣어야 시간초과가 나지 않는다.
				}
			}
			spread();

			for (int i = 0; i < H; i++) {
				if(days==-1) break;
				for (int j = 0; j < W; j++) {
					if (mat[i][j] == 0) {
						days=-1;
						break;
					}
					else {
						if(mat[i][j]>days)
							days= mat[i][j];
					}
						
				}
			}
			if(days>0) days--;
			System.out.println(days);

	}

	public static void spread() {

		while (!deq.isEmpty()) {
			int arr[] = deq.pollFirst();
			int x = arr[0];
			int y = arr[1];
			days = arr[2];

			for (int i = 0; i < 4; i++) {
				int newx = x + dx[i];
				int newy = y + dy[i];
				if (newx < 0 || newx > (H - 1) || newy < 0 || newy > (W - 1))
					continue;
				if (mat[newx][newy] != -1 &&  mat[newx][newy] != 1 && dayArr[newx][newy]>(days+1)) {
					mat[newx][newy] = days+1;
					dayArr[newx][newy] = days+1;
					deq.add(new int[] {newx, newy, days+1});
				}
			}

		}

	}

}
