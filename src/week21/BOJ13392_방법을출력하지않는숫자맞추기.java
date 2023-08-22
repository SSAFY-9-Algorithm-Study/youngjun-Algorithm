package week21;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ13392_방법을출력하지않는숫자맞추기 {
	
	static int T;
	static String N, M;
	static class Screw{
		int cur;
		int step;
		boolean isLeft;
		int turns;
		                                                                                                                                                                                                                                                                        
	}
	static Queue<Screw> que = new LinkedList<>();
	static int[] stepLeft; 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		N = br.readLine();
		M =br.readLine();
		stepLeft = new int[T];
		Arrays.fill(stepLeft, Integer.MAX_VALUE);
		
		turn(N,0,0);
		
	}

	private static void turn(String cur, int step, int turnCnt) {
		
		int stepLeft = Math.abs(M.charAt(step) - cur.charAt(step)) ;
		int stepRight = 10-stepLeft;
		
		
		
	}

}
