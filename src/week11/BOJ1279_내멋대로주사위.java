package week11;

import java.util.Scanner;

public class BOJ1279_내멋대로주사위 {
    static final int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        System.out.println(getCaseCount(M));
    }

    static long getCaseCount(int M) {
        long result = 0;
        for (int a = 1; a <= M - 2; a++) {
            for (int b = a + 1; b <= M - 1; b++) {
                int c = M - a - b;
                if (c <= b) break;
                result++;
            }
        }
        return result * 20 % MOD;
    }
}