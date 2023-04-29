package week10;


import java.io.*;
import java.util.*;

public class 은하의개미굴 {
    static final String FLOOR = "--";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 개미 굴 정보 입력 및 정렬
        int N = Integer.parseInt(br.readLine());
        List<String> cave = new ArrayList<>();
        String line = "";
        int idx = 0;
        for (int i = 0; i < N; i++) {
            line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == ' ') {
                    idx = j;
                    break;
                }
            }
            cave.add(line.substring(idx + 1));
        }

        Collections.sort(cave);

        // 개미 굴 출력
        String start = "", now = "";
        String[] written = new String[16];
        int floor = 1;

        for (String info : cave) {
            floor = 1;
            st = new StringTokenizer(info);
            start = st.nextToken();
            if (!start.equals(written[floor])) {
                written = new String[16];
                written[floor] = start;
                sb.append(start).append("\n");
            }

            floor++;

            while (st.hasMoreTokens()) {
                now = st.nextToken();
                if (!now.equals(written[floor])) {
                    written[floor] = now;
                    for (int i = 1; i < floor; i++) {
                        sb.append(FLOOR);
                    }
                    sb.append(now).append("\n");
                }
                floor++;
            }
            
            while (floor< 16) {
                written[floor] = "";
                floor++;
            }
        }
        
        System.out.print(sb);
        br.close();
    }
}



