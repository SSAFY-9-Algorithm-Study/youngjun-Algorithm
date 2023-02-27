package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SWEA1225 {
	
	static int T = 10;
	static int n = 8;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int i = 1; i < T+1; i++) {
			Deque<Integer> deq = new ArrayDeque<Integer>();
			int test = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				deq.add(Integer.parseInt(st.nextToken()));
			}
			
			int sub= 1;
			while(deq.getLast()>0) {
				deq.add(deq.removeFirst()-sub); 
				sub+=1;
				if(sub>5)
					sub%=5;
			}
			deq.removeLast();
			deq.add(0);
			System.out.print("#" + test + " ");
			while (!deq.isEmpty()) {
				System.out.print(deq.removeFirst() + " ");
			}
			System.out.println();
		}
	}

}
