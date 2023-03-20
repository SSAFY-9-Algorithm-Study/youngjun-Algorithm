package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BonusP2491 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n =Integer.parseInt(br.readLine());
		int remains = n-1;
		int maxLen = 1;
		int len = 1;
		int arr[] = new int[n];
		boolean desc = true;
		boolean same = false;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		if(n==1)
			System.out.println(1);
		else {
			if(arr[0]<arr[1])
				desc = false;
			else if(arr[0]==arr[1])
				same = true;
			else
				desc = true;
			len++;
			maxLen++;
			System.out.println(checker(n,desc,same, maxLen,arr,len,remains));
		}
	}

	public static int checker(int n, boolean desc, boolean same, int maxLen, int arr[], int len, int remains) {
		for (int i = 1; i < n - 1; i++) {
			if (desc && !same) {
				if (arr[i] < arr[i + 1]) {
					if (len > maxLen) {
						maxLen = len;
						if (maxLen > remains) {
							break;
						}
					}
					len = 2;
					desc = !desc;
				} else {
					len++;
				}
			} else if(!desc &&!same) {
				if (arr[i] > arr[i + 1]) {
					if (len > maxLen) {
						maxLen = len;
						if (maxLen > remains) {
							break;
						}
					}
					len = 2;
					desc = !desc;
				} else {
					len++;
				}
			}
			else
				len++;
			remains--;
			System.out.println("cur num is :" + arr[i]);
			System.out.println("cur desc is :" + desc);
			System.out.println("cur len is :" + len);
			System.out.println("cur maxLen is :" + maxLen );
			System.out.println();
		}

		return maxLen;

	}

}
