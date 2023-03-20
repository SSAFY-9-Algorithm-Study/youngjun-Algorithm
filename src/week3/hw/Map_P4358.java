package week3.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Map_P4358 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		Map<String, Double> m = new TreeMap<String, Double>();
		
		String wood = "";
		int total = 0;
		
		//(wood=bf.readLine()) != null

		while((wood=bf.readLine()) != null) {
			total++;
			if(m.containsKey(wood)) {
				m.put(wood, m.get(wood)+1);
			}
			else {
				m.put(wood, (double) 1);
			}
		}
		

			
		List<Double> valPercent = new ArrayList<>(m.values());
		List<String> keyList = new ArrayList<>(m.keySet());
		for(int i=0; i<m.size(); i++) {
			String curKey = keyList.get(i);
			m.replace(curKey, (double) (Math.round(m.get(curKey)/total * 1000000)) / 10000);
			System.out.print(curKey + " ");
			System.out.println(String.format("%.4f", m.get(curKey)));
		}
		
		bf.close();
	}
}
