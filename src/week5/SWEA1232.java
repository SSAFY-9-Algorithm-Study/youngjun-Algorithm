package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
	public String op;
	public double num;
	
	public int left;
	public int right;
	
	public Node(String op, double n, int l, int r) {
		this.op = op;
		this.num = n;
		this.left = l;
		this.right = r;
	}
}

public class SWEA1232 {
	public static int N;
	public static Node[] tree = new Node[1001];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		for (int tc = 1; tc <= 10; ++tc) {
			N = Integer.parseInt(br.readLine());
			
			for (int i = 1; i <= N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				
				// 인덱스는 전부 나오므로 첫 번째 입력은 버려
				st.nextToken();
				
				if (st.countTokens() > 1) {
					tree[i] = new Node(st.nextToken(), 0, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				}
				else {
					tree[i] = new Node("", Double.parseDouble(st.nextToken()), -1, -1);
				}
			}
			
			for (int i = N; i > 0; i--) {
				if (tree[i].left > 0 && tree[i].right > 0) {
					if (tree[i].op.equals("+")) {
						tree[i].num = tree[tree[i].left].num + tree[tree[i].right].num;
					}
					else if (tree[i].op.equals("-")) {
						tree[i].num = tree[tree[i].left].num - tree[tree[i].right].num;
					}
					else if (tree[i].op.equals("*")) {
						tree[i].num = tree[tree[i].left].num * tree[tree[i].right].num;
					} 
					else {
						tree[i].num = tree[tree[i].left].num / tree[tree[i].right].num;
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append((int) tree[1].num).append("\n");
		}
		
		System.out.print(sb);
	}
}