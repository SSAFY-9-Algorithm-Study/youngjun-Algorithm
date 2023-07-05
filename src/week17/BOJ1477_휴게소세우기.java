package week17;

import java.io.*;
import java.util.*;

public class BOJ1477_휴게소세우기 {

	static int N, M, L;
	static int[] pointList;
	static int ans;
	static PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2-o1;
		}
	});

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		pointList = new int[N + 2];
		pointList[0] = 0;
		pointList[N + 1] = L;
		if(N>0) {
			st = new StringTokenizer(br.readLine());
		}
		
		for (int i = 1; i <= N; i++) {
			pointList[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(pointList);
		for (int i = 0; i < N + 1; i++) {
			pq.add(pointList[i + 1] - pointList[i]);
		}
		System.out.println(Arrays.toString(pointList));
		System.out.println(pq);

		while (M > 0) {
			int curVal = pq.poll();
			
//			System.out.println(curVal);
			int nextVal = pq.peek()==null? 0 : pq.peek();
//			System.out.println(nextVal);
			
			int div = M + 1;
			boolean isbreak = false;
			int addVal = curVal / (M + 1);
			int leftOver = curVal - (addVal * (M+1));
			for (int i = 1; i <= M; i++) {
				if (curVal / (i + 1) < nextVal) {
					div = i + 1;
					addVal = curVal / div;
					leftOver = curVal - addVal*div;
					M -= i;
					isbreak = true;
					break;
				}
			}
			if(!isbreak) M=0;
			int[] arr = new int[div];
			int idx = 0;
			Arrays.fill(arr, addVal);
			
			while(leftOver>0) {
				arr[idx]+=1;
				idx++;
				leftOver--;
			}
			
			System.out.println("arr is " + Arrays.toString(arr));
			for(int i : arr) {
				pq.add(i);
			}
		}
		System.out.println(pq);
		System.out.println(pq.poll());
	}

}
