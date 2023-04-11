package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ1715_카드정렬하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int sum = 0;
		Queue<Integer> numQue = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		for (int i = 0; i < N; i++) {
			numQue.add(Integer.parseInt(br.readLine()));
		}

		while (numQue.size() > 1) {
			int first = numQue.poll();
			int second = numQue.poll();
			sum += (first + second);
			numQue.offer(first + second);
		}
		
		System.out.println(sum);

	}

}
