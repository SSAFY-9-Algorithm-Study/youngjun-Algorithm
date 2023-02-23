package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SWEA1234 {
	
	static int T = 10;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int i = 1; i < T+1; i++) {
			st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt( st.nextToken());
			Deque<Character> deq = new ArrayDeque<Character>();
			String str = st.nextToken();
			
			for (int j = 0; j < len; j++) {
				if(deq.size()>0 && deq.peekLast()==str.charAt(j)) {
					deq.pollLast() ;
				}
				else {
					deq.add(str.charAt(j));
				}
					
				
			}
			StringBuffer sb = new StringBuffer();
			int size = deq.size();
			for (int j = 0; j < size; j++) {
				sb.append(deq.pollFirst());
			}
			
			System.out.println("#" + i + " " + sb.toString());
		}
	}

}
