package SearchEngine;

import java.io.File;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SearchWord {
	private static Scanner sc = new Scanner(System.in);
	//public static String htmlDirectoryPath = "dat/HTML files/";
	//public static String txtDirectoryPath = "dat/Text Files/";
	public static void searchWord() {

		Hashtable<String, Integer> FileList = new Hashtable<String, Integer>();

		System.out.println("\nEnter word:");
		String wordToSearch= sc.next();

		int WordFrequency = 0;
		int TotFiles = 0;
		FileList.clear();
		try {
			System.out.println("\nSearching...");
			File Files = new File("dat/HTML files/");

			File[] ArrayofFiles = Files.listFiles();

			for (File file: ArrayofFiles) {
				In data = new In(file.getAbsolutePath());
				String txt = data.readAll();
				data.close();
				Pattern p = Pattern.compile("::");
				String[] file_name = p.split(txt);
				WordFrequency = Search.wordSearch(txt, wordToSearch.toLowerCase(), file.getName());

				if (WordFrequency != 0) {
					FileList.put(file.getName(), WordFrequency);
					TotFiles++;
				}	
			}
			if(TotFiles>0) {
				System.out.println("\nTotal number of files containing word : " + wordToSearch + " is : " + TotFiles);
			}else {
				System.out.println("\nFile not found! word searched: "+ wordToSearch);
				System.out.println("Wait.....\n");
				PredictWord.suggestAltWord(wordToSearch.toLowerCase());
			}

			RankPages.rankFiles(FileList, TotFiles); 				   


		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}
}

