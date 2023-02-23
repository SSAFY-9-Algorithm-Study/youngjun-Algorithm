package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BruteForce_P14888 {

	static int n;
	static int nums[];
	static int cals[];
	static int res[];
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int test = 0;
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		nums = new int[n];
		cals = new int[4];
		res = new int[n - 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			cals[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0);
		System.out.println(max);
		System.out.println(min);
		System.out.println(test);
	}

	public static void dfs(int L) {

		if (L == (n - 1)) {
			int ans = calculate(nums, res);
			test++;
			max = ans>max ? ans : max; 
			min = ans<min ? ans : min; 
		}

		else {
			for (int j = 0; j < 4; j++) {
				if (cals[j] != 0) {
					res[L] = j;
					cals[j] -= 1;
					dfs(L + 1);
					cals[j] += 1;
				}

			}
		}
	}

	public static int calculate(int[] nums, int[] res) {
		int ans = nums[0];
		for (int i = 0; i < res.length; i++) {
			int ops = res[i];
			if (ops == 0)
				ans += nums[i + 1];
			else if (ops == 1)
				ans -= nums[i + 1];
			else if (ops == 2)
				ans *= nums[i + 1];
			else if (ops == 3) {
				if (ans < 0) {
					ans = -ans;
					ans /= nums[i + 1];
					ans = -ans;
				} else
					ans /= nums[i + 1];
			}
		}
		return ans;
	}

}
