package week3;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Test {
	
	public static void main(String[] args) {
		TreeMap<Integer, String> tMap = new TreeMap<Integer, String>();
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		tMap.put(5, "fff");
		tMap.put(3, "bbb");
		tMap.put(2, "ccc");
		tMap.put(7, "ddd");
		tMap.put(1, "aaa");
		tMap.put(1, "cccc");
		
		map.replace(2, "ddd");
		System.out.println(tMap.firstEntry());
		System.out.println(tMap.ceilingEntry(6)); //6 이사큰 키 중 가장 작은 키
	}

}
