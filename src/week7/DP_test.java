package week7;

public class DP_test {
	
	static long[] DP = new long[101];

	public static void main(String[] args) {
		System.out.println(factorial(100));
//		System.out.println(fibonacci(100));
		System.out.println(DPfiboUp(100));
		System.out.println(DPfiboDown(100));
	}

	private static int factorial(int i) {
		if(i==1 || i==0)
			return 1;
		return factorial(i-1)*i;
	}

	private static int fibonacci(int i) {
		if(i==1 || i==2)
			return 1;
		return fibonacci(i-1) + fibonacci(i-2);

	}
	
	private static long DPfiboUp(int i) {
		DP[0] = 0;
		DP[1] = 1;
		for (int j = 2; j < DP.length; j++) {
			DP[j] = DP[j-1] + DP[j-2];
		}
		return DP[i];
	}
	
	private static long DPfiboDown(int i) {
		if(i<=1)
			return i;
		if(DP[i]>0)
			return DP[i];
		DP[i] = DPfiboDown(i-1) + DPfiboDown(i-2);
		return DP[i];
	}
	

}
