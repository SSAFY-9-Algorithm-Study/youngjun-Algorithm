package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BOJ1339_단어수학 {
	
	static int N;
	static int letNum;
	static String[] wordArr;
	static Set<Character> letters = new HashSet<>();
	static Map<Character, Integer> letterMap = new HashMap<>();
	static char[] letterArr;
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		wordArr = new String[N];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			wordArr[i] = str;
			for (int j = 0; j < str.length(); j++) {
				letters.add(str.charAt(j));
			}
		}
		
		letNum = letters.size();
		letterArr = new char[letNum];
		int i = 0;
		for (char chr : letters) {
			letterArr[i++] = chr;
		}
		perm(0,new int[letNum], new int[10]);
		
		
		System.out.println(ans);
		
		
	}
	
	public static void perm(int lev, int res[], int visited[]) {
		if(lev == letNum) {
//			System.out.println(Arrays.toString(res));
			for (int i = 0; i < letterArr.length; i++) {
				letterMap.put(letterArr[i],res[i]);
			}
//			System.out.println(letterMap);
			ans = Math.max(cal(res), ans);
		}
		
		else {
			for (int i = 9; i >= 10-letNum; i--) {
				
				if(visited[i]==0) {
					res[lev] = i;
					visited[i]=1;
					perm(lev+1,res,visited);
					res[lev] = 0;
					visited[i]=0;
				}
			}
		}
			
	}
	
	public static int cal(int res[]) {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			String word = wordArr[i];
			int wordLen = word.length();
			for (int j = 0; j < wordLen; j++) {
				sum += letterMap.get(word.charAt(j))*(Math.pow(10, wordLen-j-1));
			}
		}
		return sum;
	}

}
