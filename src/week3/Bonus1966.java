package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Bonus1966 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(bf.readLine());
		for (int i = 0; i < test; i++) {
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int len = Integer.parseInt(st.nextToken());
			int idx = Integer.parseInt(st.nextToken());
			Deque<Integer> deq = new LinkedList<Integer>();
			Deque<Integer> idxQue = new LinkedList<Integer>();
			Queue<Integer> orderQue = new PriorityQueue<Integer>(Collections.reverseOrder());
			
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < len; j++) {
				int val = Integer.parseInt(st.nextToken());
				orderQue.add(val);
				deq.add(val);
				idxQue.add(j);
			}
			
			int max = orderQue.poll();
			int rank = 1;
			while(!deq.isEmpty()) {
				if(deq.peekFirst()==max) {
					int val = deq.poll();
					int curIdx = idxQue.poll();
					if (curIdx == idx) {
						System.out.println(rank);
						break;
					}
					rank++;
					if(!orderQue.isEmpty())
						max = orderQue.poll();
				}
				else {
					deq.add(deq.poll());
					idxQue.add(idxQue.poll());
				}
			}
		}
		
	
		
		
	}

}
