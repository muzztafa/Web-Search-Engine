package SearchEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;

public class RankPages {

	public static void rankFiles(Hashtable<?, Integer> files, int occurunces) {

		ArrayList<Map.Entry<?, Integer>> fileList = new ArrayList<Map.Entry<?, Integer>>(files.entrySet());
		Collections.sort(fileList, new Comparator<Map.Entry<?, Integer>>() {
			public int compare(Map.Entry<?, Integer> obj1, Map.Entry<?, Integer> obj2) {
				return obj1.getValue().compareTo(obj2.getValue());
			}
		});

		Collections.reverse(fileList);

		if (occurunces != 0) {
			if(occurunces > 10)
				System.out.println("\n------ Top 10 search results ------");
			else
				System.out.println("\n------ Top "+occurunces+" search results ------");

			//printing first 10 ranked files
			for (int k = 0; k < 10; k++) {
				if(fileList.get(k).getKey()!=null) {
					System.out.println("(" + (k+1) + ") " + fileList.get(k).getKey());
				}
			}
		} 
	}
	
}
