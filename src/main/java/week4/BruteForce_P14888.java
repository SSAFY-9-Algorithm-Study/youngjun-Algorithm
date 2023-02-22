package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BruteForce_P14888 {

	static int n;
	static int calN;
	static int nums[];
	static int cals[];
	static List res;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		calN = n - 1;
		nums = new int[n];
		cals = new int[4];
		res = new ArrayList<Integer>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			cals[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0, cals);
	}

	public static void dfs(int L, int[] cals) {
		
		if (L == (n - 1)) {
			int a = calculate(nums, res);
			System.out.println(res);
			System.out.println(a);
			res.clear();
		}

		else {
			for (int j = 0; j < 4; j++) {
				if (cals[j] != 0) {
					res.add(j);
					cals[j] = 0;
					dfs(L + 1, cals);
				}
				cals[j] = 1;
			}
		}
	}

	public static int calculate(int[] nums, List<Integer> res) {
		int ans = nums[0];
		for (int i = 0; i < res.size(); i++) {
			int ops = res.get(i);
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
				}
			}
		}
		return ans;
	}

}
