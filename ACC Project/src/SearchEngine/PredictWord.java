package SearchEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PredictWord {

	public static String htmlDirectoryPath = "dat/HTML files/";
	public static String txtDirectoryPath = "dat/Text Files/";
	static HashMap<String, Integer> numbers = new HashMap<String, Integer>();
	static ArrayList<String> key = new ArrayList<String>();
	
	public static void suggestAltWord(String wordToSearch) {
		String line = " ";
		String regex = "[a-z0-9]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(line);
		int fileNumber = 0;

		File dir = new File(txtDirectoryPath);
		File[] fileArray = dir.listFiles();
		for (File file: fileArray) {
			try {
				findWord(file, fileNumber, matcher, wordToSearch);
				fileNumber++;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		Integer allowedDistance = 1; 
		boolean matchFound = false; 
		int i = 0;
		for (@SuppressWarnings("rawtypes") Map.Entry entry : numbers.entrySet()) {
			if (allowedDistance == entry.getValue()) {
				i++;
				if(i==1)
				System.out.println("Did you mean? ");
				System.out.print("(" + i + ") " + entry.getKey() + "\n");
				matchFound = true;
			}
		}
		if (!matchFound)
			System.out.println("Entered word cannot be resolved....");
	}

	
	public static void findWord(File sourceFile, int fileNumber, Matcher match, String str)
			throws FileNotFoundException, ArrayIndexOutOfBoundsException {
		try {
			BufferedReader my_rederObject = new BufferedReader(new FileReader(sourceFile));
			String line = null;

			while ((line = my_rederObject.readLine()) != null) {
				match.reset(line);
				while (match.find()) {
					key.add(match.group());
				}
			}

			my_rederObject.close();
			for (int p = 0; p < key.size(); p++) {
				numbers.put(key.get(p), EditDistance.editDistance(str.toLowerCase(), key.get(p).toLowerCase()));
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}
}
