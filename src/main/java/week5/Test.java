package week5;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.management.Query;

public class Test {

	
	public static void main(String[] args) {
		List<Integer> a = new LinkedList<>();
		Queue<Integer> b = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return 0;
			}
			
		});
		
		Collections.sort(a,Collections.reverseOrder());

		a.add(1);
		a.add(1);
		a.add(1);
		a.add(1);
		a.add(2);
		a.add(3);
		
		System.out.println(a);
	}
}
