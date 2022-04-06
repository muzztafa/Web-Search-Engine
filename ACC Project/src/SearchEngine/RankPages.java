package SearchEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;

public class RankPages {

	public static void rankFiles(Hashtable<?, Integer> files, int occur) {

		ArrayList<Map.Entry<?, Integer>> fileList = new ArrayList<Map.Entry<?, Integer>>(files.entrySet());
		Collections.sort(fileList, new Comparator<Map.Entry<?, Integer>>() {
			public int compare(Map.Entry<?, Integer> obj1, Map.Entry<?, Integer> obj2) {
				return obj1.getValue().compareTo(obj2.getValue());
			}
		});

		Collections.reverse(fileList);

		if (occur != 0) {
			if(occur > 5)
				System.out.println("\n------ Top 5 search results ------");
			else
				System.out.println("\n------ Top "+occur+" search results ------");

			int noOfFetch = 5;
			int j = 0;
			int i=1;
			while (fileList.size() > j && noOfFetch > 0) {
				
				
				if(fileList.get(j).getKey()!=null) {
				System.out.println("(" + i + ") " + fileList.get(j).getKey());
				j++;
				i++;
				}
				noOfFetch--;
				
			}
		} 
	}
	
}
