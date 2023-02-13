package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class P11650 {

	static class XY implements Comparable<XY> {
		int x;
		int y;

		XY(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(XY o) {
			if (this.x == o.x)
				return this.y - o.y;
			return this.x - o.x;

		}
	}

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        List<XY> xyList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            xyList.add(new XY(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(xyList);

        for (int i = 0; i < n; i++) {
            System.out.println(xyList.get(i).x + " " + xyList.get(i).y);
        }
        bf.close();
    }
}
