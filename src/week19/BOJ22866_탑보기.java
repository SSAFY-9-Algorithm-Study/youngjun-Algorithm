package week19;

import java.io.*;
import java.util.*;

public class BOJ22866_탑보기 {
	static int N;
	static int leftMax;
	static int rightMax;

	static class Node {
		int left; // 왼쪽 개수
		int right; // 오른쪽 개수

		// integer Min, Max 쓰면 빼기 과정에서 이상한 값으로 바뀔 수 있으므로 20000으로 설정

		// i-leftIdx, rightIdx-i 값이 가장 크도록 초기화
		int leftIdx = -200000; // 왼쪽 가장 가까운 건물 인덱스
		int rightIdx = 200000; // 오른쪽 가장 가까운 건물 인덱스
	}

	static int[] arr;
	static Node[] nodeArr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N + 2];
		nodeArr = new Node[N + 2];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			nodeArr[i] = new Node();
		}
		
		//상향식 DP 풀이

		for (int i = 1; i <= N; i++) {
			if (leftMax > arr[i]) {
				int j = i - 1;
				while (arr[i] >= arr[j]) {
					j--;
				}
				// 나보다 큰 건물의 왼쪽 건물 개수 + 1이 내 왼쪽 건물 개수
				nodeArr[i].left = nodeArr[j].left + 1;
				nodeArr[i].leftIdx = j;
			} else {
				leftMax = arr[i];
			}
		}

		for (int i = N; i >= 1; i--) {
			if (rightMax > arr[i]) {
				int j = i + 1;
				while (arr[i] >= arr[j]) {
					j++;
				}
				// 나보다 큰 건물의 오른쪽 건물 개수 + 1이 내 오른쪽 건물 개수
				nodeArr[i].right = nodeArr[j].right + 1;
				nodeArr[i].rightIdx = j;
			} else {
				rightMax = arr[i];
			}
		}

		for (int i = 1; i <= N; i++) {
			Node curNode = nodeArr[i];
			int nearIdx = 0;
			// 안세지면 0
			if (curNode.left + curNode.right == 0)
				System.out.println(0);
			// 세져있으면 양 값 중 가까운 건물 인덱스
			else {
				nearIdx = i - curNode.leftIdx <= curNode.rightIdx - i ? curNode.leftIdx : curNode.rightIdx;
				System.out.println(nodeArr[i].left + nodeArr[i].right + " " + nearIdx);
			}

		}
	}

}
