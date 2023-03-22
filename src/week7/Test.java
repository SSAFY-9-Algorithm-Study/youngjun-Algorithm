package week7;

public class Test {
	
	public static void main(String[] args) {
		System.out.println(factorial(30));
	}
	
	
	private static long factorial(int i) {
		if(i==1 || i==0)
			return 1;
		return factorial(i-1)*i;
	}
}


