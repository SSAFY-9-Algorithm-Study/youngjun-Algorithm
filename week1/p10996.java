package week1;


//https://www.acmicpc.net/problem/10996 별찍기 21

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p10996 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int pattern = Integer.parseInt(bf.readLine());

        for (int i = 0; i < pattern; i++) {
            String row1 = "";
            String row2 = "";

            for (int j = 0; j < pattern; j++) {
                if (j % 2 == 0) {
                    row1 += "*";
                    row2 += " ";
                } else {
                    row1 += " " ;
                    row2 += "*";
                }

            }
            System.out.println(row1);
            System.out.println(row2);
        }
    }
}
