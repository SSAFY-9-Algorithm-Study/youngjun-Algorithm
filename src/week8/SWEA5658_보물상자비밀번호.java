package week8;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA5658_보물상자비밀번호 {

	static int T;
	static int strLen;
	static int numSize;
	static int K;
	static Set<String> nums;
	static List<Integer> numsConverted;
	static String str;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());
			strLen = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			nums = new HashSet<>();
			numsConverted = new ArrayList<>();
			numSize = strLen / 4;
			str = br.readLine();

			for (int i = 0; i < numSize; i++) {
				putNums();
				char lastChar = str.charAt(strLen - 1);
				str = str.substring(0, strLen - 1);
				str = lastChar + str;
			}

			Collections.sort(numsConverted, Collections.reverseOrder());
			System.out.println("#" + t + " " + numsConverted.get(K - 1));
		}

	}

	private static void putNums() {
		for (int i = 0; i <= strLen - numSize; i += numSize) {
			String subStr = str.substring(i, i + numSize);
			int curSetLen = nums.size();
			nums.add(subStr);
			if (curSetLen != nums.size()) {
				numsConverted.add(Integer.parseInt(subStr, 16));
			}
		}

	}
}
