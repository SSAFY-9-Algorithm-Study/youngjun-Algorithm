import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1463_1로만들기 {

	static int[] DP;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		DP = new int[N + 1];
		DP[1] = 0;
		int div2;
		int div3;
		
		
		for (int i = 2; i < N + 1; i++) {
			div2 = Integer.MAX_VALUE;
			div3 = Integer.MAX_VALUE;
			if (i % 2 == 0)
				div2 = DP[i / 2] + 1;
			if (i % 3 == 0)
				div3 = DP[i / 3] + 1;
			DP[i] = Math.min(DP[i - 1]+1, Math.min(div2, div3));
		}
		
		System.out.println(DP[N]);

	}


}
