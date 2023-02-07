package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//https://www.acmicpc.net/problem/1874

public class BonusP1874v2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int nums = Integer.parseInt(bf.readLine());
        int cur = 1;
        Stack<Integer> numStack = new Stack<>();
        List<String> ans = new ArrayList<>();


        for (int i = 0; i < nums; i++) {
            int num = Integer.parseInt(bf.readLine());

            for (; cur <= num; cur++) {
                numStack.add(cur);
                ans.add("+");
            }
            if (numStack.peek() == num) {
                numStack.pop();
                ans.add("-");
            } else {
                ans.clear();
                ans.add("NO");
                break;
            }
        }

        for (int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i));
        }
    }
}
