package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWEA1218 {
	
	static int T = 10;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 1; i < T+1; i++) {
			int n = Integer.parseInt(br.readLine());
			int possible = 1;
			char[] arr = br.readLine().toCharArray();
			Stack<Character> stack = new Stack<>();
			for (int j = 0; j < n; j++) {
				char cur = arr[j];
				if(cur=='}') {
					if(stack.pop()!='{') {
						possible = 0;
						break;
					}
				}
				
				else if(cur==']') {
					if(stack.pop()!='[') {
						possible = 0;
						break;
					}
				}
				
				
				else if(cur==')') {
					if(stack.pop()!='(') {
						possible = 0;
						break;
					}
				}
				
				else if(cur=='>') {
					if(stack.pop()!='<') {
						possible = 0;
						break;
					}
				}
				else
					stack.add(cur);

			}
			System.out.println("#" + i + " " + possible);
		}
		
	}

}
