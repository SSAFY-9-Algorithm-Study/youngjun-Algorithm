package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class P1427 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] a =   bf.readLine().split("");
        Integer []b = new Integer[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = Integer.parseInt(a[i]);
        }
        Arrays.sort(b,Collections.reverseOrder());
        for (Integer nums : b
             ) {
            System.out.print(nums);
            
        }

    }
}
