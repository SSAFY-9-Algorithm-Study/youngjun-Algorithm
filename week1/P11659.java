package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//https://www.acmicpc.net/problem/11659 구간합 구하기
public class P11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int nums = Integer.parseInt(st.nextToken());
        int sections = Integer.parseInt(st.nextToken());
        int numArray[] = new int[nums+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= nums; i++) {
            numArray[i] = numArray[i-1] + Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < sections; i++) {
            st = new StringTokenizer(br.readLine());
            int sectionStart = Integer.parseInt(st.nextToken());
            int sectionEnd = Integer.parseInt(st.nextToken());
            System.out.println(numArray[sectionEnd] - numArray[sectionStart-1]);
        }
    }
}
