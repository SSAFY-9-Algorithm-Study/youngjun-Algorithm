
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class SWEA1249_º¸±Þ·Î {

	static int T;
	static int N;
	static int[][] mat;
	static long[][] DP;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	static class Point {
		int val;
		int x;
		int y;
		

		public Point(int val, int x, int y) {
			super();
			this.val = val;
			this.x = x;
			this.y = y;
			
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Queue<Point> que = new PriorityQueue<>(new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				// TODO Auto-generated method stub
				return o1.val - o2.val;
			}
		});

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			N = Integer.parseInt(br.readLine());
			mat = new int[N][N];
			DP = new long[N][N];

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					mat[i][j] = str.charAt(j) - '0';
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
						DP[i][j] = Integer.MAX_VALUE;
				}
			}
			DP[0][0]=0;

			que.add(new Point(0,0,0));

			int x;
			int y;
			int val;
			while (!que.isEmpty()) {
				Point point = que.poll();
				
				x = point.x;
				y = point.y;
				val = point.val;
				
				if(val>DP[x][y]) continue;

				for (int i = 0; i < 4; i++) {
					int newx = x + dx[i];
					int newy = y + dy[i];
					if (newx < 0 || newx > N - 1 || newy < 0 || newy > N - 1)
						continue;
					if (DP[newx][newy]>val + mat[newx][newy]) {
						DP[newx][newy] = val + mat[newx][newy];
						que.add(new Point(val + mat[newx][newy],newx,newy));
					}
				}

			}

			System.out.println("#" + t + " " + DP[N - 1][N - 1]);

		}

	}

}
