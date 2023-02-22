package week3.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Stack_P1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int nums = Integer.parseInt(bf.readLine());
        int cur = 1;
        Stack<Integer> numStack = new Stack<>();
        List<String> ans = new ArrayList<>();


        for (int i = 0; i < nums; i++) {
            int num = Integer.parseInt(bf.readLine());
            if(cur>num){
                if(num!=numStack.pop()){
                    ans.clear();
                    ans.add("NO");
                    break;
                }
                else{
                    ans.add("-");
                }
            }
            else{
                for (int j = cur; j <= num; j++) {
                    numStack.add(j);
                    ans.add("+");
                }
                cur = numStack.pop()+1;
                ans.add("-");
            }

        }

        for (int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i));
        }
    }
}
