package week14;

import java.io.*;
import java.util.*;

import javax.xml.transform.Templates;

public class BOJ16639_괄호추가하기3 {

	static int N;
	static char[] exp;
	static int max = Integer.MIN_VALUE;
	static List<OpWithNum> opList;

	static class OpWithNum {
		char op;
		int num;

		public OpWithNum(char op, int num) {
			super();
			this.op = op;
			this.num = num;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		opList = new ArrayList<>();
		String str = br.readLine();

		opList.add(new OpWithNum('+', str.charAt(0) - '0'));
		for (int i = 1; i < str.length(); i += 2) {

			opList.add(new OpWithNum(str.charAt(i), str.charAt(i + 1) - '0'));

		}

		dfs(opList);
		System.out.println(max);

	}

	private static void dfs(List<OpWithNum> opList2) {
		if (opList2.size() == 1) {
			max = Math.max(max, opList2.get(0).num);
			return;
		}

		for (int i = 0; i < opList2.size() - 1; i++) {
			List<OpWithNum> tempList = new ArrayList<OpWithNum>();
			tempList.addAll(opList2);
			OpWithNum num1 = tempList.get(i);
			OpWithNum num2 = tempList.get(i + 1);

			OpWithNum res = cal(num1, num2);
			tempList.remove(num1);
			tempList.set(i, res);
			dfs(tempList);

		}
	}

	private static OpWithNum cal(OpWithNum num1, OpWithNum num2) {
		char resOp = num1.op;
		char calOp = num2.op;
		int calNum1 = num1.num;
		int calNum2 = num2.num;
		int res = 0;
		switch (calOp) {
		case '+':
			res = calNum1 + calNum2;
			break;
		case '-':
			res = calNum1 - calNum2;
			break;
		case '*':
			res = calNum1 * calNum2;
			break;
		default:
			break;
		}
		return new OpWithNum(resOp, res);
	}
}
