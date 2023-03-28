
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SWEA1257_K번째문자열 {

	static int T;
	static int N;
	static StrAndSubs[] strAndSubs;
	static List<String> dict = new ArrayList<>();
	static String ans;

	static class StrAndSubs {
		String str;
		String[] strSub;
		int LCP;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T + 1; t++) {

			N = Integer.parseInt(br.readLine());

			String str = br.readLine();
			int strlen = str.length();
			strAndSubs = new StrAndSubs[strlen];
			for (int i = 0; i < strlen; i++) {
				StrAndSubs strAndSub = new StrAndSubs();
				strAndSub.str = str.substring(i, strlen);
				strAndSub.strSub = new String[strlen];
				strAndSubs[i] = strAndSub;
			}

			Arrays.sort(strAndSubs, new Comparator<StrAndSubs>() {

				@Override
				public int compare(StrAndSubs o1, StrAndSubs o2) {
					// TODO Auto-generated method stub
					return o1.str.compareTo(o2.str);
				}
			});
//			System.out.println(Arrays.toString(strAndSubs));

			// 각 접미사들의 접두사들 모두 저장
			for (int i = 0; i < strAndSubs.length; i++) {
				String curStr = strAndSubs[i].str;
				for (int j = 0; j < curStr.length(); j++) {
					strAndSubs[i].strSub[j] = curStr.substring(0, j + 1);
				}
			}
			strAndSubs[0].LCP = 0;

			// 각 접미사들의 LCP 저장
			for (int i = 1; i < strAndSubs.length; i++) {
				String checkStr1 = strAndSubs[i - 1].str;
				String checkStr2 = strAndSubs[i].str;
				strAndSubs[i].LCP = lcpCal(checkStr1, checkStr2);
			}

			int idx = 0;
			boolean found = false;
			for (int i = 0; i < strAndSubs.length; i++) {
				if (found)
					break;
				for (int j = strAndSubs[i].LCP; j < strAndSubs[i].str.length(); j++) {
					String curStr = strAndSubs[i].strSub[j];
					if (idx == N - 1) {
						ans = curStr;
						found = true;
						break;
					}

					else {
						dict.add(curStr);
						idx++;
					}

				}
			}
			
			if(ans==null)
				ans = "none";
			System.out.println("#" + t + " " + ans);
			ans = null;

		}

	}

	public static int lcpCal(String str1, String str2) {
		int str1Len = str1.length();
		int str2Len = str2.length();
		int checkLen = Math.min(str1Len, str2Len);
		int lcp = 0;
		for (int i = 0; i < checkLen; i++) {
			if (str1.charAt(i) == str2.charAt(i)) {
				lcp++;
			} else
				break;
		}
		return lcp;
	}

}
