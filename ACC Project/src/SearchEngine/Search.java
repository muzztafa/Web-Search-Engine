package SearchEngine;

import java.util.ArrayList;

public class Search {
	public static int wordSearch(String data, String word, String fileName) {
		int occurences = 0;
		SearchEngine.BoyerMoore boyerMoore = new SearchEngine.BoyerMoore(word);
		ArrayList<Integer> offsets = boyerMoore.searchOccurences(data);
		occurences = offsets.size();
		if (occurences != 0) {
			System.out.println("Found in HTML file --> " + fileName + " --> " + occurences + " times"); 
			System.out.println("-------------------------------------------------------------------------");
		}
		return occurences;
	}
}
