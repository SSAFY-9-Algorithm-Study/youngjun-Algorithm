package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class P1822 {

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		Set<Integer> a = new TreeSet<>();
		Set<Integer> b = new TreeSet<>();

		int first = 0;
		int second = 0;

		StringTokenizer st = new StringTokenizer(bf.readLine());
		first = Integer.parseInt(st.nextToken());
		second = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < first; i++) {
			a.add(Integer.parseInt(st.nextToken()));
		}

		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < second; i++) {
			b.add(Integer.parseInt(st.nextToken()));
		}

		a.removeAll(b);
		System.out.println(a.size());
		if(a.size()!=0) {
			for(int val : a)
				System.out.print(val + " ");
		}
		
	}

}
