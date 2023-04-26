package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ14725_개미굴 {

	static int N;
	static int[] diffDepth;
	static int maxinfoSize;
	static List<List<StringInfo>> infoList = new ArrayList<>();

	static class StringInfo {
		String str;
		boolean erase;

		// String이 지워질 것인지 아닌지를 담음
		public StringInfo(String str, boolean erase) {
			super();
			this.str = str;
			this.erase = erase;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		diffDepth = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int depth = Integer.parseInt(st.nextToken());
			List<StringInfo> info = new ArrayList<>();

			for (int j = 0; j < depth; j++) {
				info.add(new StringInfo(st.nextToken(), false));
			}

			infoList.add(info);
			maxinfoSize = Math.max(info.size(), maxinfoSize);
		}

		// 정렬 기준 구현, 각 정보리스트마다 string값을 비교하여 오름차순 정렬
		infoList.sort(new Comparator<List<StringInfo>>() {

			@Override
			public int compare(List<StringInfo> o1, List<StringInfo> o2) {
				int minSize = Math.min(o1.size(), o2.size());
				for (int i = 0; i < minSize; i++) {
					if (o1.get(i).str.compareTo(o2.get(i).str) < 0)
						return -1;
					else if (o1.get(i).str.compareTo(o2.get(i).str) > 0)
						return 1;
				}
				return 0;
			}
		});

		// 지워야 하는 string들을 모든 정보리스트마다 이전 정보리스트와 비교하면서 체크
		for (int i = 1; i < infoList.size(); i++) {
			List<StringInfo> lastInfo = infoList.get(i-1);
			List<StringInfo> curInfo = infoList.get(i);
			for (int j = 0; j < curInfo.size(); j++) {
				String lastString = lastInfo.get(j).str;
				String curString = curInfo.get(j).str;
				
					if(lastString.equals(curString)) {
						curInfo.get(j).erase = true;
					}
					else {
						break;
					}
			}

		}
		
		// erase로 체크된 stringinfo는 출력하지 않고 나머지만 index*2만큼 - 를 붙여서 출력
		for (int i = 0; i < infoList.size(); i++) {
			List<StringInfo> curInfo = infoList.get(i);
			for (int j = 0; j < curInfo.size(); j++) {
				StringInfo curStringInfo = curInfo.get(j);
				
				if(!curStringInfo.erase) {
					for (int k = 0; k < j*2; k++) {
						System.out.print("-");
					}
					System.out.println(curStringInfo.str);
				}
				
			}
		}

	}

}
