package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class P11866 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] n = bf.readLine().split(" ");
		int num = Integer.parseInt(n[0]);
		int delNum = Integer.parseInt(n[1]);
		List<Integer> ints = new LinkedList<>();
		for (int i = 1; i <= num; i++) {
			ints.add(i);
		}
		int index = 0;
		
		while(!ints.isEmpty()) {
			index = (index + (delNum - 1)) % ints.size();
			System.out.print(ints.remove(index) + " ");
		}
	}

}
