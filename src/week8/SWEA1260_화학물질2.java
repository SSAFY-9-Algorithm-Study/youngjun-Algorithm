package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA1260_화학물질2 {

	static int T;
	static int N;
	static int[][] mat;
	static int[][] visited;
	static List<Integer> front;
	static List<Integer> rear;

//	static List<Integer> ansList;
	static ArrayList<Tuple> ansList;
	static ArrayList<Tuple> finalAnsList;
	static int ansListSize = 0;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int lastx;
	static int lasty;
	
	static class Tuple{
		int frontItem;
		int rearItem;
		
		public Tuple(int frontItem, int rearItem) {
			super();
			this.frontItem = frontItem;
			this.rearItem = rearItem;
		}
	}
	
	static int multiMax = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			N = Integer.parseInt(br.readLine());
			mat = new int[N][N];
			visited = new int[N][N];
			front = new ArrayList<>();
			rear = new ArrayList<>();
			multiMax = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (mat[i][j] > 0 && visited[i][j] == 0) {
						visited[i][j] = 1;
						bfs(i, j);
						front.add(lastx - i + 1);
						rear.add(lasty - j + 1);
					}

				}
			}

//			System.out.println(front);
//			System.out.println(rear);

			for (int i = 0; i < front.size(); i++) {
				ansList = new ArrayList<>();
				int rearItem = rear.get(i);
				ansList.add(new Tuple(front.get(i),rearItem));
				dfs(rearItem);
				if (ansList.size() == front.size()) {
//					System.out.println("possible route!");
					
					finalAnsList = ansList;
				}
			}

//			System.out.println("list size is " + finalAnsList.size());
//			System.out.print("#" + t + " ");
//			for (int i = 0; i < finalAnsList.size(); i++) {
//				System.out.print(" " + finalAnsList.get(i).frontItem);
//				System.out.print(" " + finalAnsList.get(i).rearItem);
//			}
//			System.out.println();
			
			getMaxCal(finalAnsList, 0);
//			System.out.println("list size is " + finalAnsList.size());
			System.out.println("#" + t + " " + multiMax);
			
			

		}
	}

	
	private static void getMaxCal(ArrayList<Tuple> tupleList, int multi) {
		if(tupleList.size()==1) {
//			System.out.println("cur multi is " + multi);
			multiMax = Math.min(multi, multiMax);
		}
		else {
			for (int i = 0; i < tupleList.size()-1; i++) {
				ArrayList<Tuple> newTupleList = (ArrayList<Tuple>) tupleList.clone();
				Tuple firstTuple = newTupleList.get(i);
				Tuple secondTuple = newTupleList.get(i+1);
				Tuple newTuple = new Tuple(firstTuple.frontItem, secondTuple.rearItem);
				
				
				int cal = multi+calMulti(firstTuple, secondTuple);
				newTupleList.set(i, newTuple);
				newTupleList.remove(i+1);
				getMaxCal(newTupleList, cal);
				
			}
		}
	}


	private static int calMulti(Tuple tuple, Tuple tuple2) {
		// TODO Auto-generated method stub
		return tuple.frontItem * tuple.rearItem * tuple2.rearItem;
	}


	// 저장된 front rear로 연속되게 이어진 리스트 반환
	private static void dfs(int rearItem) {
		for (int i = 0; i < front.size(); i++) {
			int frontItem = front.get(i);
			if (frontItem == rearItem) {
				ansList.add(new Tuple(frontItem,rear.get(i)));
				dfs(rear.get(i));
				break;
			}
		}

	}

	
	// 행렬을 구하여 세로를 front, 가로를 rear에 저장
	private static void bfs(int x, int y) {
		Deque<int[]> deq = new ArrayDeque<>();
		deq.offer(new int[] { x, y });

		while (!deq.isEmpty()) {
			int[] arr = deq.pollFirst();

			x = arr[0];
			y = arr[1];
			lastx = x;
			lasty = y;

			for (int k = 0; k < 4; k++) {
				int newx = x + dx[k];
				int newy = y + dy[k];
				if (newx < 0 || newx > N - 1 || newy < 0 || newy > N - 1 || visited[newx][newy] == 1
						|| mat[newx][newy] == 0)
					continue;
				visited[newx][newy] = 1;
				deq.offer(new int[] { newx, newy });
			}

		}

	}

}
