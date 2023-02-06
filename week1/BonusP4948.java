package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//https://www.acmicpc.net/problem/4948
public class BonusP4948 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            int cnt = 0;
            int num = Integer.parseInt(bf.readLine());
            if(num==0)
                break;
            for (int i = num+1; i <= num*2; i++) {
                if(primaryNums(i)) cnt++;
            }
            System.out.println(cnt);
        }
    }

    public static boolean primaryNums(int a) {
        if (a < 2) return false;
        else if (a == 2) return true;
        else {
            for (int i = 2; i <= Math.sqrt(a); i++) {
                if (a % i == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}