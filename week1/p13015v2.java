package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//https://www.acmicpc.net/problem/13015
public class p13015v2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(bf.readLine());
        StringBuilder filledNum = new StringBuilder();
        StringBuilder unfilledNum = new StringBuilder();
        StringBuilder blankSpace = new StringBuilder();
        for (int i = 0; i < num; i++) {
            filledNum.append("*");
            if ((i == 0 || i == num - 1)) {
                unfilledNum.append("*");
            } else {
                unfilledNum.append(" ");
            }
            blankSpace.append("  ");
        }
        String newBlankSpace = blankSpace.substring(0, 2 * num - 3);
        System.out.println(filledNum+ newBlankSpace +  filledNum);

        for (int i = 1; i < num - 1; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            System.out.println(unfilledNum + newBlankSpace.substring(0, 2 * num - 3 - 2 * i) + unfilledNum);
        }

        for (int i = 0; i < num - 1; i++) {
            System.out.print(" ");
        }
        System.out.print(unfilledNum + unfilledNum.substring(1));
        System.out.println();


        for (int i = num - 2; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            System.out.println(unfilledNum + newBlankSpace.substring(0, 2 * num - 3 - 2 * i) + unfilledNum);
        }
        System.out.println(filledNum + newBlankSpace + filledNum);

    }
}
