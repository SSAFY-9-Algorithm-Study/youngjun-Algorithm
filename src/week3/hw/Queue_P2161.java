package week3.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Queue_P2161 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Deque<Integer> deq = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			deq.add(i+1);
		}
		
		while(deq.size()>1) {
			System.out.print(deq.removeFirst() + " ");
			deq.addLast(deq.removeFirst());
		}
		
		System.out.println(deq.getFirst());		
	}

}
