package week8;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA1259_금속막대v2 {

	static int T;
	static int N;
	static int[] front;
	static int[] rear;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T + 1; t++) {
			N = Integer.parseInt(br.readLine());
			front = new int[N + 1];
			rear = new int[N + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				front[i] = Integer.parseInt(st.nextToken());
				rear[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 1; i <= N; i++) {
				dfs(rear[i]);
			}
		}
	}

	private static void dfs(int rear) {
	}
}
