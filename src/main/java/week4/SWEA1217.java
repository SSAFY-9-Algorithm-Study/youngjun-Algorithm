package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class SWEA1217 {
	
	static int T = 10;
	static int m;
	static int res;
	static int n;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int i = 1; i <T+1; i++) {
			int t = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			res = n;
			power(n,1);
			System.out.println("#" + t + " " + res);
		}
		
		
	}
	
	public static void power(int result, int over) {
		if(m==over)
			res =  result;
		else {
			result = result * n;
			power(result,++over);
		}

	}

}
