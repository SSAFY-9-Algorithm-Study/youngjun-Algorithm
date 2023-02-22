package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BruteForce_P1436 {
	
	static int idx = 1;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ans = Integer.parseInt(br.readLine());
		int start = 666;
		while(idx!=ans) {
			if(check(++start))
				idx++;
		}
		System.out.println(start);
	}
	
	
	public static boolean check(Integer a) {
		char[] aChar = a.toString().toCharArray();
		int cnt = 0;
		for(char c : aChar) {
			if (c=='6') {
				if((++cnt)==3)
					return true;
			}
			else
				cnt=0;
		}
		return false;
	}
	
	

}
