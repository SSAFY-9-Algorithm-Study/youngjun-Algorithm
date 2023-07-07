package week18;

import java.io.*;
import java.util.*;

//1
//6
//I 1
//I 1
//I 1
//I 1
//D -1
//D 1


//1
//9
//I 36
//I 37
//I 38
//D 1
//D 1
//I 39
//I 40
//D -1
//D -1
public class BOJ7662_이중우선순위큐 {
	static class Node {
		int val;
		int idx;

		@Override
		public String toString() {
			return val + "";
		}

	}

	static int T, N;
	static int curIdx, curCnt;
	static boolean isEmpty;
	static Queue<Node> ascQue = new PriorityQueue<Node>(new Comparator<Node>() {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.val - o2.val;
		}
	});
	static Queue<Node> descQue = new PriorityQueue<Node>(new Comparator<Node>() {

		@Override
		public int compare(Node o1, Node o2) {
			return o2.val - o1.val;
		}
	});

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T + 1; t++) {
			N = Integer.parseInt(br.readLine());
			ascQue.clear();
			descQue.clear();
			isEmpty = false;
			curIdx = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				char ops = st.nextToken().charAt(0);
				int num = Integer.parseInt(st.nextToken());
				switch (ops) {
				case 'I':
					enque(num);
					break;
				case 'D':
					deque(num);
					break;
				default:
					break;
				}
				System.out.println(ascQue);
				System.out.println(descQue);
			}
			if (ascQue.isEmpty() || descQue.isEmpty()) {
				System.out.println("EMPTY");
			} else {
				System.out.print(descQue.poll().val + " " + ascQue.poll().val);
			}

		}

	}

	private static void deque(int num) {
		Node curMin = ascQue.peek();
		Node curMax = descQue.peek();
		if (curMax == null || curMin == null || curCnt == 0) {
			ascQue.clear();
			descQue.clear();
			return;
		}
		Node curNode;
		if (num == -1) {
			curNode = ascQue.poll();
			curCnt--;
//			if (curNode.idx == curMax.idx) {
//				ascQue.clear();
//				descQue.clear();
//				return;
//			}
		} else {
			curNode = descQue.poll();
			curCnt--;
//			if (curNode.idx == curMin.idx) {
//				ascQue.clear();
//				descQue.clear();
//				return;
//			}
		}
		if (curMax == null || curMin == null || curCnt == 0) {
			ascQue.clear();
			descQue.clear();
			return;
		}
	}

	private static void enque(int num) {
		Node node = new Node();
		node.idx = curIdx;
		node.val = num;
		ascQue.add(node);
		descQue.add(node);
		curIdx++;
		curCnt++;
	}

}
