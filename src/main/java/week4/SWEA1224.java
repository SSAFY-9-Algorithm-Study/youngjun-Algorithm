package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

import org.omg.CORBA.INTERNAL;

public class SWEA1224 {

	static int T = 10;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> ans = new Stack<>();
		Stack<Character> temp = new Stack<>();
		int lock = 0;

		for (int i = 1; i < T + 1; i++) {
			int n = Integer.parseInt(br.readLine());

			String exp = br.readLine();
			for (int j = 0; j < n; j++) {
				char cur = exp.charAt(j);
				if ( cur == '(' || cur == '*' || cur == '+') {
					temp.add(exp.charAt(j));
				}
				else if(cur == ')') {
					char pop = temp.pop();
					while(pop!='(') {
						ans.add(pop);
						pop = temp.pop();
					}
					lock-=1;
				}
				
				
				else {
					ans.add(exp.charAt(j));
					if (temp.size() > 0) {
						if(temp.peek()=='(') {
							lock+=1;
						}
						else if(lock == 0)
							while(temp.size()>0)
								ans.add(temp.pop());
					}
				}

			}
			System.out.println(ans.toString());
			
			
			
			

		}

	}

}
