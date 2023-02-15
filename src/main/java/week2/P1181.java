package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class P1181 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		String[] words = new String[n];
		for (int i = 0; i < n; i++) {
			words[i] = bf.readLine();
		}
		Arrays.sort(words);
		Arrays.sort(words, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				
				return o1.length() - o2.length();
			}
		});
		
		
		//중복 값 제거
		for (int i = 0; i < words.length; i++) {
			if(i<words.length-1 && !words[i].equals(words[i+1]))
			System.out.println(words[i]);
		}
		System.out.println(words[words.length-1]);
		
		bf.close();
	}

}