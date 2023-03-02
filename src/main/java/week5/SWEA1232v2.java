package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class SWEA1232v2 {
	
	static Node[] nodeArr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		nodeArr = new Node[n+1];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			char data = st.nextToken().charAt(0);
			int left = -1;
			int right = -1;
			if(st.hasMoreTokens()) {
				left = Integer.parseInt(st.nextToken());
			}
			if(st.hasMoreTokens()) {
				right = Integer.parseInt(st.nextToken());
			}
			
			nodeArr[i+1] = new SWEA1232v2().new Node(data, i, left, right);
		}
		
		inorder_traverse(1);
		
	}
	
	public static void inorder_traverse(int i) {
		
		if(nodeArr[i].left != -1)
		inorder_traverse(nodeArr[i].left);
		
		System.out.println(nodeArr[i].data);
		if(nodeArr[i].right != -1)
		inorder_traverse(nodeArr[i].right);
	}
	
	
	 class Node {
			
			char data;
			int num;
			int left;
			int right;
			
			
			public Node(char data, int num, int left, int right) {
				super();
				this.data = data;
				this.num = num;
				this.left = left;
				this.right = right;
			}
		}

}