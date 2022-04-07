package SearchEngine;

import java.util.ArrayList;

public class Search {
	public static int wordSearch(String data, String word, String fileName) {
		int counter = 0;
		SearchEngine.BoyerMoore boyerMoore = new SearchEngine.BoyerMoore(word);
		ArrayList<Integer> offsets = boyerMoore.searchOccurences(data);
		counter = offsets.size();
		if (counter != 0) {
			System.out.println("Found in HTML file --> " + fileName + " --> " + counter + " times"); 
			System.out.println("-------------------------------------------------------------------------");
		}
		return counter;
	}
}
