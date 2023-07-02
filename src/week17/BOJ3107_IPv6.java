package week17;

import java.io.*;
import java.util.*;

public class BOJ3107_IPv6 {

	static StringBuilder sb;
	static StringBuilder resSb1 = new StringBuilder();
	static StringBuilder resSb2 = new StringBuilder();
	static String zeroString = "0000:";
	static int cnt;
	static boolean isSb1 = true;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder(br.readLine());
		sb.append(":");

		StringBuilder tempSb = new StringBuilder();

		for (int i = 0; i < sb.length(); i++) {
			char curChar = sb.charAt(i);

			if (curChar == ':') {
				if (tempSb.length() == 0) {
					isSb1 = false;

				} else {
					cnt++;
					for (int j = 0; j < 4 - tempSb.length(); j++) {
						appendToSb("0");
					}
					appendToSb(tempSb.toString());
					appendToSb(":");
					tempSb.setLength(0);
				}

			} else {
				tempSb.append(curChar);
			}
		}
		for (int i = 0; i < 8 - cnt; i++) {
			resSb1.append(zeroString);
		}
		resSb1.append(resSb2);
		resSb1.deleteCharAt(resSb1.length() - 1);
		System.out.println(resSb1);
	}


	private static void appendToSb(String sb) {
		if (isSb1) {
			resSb1.append(sb);
		} else {
			resSb2.append(sb);
		}
	}

}
