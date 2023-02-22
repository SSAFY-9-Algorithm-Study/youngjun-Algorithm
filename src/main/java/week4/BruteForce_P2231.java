package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BruteForce_P2231 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String in = br.readLine();
		int n = Integer.parseInt(in);
		
		for (int i = 0; i < n; i++) {
			int check = i;
			int sum = check;
			int div = 10;
			
			while(check>=1) {
				sum+=check%div;
				check= check/10;
			}
			
			if(sum==n) {
				System.out.println(i);
				System.exit(0);
			}
			
		}
		System.out.println(0);
		
		

	}

}
