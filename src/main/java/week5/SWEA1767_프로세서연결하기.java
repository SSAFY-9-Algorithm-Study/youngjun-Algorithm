package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA1767_프로세서연결하기 {

	static int T;
	static int N;

	static class Processor {

		int x;
		int y;
		int visited;

		public Processor(int x, int y, int visited) {
			super();
			this.x = x;
			this.y = y;
			this.visited = visited;
		}

		@Override
		public String toString() {
			return "Processor [x=" + x + ", y=" + y + " , visited = " + visited + "]";
		}

	}
	
	static List<Processor> processorList;
	static List<Processor> processorListWithoutWall;
	static List<Integer>[] processorXYList;
//	static List<Integer> processorYList;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		processorList = new ArrayList<>();
		processorXYList = new ArrayList[N];
		processorListWithoutWall = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int isProcessor = Integer.parseInt(st.nextToken());
				if (isProcessor == 1) {
					processorList.add(new Processor(i, j,0));
					processorXYList[i].add(j);
					if (i != 0 && j != 0)
						processorListWithoutWall.add(new Processor(i, j,0));
				}
			}

		}
		
		for (int i = 0; i < N; i++) {
			Collections.sort(processorXYList[i]);
		}
		
		
		
		System.out.println(processorList);
		System.out.println(processorListWithoutWall);
		
		link();

	}
	
	public static void link() {
		
		for (int i = 0; i < processorListWithoutWall.size(); i++) {
			Processor curProcessor = processorListWithoutWall.get(i);
			
			if(curProcessor.visited==0 && !(processorXYList[curProcessor.x].size()>0 && processorXYList[curProcessor.x].get(0)<curProcessor.y)) {
				curProcessor.visited=1;
			}
				
			
		
		}
		
	}

}
