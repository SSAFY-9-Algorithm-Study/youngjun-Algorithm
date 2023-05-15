package week4;

import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class SWEA1224 {

	static int T = 10;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> ans = new Stack<>();
		Stack<Character> temp = new Stack<>();
		Stack<Integer> cal = new Stack<>();
		int lock = 0;

		for (int i = 1; i < T + 1; i++) {
			int n = Integer.parseInt(br.readLine());

			String exp = br.readLine();
			for (int j = 0; j < n; j++) {
				char cur = exp.charAt(j);
				if (cur == '(') { // 연산자면 연산자 스택 add
					// *면 그냥 같은 *있으면 빼서 ans에 더해줌 +면 그냥 연산자 스택에 채워줌
					// +면 + 거나 * 면 스택에서 계속 다 뺀 다음 채워줌
					// (면 그냥 넣음
					temp.add(cur);
				}

				else if (cur == ')') { // 닫기 연산자면 ( 나올때까지 pop
					if (temp.size() > 0) {
						char top = temp.peek();
						while (top != '(' && temp.size() > 0) {
							ans.add(temp.pop());
							top = temp.peek();
						}
					}

					if (temp.size() > 0)
						temp.pop();
				}

				else if (cur == '+' || cur == '-') {
					if (temp.size() > 0) {
						char top = temp.peek();
						while (top != '(' && temp.size() > 0) {
							ans.add(temp.pop());
							if (temp.size() > 0)
								top = temp.peek();
						}
					}

					temp.add(cur);
				}

				else if (cur == '*' || cur == '/') {
					if (temp.size() > 0) {
						char top = temp.peek();
						while (top != '(' && temp.size() > 0) {
							top = temp.peek();
							if (top == '+' || top == '-') {
								break;
							} else {
								ans.add(temp.pop());
							}

							if (temp.size() > 0)
								top = temp.peek();
						}
					}

					temp.add(cur);
				}

				else { // 숫자일떄는 ans 에 add
					ans.add(cur);
				}
			}
			while (temp.size() > 0)
				ans.add(temp.pop());

			for (char cha : ans)
//				System.out.print(cha);
			
			for(int i1=0; i1<ans.size(); i1++) {
				char cur = ans.get(i1);
				
				if(cur=='+' || cur=='*') {
					int num2 = cal.pop();
					int num1 = cal.pop();
					if(cur=='+') {
						cal.add((num1+num2));
					}
					else
						cal.add((num1*num2));
				}
				else
					cal.add(cur-'0');
					
			}
			
			System.out.println("#" + i + " " + (cal.pop()));
			
		}

	}

}
