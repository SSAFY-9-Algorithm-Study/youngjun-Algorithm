package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1759_암호만들기 {

	static int N;
	static int M;
	static char letter[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		letter = new char[M];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			letter[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(letter);
		comb(0, new int[M],0);
	}

	private static void comb(int lev, int[] res, int begin) {
		if(lev == N) {
			if(check(res)) {
				for (int i = 0; i < M; i++) {
					if(res[i]==1)
						System.out.print(letter[i]);
				}
				System.out.println();
			}

		}
		
		else {
			for (int i = begin; i < M; i++) {
				res[i] = 1;
				comb(lev+1,res,i+1);
				res[i]=0;
			}
		}
		
	}

	private static boolean check(int[] res) {
		int vowel = 0;
		int cont = 0;
		for (int i = 0; i < res.length; i++) {
			if(res[i]==1) {
				if(letter[i]=='a' || letter[i]=='e' || letter[i]=='i' || letter[i]=='o' || letter[i]=='u' )
					vowel++;
				else
					cont++;
				if(vowel>=1 && cont>=2)
					return true;
			}
			
		}
		return false;
		
	}
}
