import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1221_GNS {

	static int T;
	static int[] numcnt = new int[10];
	static String[] nums = { "ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN" };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {

			int N;
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				check(st.nextToken());
			}
			
			System.out.println("#" + t);
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < numcnt[i]; j++) {
					System.out.print(nums[i] + " ");
				}
			}
			
			
			numcnt = new int[10];

		}

	}

	private static void check(String str) {
		if(str.charAt(0)=='Z') {
			numcnt[0]+=1;
			return;
		}
		if(str.charAt(0)=='O') {
			numcnt[1]+=1;
			return;
		}
		if(str.charAt(0)=='T') {
			if(str.charAt(1)=='W')
			numcnt[2]+=1;
			else
			numcnt[3]+=1;
			return;
		}
		if(str.charAt(0)=='F') {
			if(str.charAt(1)=='O')
			numcnt[4]+=1;
			else
			numcnt[5]+=1;
			return;
		}
		if(str.charAt(0)=='S') {
			if(str.charAt(1)=='I')
			numcnt[6]+=1;
			else
			numcnt[7]+=1;
			return;
		}
		if(str.charAt(0)=='E') {
			numcnt[8]+=1;
			return;
		}
		if(str.charAt(0)=='N') {
			numcnt[9]+=1;
			return;
		}
	}

}
