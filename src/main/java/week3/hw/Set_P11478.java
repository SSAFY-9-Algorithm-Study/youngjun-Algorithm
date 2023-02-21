package week3.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Set_P11478 {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String s = bf.readLine();
		int sLen = s.length();
		Set<String> sSet = new HashSet<>();
		for (int i = 1; i <= sLen; i++) {
			for (int j = 0; j < sLen-i+1; j++) {
				sSet.add(s.substring(j, j+i));
			}
		}
		
		System.out.println(sSet.size());
	}

}
