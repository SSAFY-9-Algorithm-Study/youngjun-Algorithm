package week17;

import java.io.*;
import java.util.*;

public class BOJ1083_소트 {

	static int N,S;
	static List<Integer> arr = new ArrayList<>();

	    public static void main(String[] args) throws IOException {

	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	        N = Integer.parseInt(br.readLine());

	        arr = new ArrayList<>();
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        for (int i = 0; i < N; i++) {
	            arr.add(Integer.parseInt(st.nextToken()));
	        }

	        S = Integer.parseInt(br.readLine());

	        int moveCnt = 0, changeIdx = 0;
	        while (moveCnt < S && changeIdx < N - 1) {
	            int maxNum = arr.get(changeIdx), maxIdx = -1;
	            

	            int idx = changeIdx + 1, count = 1;
	            while (moveCnt + count <= S && idx < N) {
	                int num = arr.get(idx);
	                if (num > maxNum) {
	                    maxNum = num;
	                    maxIdx = idx;
	                }
	                count++;
	                idx++;
	            }

	            if (maxIdx != -1) {
	                arr.remove(maxIdx);
	                arr.add(changeIdx, maxNum);
	                moveCnt += maxIdx - changeIdx;
	            }
	            changeIdx++;
	        }

	        StringBuilder sb = new StringBuilder();
	        for (int i : arr) {
	            sb.append(i).append(' ');
	        }
	        System.out.println(sb);
	        br.close();
	    }

}
